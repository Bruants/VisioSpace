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
 *
 * @author AlexisVivier
 */
@Stateless
public class GestionReservation implements GestionReservationLocal {

    private ReservationFacadeLocal reservation;
    
    private OperationFacadeLocal operation;
    

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
    
    @Override
    public void reserverVoyage(long id, int passagers, Date dateDepart, Date dateArrivee, long stationDepart, long stationArrivee) {
    }

}
