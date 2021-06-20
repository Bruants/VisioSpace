/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.vspaceshared.utilities;

import java.io.Serializable;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
public class QuaiExport implements Serializable {
    
    private Long id;
   
    private StationExport station;

    public QuaiExport() {
    }

    public QuaiExport(Long id, StationExport station) {
        this.id = id;
        this.station = station;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StationExport getStation() {
        return station;
    }

    public void setStation(StationExport station) {
        this.station = station;
    }
   
}
