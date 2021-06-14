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
import fr.miage.spacelib.facades.UsagerFacadeLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucunQuaiException;
import fr.miage.spacelib.vspaceshared.utilities.AucunVoyageException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePassagersInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePlacesInvalideException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Gestion des reservations des usagers
 * @author AlexisVivier
 */
@Stateless
public class GestionReservation implements GestionReservationLocal {

    @EJB(beanName = "ReservationUsagerEJB")
    private UsagerFacadeLocal usagerFacade;

    @EJB(beanName = "ReservationEJB")
    private ReservationFacadeLocal reservations;
    
    @EJB(beanName = "OperationReservationEJB")
    private OperationFacadeLocal operations;
    
    @EJB(beanName = "GestionStationReservationEJB")
    private GestionStationLocal stations;
    
    @EJB(beanName = "GestionNavetteReservationEJB")
    private GestionNavetteLocal navettes;

    /**
     * Get the value of navettes
     *
     * @return the value of navettes
     */
    public GestionNavetteLocal getNavettes() {
        return navettes;
    }

    /**
     * Set the value of navettes
     *
     * @param navettes new value of navettes
     */
    public void setNavettes(GestionNavetteLocal navettes) {
        this.navettes = navettes;
    }


    /**
     * Get the value of stations
     *
     * @return the value of stations
     */
    public GestionStationLocal getStations() {
        return stations;
    }

    /**
     * Set the value of stations
     *
     * @param stations new value of stations
     */
    public void setStations(GestionStationLocal stations) {
        this.stations = stations;
    }


    /**
     * Get the value of operations
     *
     * @return the value of operations
     */
    public OperationFacadeLocal getOperations() {
        return operations;
    }

    /**
     * Set the value of operations
     *
     * @param operations new value of operations
     */
    public void setOperations(OperationFacadeLocal operations) {
        this.operations = operations;
    }

    /**
     * Get the value of reservations
     *
     * @return the value of reservations
     */
    public ReservationFacadeLocal getReservations() {
        return reservations;
    }

    /**
     * Set the value of reservations
     *
     * @param reservations new value of reservations
     */
    public void setReservations(ReservationFacadeLocal reservations) {
        this.reservations = reservations;
    }
    
    /**
     * Reserve le voyage d'un usager entre deux stations : 
     *  - Trouver et reserver une navette disponible
     *    à la station de départ
     *  - Trouver et reserver un quai de libre 
     *    à la station d'arrivée
     * @param idUsager       Identifiant de l'usager à l'origine de la commande
     * @param nbPassagers    Nombre de passager dans la navette
     * @param dateDepart     Date de départ du voyage
     * @param dateArrivee    Date d'arrivée du voyage
     * @param stationDepart  Station de départ du voyage
     * @param stationArrivee Station d'arrivée du voyage
     * @return reservation   Le voyage réservé 
     */
    @Override
    public Reservation reserverVoyage(long idUsager, int nbPassagers, 
            Date dateDepart, Date dateArrivee, 
            long stationDepart, long stationArrivee)
            
            throws AucunQuaiException, AucuneStationException, 
            AucuneNavetteException, NombrePlacesInvalideException {
        
        Reservation reservation = new Reservation();
        Operation voyage = new Operation();
        Navette navette;

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
        
        //Recherche d'une navette correspondante
        navette = stations.navettesDispo(stationDepart, nbPassagers);
        reservation.setUtilisee(navette);
        
        reservation.setDepart(navettes.quai(navette.getId()));
        //Recherche d'un quai de libre
        reservation.setArrivee(
                stations.reserverQuai(stationArrivee, navette.getId())
        );
        
        reservations.create(reservation);
        
        return reservation;
    }

    /**
     * Débute le voyage reservé
     * La navette libére alors le quai 
     * de la station de départ et une opération de départ
     * est créer dans l'historique de la navette
     * @param idVoyage Identifiant du voyage courant
     */
    @Override
    public void departVoyage(long idVoyage) 
            throws AucuneNavetteException, AucunVoyageException, 
            AucunQuaiException {
        Navette navette = reservations.find(idVoyage).getUtilisee();
        navettes.lancerNavette(navette.getId());
    }

    /**
     * Termine le voyage initié
     * La navette s'amarre alors au quai 
     * de la station d'arrivée et une opération d'arrivée
     * est créer dans l'historique de la navette
     * @param idVoyage Identifiant du voyage courant
     */
    @Override
    public void arriveeVoyage(long idVoyage) 
     throws AucunVoyageException {
        
        Reservation reservation = reservations.find(idVoyage);
        Operation voyage = reservation.getVoyage();
        
        voyage.setTerminee(true);
        reservations.edit(reservation);
    }
    
    @Override
    public List<Station> toutesStations(){
        return stations.toutesStations();
    }

}
