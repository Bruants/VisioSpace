/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Navette;
import fr.miage.spacelib.entities.Operation;
import fr.miage.spacelib.entities.Reservation;
import fr.miage.spacelib.entities.Station;
import fr.miage.spacelib.facades.OperationFacadeLocal;
import fr.miage.spacelib.facades.ReservationFacadeLocal;
import fr.miage.spacelib.facades.StationFacadeLocal;
import fr.miage.spacelib.facades.UsagerFacadeLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucunQuaiException;
import fr.miage.spacelib.vspaceshared.utilities.AucunUsagerException;
import fr.miage.spacelib.vspaceshared.utilities.AucunVoyageException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import fr.miage.spacelib.vspaceshared.utilities.DateInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePassagersInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePlacesInvalideException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Gestion des reservations des usagers
 *
 * @author AlexisVivier
 */
@Stateless
public class GestionReservation implements GestionReservationLocal {

    @EJB
    private GestionNavetteLocal gestionNavette;

    @EJB
    private GestionStationLocal gestionStation;

    @EJB
    private OperationFacadeLocal operationFacade;

    @EJB
    private ReservationFacadeLocal reservationFacade;

    @EJB
    private UsagerFacadeLocal usagerFacade;

    @EJB
    private StationFacadeLocal stationFacade;

    /**
     * Reserve le voyage d'un usager entre deux stations : - Trouver et reserver
     * une navette disponible à la station de départ - Trouver et reserver un
     * quai de libre à la station d'arrivée
     *
     * @param idUsager Identifiant de l'usager à l'origine de la commande
     * @param nbPassagers Nombre de passager dans la navette
     * @param dateDepart Date de départ du voyage
     * @param dateArrivee Date d'arrivée du voyage
     * @param stationDepart Station de départ du voyage
     * @param stationArrivee Station d'arrivée du voyage
     * @return reservation Le voyage réservé
     */
    @Override
    public Reservation reserverVoyage(long idUsager, int nbPassagers,
            Date dateDepart, Date dateArrivee,
            long stationDepart, long stationArrivee)
            throws AucunQuaiException, AucuneStationException,
            AucuneNavetteException, NombrePlacesInvalideException,
            AucunUsagerException, DateInvalideException, NombrePassagersInvalideException {

        Reservation reservation = new Reservation();
        Operation voyage = new Operation();
        Navette navette;
        int nbPlacesNavette;

        if (usagerFacade.find(idUsager) == null) {
            throw new AucunUsagerException();
        }

        if (dateDepart.after(dateArrivee)) {
            throw new DateInvalideException();
        }

        if (nbPassagers <= 0 || nbPassagers > 15) {
            throw new NombrePassagersInvalideException();
        }

        if (stationFacade.find(stationDepart) == null
                || stationFacade.find(stationArrivee) == null) {
            throw new AucuneStationException();
        }

        /* Création de l'opération voyage */
        voyage.setTypeOperation(Operation.TYPES.VOYAGE);
        voyage.setTerminee(false);
        voyage.setDate(new Date());
        voyage.setDateDepart(dateDepart);
        voyage.setDateArrivee(dateArrivee);
        reservation.setVoyage(voyage);

        /* Création de la réservation */
        reservation.setUsager(usagerFacade.find(idUsager));

        reservation.setNbPassagers(nbPassagers);

        //Identifie le nombres de places a reszerver
        nbPlacesNavette = nbPassagers <= 10 ? 10 : 15;
        nbPlacesNavette = nbPassagers <= 5 ? 5 : -1;
        nbPlacesNavette = nbPassagers <= 2 ? 2 : -1;

        //Recherche d'une navette correspondante
        navette = gestionStation.navettesDispo(stationDepart, nbPlacesNavette);
        reservation.setUtilisee(navette);

        reservation.setDepart(gestionNavette.quai(navette.getId()));
        //Recherche d'un quai de libre
        reservation.setArrivee(
                gestionStation.reserverQuai(stationArrivee, navette.getId())
        );

        reservationFacade.create(reservation);

        return reservation;
    }

    /**
     * Débute le voyage reservé La navette libére alors le quai de la station de
     * départ et une opération de départ est créer dans l'historique de la
     * navette
     *
     * @param idVoyage Identifiant du voyage courant
     */
    @Override
    public void departVoyage(long idReservation)
            throws AucuneNavetteException, AucunVoyageException,
            AucunQuaiException {
        Navette navette = reservationFacade.find(idReservation).getUtilisee();

        if (navette == null) {
            throw new AucuneNavetteException();
        }

        gestionNavette.lancerNavette(navette.getId());
    }

    /**
     * Termine le voyage initié La navette s'amarre alors au quai de la station
     * d'arrivée et une opération d'arrivée est créer dans l'historique de la
     * navette
     *
     * @param idVoyage Identifiant du voyage courant
     */
    @Override
    public void arriveeVoyage(long idReservation)
            throws AucunVoyageException {

        Reservation reservation = reservationFacade.find(idReservation);

        if (reservation == null) {
            throw new AucunVoyageException();
        }

        Operation voyage = reservation.getVoyage();

        voyage.setTerminee(true);
        reservationFacade.edit(reservation);
    }

    @Override
    public List<Station> toutesStations() {
        return gestionStation.toutesStations();
    }

    @Override
    public Reservation lastReservation(long idUsager) throws AucunVoyageException {
        if (reservationFacade.findUsager(idUsager).size() <= 0) {
            throw new AucunVoyageException(" de l'utilisateur : " + idUsager);
        }
        return reservationFacade.findUsager(idUsager).get(0);
    }

    @Override
    public Reservation trouver(long idReservation) {
        return reservationFacade.find(idReservation);
    }

}
