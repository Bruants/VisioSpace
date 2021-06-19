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

        System.out.println("Gestion Reservation USAGER : " + usagerFacade.find(idUsager));

        System.out.println("Coucou1");

        if (usagerFacade.find(idUsager) == null) {
            throw new AucunUsagerException();
        }

        System.out.println("Coucou2");

        if (dateDepart.after(dateArrivee)) {
            throw new DateInvalideException();
        }
        System.out.println("Coucou3");

        if (nbPassagers <= 0 || nbPassagers > 15) {
            throw new NombrePassagersInvalideException();
        }

        System.out.println("Coucou4");

        if (stationFacade.find(stationDepart) == null
                || stationFacade.find(stationArrivee) == null) {
            throw new AucuneStationException();
        }

        System.out.println("Coucou5");

        /* Création de l'opération voyage */
        voyage.setTypeOperation(Operation.TYPES.VOYAGE);
        voyage.setTerminee(false);
        voyage.setDate(new Date());
        voyage.setDateDepart(dateDepart);
        voyage.setDateArrivee(dateArrivee);
        operationFacade.create(voyage);
        reservation.setVoyage(voyage);

        System.out.println("Coucou6");

        /* Création de la réservation */
        reservation.setUsager(usagerFacade.find(idUsager));

        System.out.println("Coucou7");

        reservation.setNbPassagers(nbPassagers);

        System.out.println("Coucou8");

        //Identifie le nombres de places a reserver
        nbPlacesNavette = nbPassagers <= 2 ? 2 : -1;
        nbPlacesNavette = nbPassagers <= 5 ? 5 : -1;
        nbPlacesNavette = nbPassagers <= 10 ? 10 : 15;

        //Recherche d'une navette correspondante
        navette = gestionStation.navettesDispo(stationDepart, nbPlacesNavette);
        reservation.setDepart(navette.getStationeSur());
        reservation.setUtilisee(navette);

        System.out.println("Coucou9");

        //Recherche d'un quai de libre
        reservation.setArrivee(
                gestionStation.reserverQuai(stationArrivee, navette.getId(), dateArrivee)
        );

        System.out.println("Coucou11");

        reservationFacade.create(reservation);

        System.out.println("Coucou12");

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
            throws AucunVoyageException, AucuneNavetteException, AucunQuaiException {

        Reservation reservation = reservationFacade.find(idReservation);

        if (reservation == null) {
            throw new AucunVoyageException();
        }

        Operation voyage = reservation.getVoyage();

        gestionStation.arrimerNavette(reservation.getArrivee().getId(), reservation.getUtilisee().getId());

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

    @Override
    public void annulerReservation(long idUsager, long idReservation) throws AucunUsagerException, AucunVoyageException {
        Reservation res = reservationFacade.find(idReservation);

        if (res == null) {
            throw new AucunVoyageException("La reservation n'existe pas");
        }

        if (res.getUsager().getId() != idUsager) {
            throw new AucunUsagerException("Mauvais usager : " + idUsager);
        }

        res.setAnnulee(true);
        // Annulation de l'opération
        res.setVoyage(res.getVoyage().getPrecedenteOperation());

        reservationFacade.edit(res);
    }

}
