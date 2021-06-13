/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.vspaceshared.utilities;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author AlexisVivier
 */
public class UsagerExport implements Serializable {
    
    private Long id;
    
    private List<ReservationExport> reservations;

    public UsagerExport() {
    }

    public UsagerExport(Long id, List<ReservationExport> reservations) {
        this.id = id;
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ReservationExport> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationExport> reservations) {
        this.reservations = reservations;
    }
    
    
}
