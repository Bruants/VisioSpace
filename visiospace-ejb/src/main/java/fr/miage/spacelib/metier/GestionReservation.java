/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.facades.OperationFacadeLocal;
import fr.miage.spacelib.facades.ReservationFacadeLocal;
import java.util.Date;
import javax.ejb.Stateless;

/**
 * Gestion des reservations des usagers
 * @author AlexisVivier
 */
@Stateless
public class GestionReservation implements GestionReservationLocal {

    private ReservationFacadeLocal reservation;
    
    private OperationFacadeLocal operation;
    
    private GestionStationLocal stations;
    
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
     * Get the value of operation
     *
     * @return the value of operation
     */
    public OperationFacadeLocal getOperation() {
        return operation;
    }

    /**
     * Set the value of operation
     *
     * @param operation new value of operation
     */
    public void setOperation(OperationFacadeLocal operation) {
        this.operation = operation;
    }

    /**
     * Get the value of reservation
     *
     * @return the value of reservation
     */
    public ReservationFacadeLocal getReservation() {
        return reservation;
    }

    /**
     * Set the value of reservation
     *
     * @param reservation new value of reservation
     */
    public void setReservation(ReservationFacadeLocal reservation) {
        this.reservation = reservation;
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
     */
    @Override
    public void reserverVoyage(long idUsager, int nbPassagers, Date dateDepart, Date dateArrivee, long stationDepart, long stationArrivee) {
    }

    /**
     * Débute le voyage reservé
     * La navette libére alors le quai 
     * de la station de départ et une opération de départ
     * est créer dans l'historique de la navette
     * @param idVoyage Identifiant du voyage courant
     */
    @Override
    public void departVoyage(long idVoyage) {
    }

    /**
     * Termine le voyage initié
     * La navette s'amarre alors au quai 
     * de la station d'arrivée et une opération d'arrivée
     * est créer dans l'historique de la navette
     * @param idVoyage Identifiant du voyage courant
     */
    @Override
    public void arriveeVoyage(long idVoyage) {
    }

}
