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
import fr.miage.spacelib.vspaceshared.utilities.VoyageDejaCommenceException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Gestion des reservations des usagers
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
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
     * @throws fr.miage.spacelib.vspaceshared.utilities.AucunQuaiException
     * @throws fr.miage.spacelib.vspaceshared.utilities.AucuneStationException
     * @throws fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException
     * @throws fr.miage.spacelib.vspaceshared.utilities.NombrePlacesInvalideException
     * @throws fr.miage.spacelib.vspaceshared.utilities.AucunUsagerException
     * @throws fr.miage.spacelib.vspaceshared.utilities.DateInvalideException
     * @throws fr.miage.spacelib.vspaceshared.utilities.NombrePassagersInvalideException
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
        operationFacade.create(voyage);
        reservation.setVoyage(voyage);//

        /* Création de la réservation */
        reservation.setUsager(usagerFacade.find(idUsager));
        
        reservation.setNbPassagers(nbPassagers);

        
        //Identifie le nombres de places a reserver
        if (nbPassagers > 10) {
            nbPlacesNavette = 15;
            
        } else if (nbPassagers > 5) {
            nbPlacesNavette = 10;

        } else if (nbPassagers > 2) {
            nbPlacesNavette = 5;

        } else {
            nbPlacesNavette = 2;
        }

        //Recherche d'une navette correspondante
        navette = gestionStation.navettesDispo(stationDepart, nbPlacesNavette);
        
        reservation.setDepart(navette.getStationeSur());
        reservation.setUtilisee(navette);
        //Recherche d'un quai de libre
        reservation.setArrivee(
                gestionStation.reserverQuai(stationArrivee, navette.getId(), dateArrivee)
        );
        
        navette.setDerniereOperation(voyage);


        reservationFacade.create(reservation);
                voyage.setReservation(reservation);
        return reservation;
    }

    /**
     * Débute le voyage reservé La navette libére alors le quai de la station de
     * départ et une opération de départ est créer dans l'historique de la
     * navette
     *
     * @param idReservation Identifiant du voyage courant
     * @throws fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException
     * @throws fr.miage.spacelib.vspaceshared.utilities.AucunVoyageException
     * @throws fr.miage.spacelib.vspaceshared.utilities.AucunQuaiException
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
     * @param idReservation Identifiant du voyage courant
     * @throws fr.miage.spacelib.vspaceshared.utilities.AucunVoyageException
     * @throws fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException
     * @throws fr.miage.spacelib.vspaceshared.utilities.AucunQuaiException
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
        reservation.getUtilisee().addCompteurVoyage();
        reservationFacade.edit(reservation);
    }

    /**
     * @return Toutes les stations existantes
     */
    @Override
    public List<Station> toutesStations() {
        return gestionStation.toutesStations();
    }

    /**
     * @param idUsager L'utilisateur dont on veut l'information
     * @return La dernière révision enregistrée par l'utilisateur dans le système
     * @throws AucunVoyageException 
     */
    @Override
    public Reservation lastReservation(long idUsager) throws AucunVoyageException {
        if (reservationFacade.findUsager(idUsager).size() <= 0) {
            throw new AucunVoyageException(" de l'utilisateur : " + idUsager);
        }
        return reservationFacade.findUsager(idUsager).get(0);
    }

    /**
     * @param idReservation L'id de la réservation que l'on veut obtenir
     * @return La réservation associée à l'idReservation
     */
    @Override
    public Reservation trouver(long idReservation) {
        return reservationFacade.find(idReservation);
    }

    /**
     * Annuler la réservation de l'usager, il est nécessaire que la réservation ne pas commencée et pas déjà annulée
     * @param idUsager L'utilisateur qui initie la réservation
     * @param idReservation La réservation que l'on veut annuler
     * @throws AucunUsagerException
     * @throws AucunVoyageException
     * @throws VoyageDejaCommenceException 
     */
    @Override
    public void annulerReservation(long idUsager, long idReservation) throws AucunUsagerException, AucunVoyageException, VoyageDejaCommenceException {
        Operation ancienneOperation;
        Reservation res = reservationFacade.find(idReservation);

        if (res == null) {
            throw new AucunVoyageException("La reservation n'existe pas");
        }

        if (res.getUsager().getId() != idUsager) {
            throw new AucunUsagerException("Mauvais usager : " + idUsager);
        }
        ancienneOperation = res.getVoyage();

        if((new Date()).after(ancienneOperation.getDateDepart()) && !res.isAnnulee()) {
            throw new VoyageDejaCommenceException();
        } // todo split exception avec voyage annulé

        res.setAnnulee(true);
        // Annulation de l'opération
        res.setVoyage(ancienneOperation.getPrecedenteOperation());
        operationFacade.remove(ancienneOperation);

        reservationFacade.remove(res);
    }

}
