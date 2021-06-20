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
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
public class StationExport implements Serializable {
    
    private Long id;
    
    /**Coordonées stellaire */
    private String coordonnee;

    public StationExport() {
    }

    public StationExport(Long id, String coordonnee) {
        this.id = id;
        this.coordonnee = coordonnee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoordonnee() {
        return coordonnee;
    }

    public void setCoordonnee(String coordonnee) {
        this.coordonnee = coordonnee;
    }
}
