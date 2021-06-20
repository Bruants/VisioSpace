/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.vspaceshared.utilities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kevin
 */
public class Combinaison {
    private List<Long> station = new ArrayList<>();
    
    private List<Long> pourcentageQuaisDisponibles = new ArrayList<>();

    public Combinaison(Long key, Long value) {
        station.add(key);
        pourcentageQuaisDisponibles.add(value);
    }

    public List<Long> getStation() {
        return station;
    }

    public List<Long> getPourcentageQuaisDisponibles() {
        return pourcentageQuaisDisponibles;
    }

    public void setStation(List<Long> superieurKey) {
        this.station = superieurKey;
    }

    public void setPourcentageQuaisDisponibles(List<Long> pourcentageQuaisDisponibles) {
        this.pourcentageQuaisDisponibles = pourcentageQuaisDisponibles;
    }
        
        
}
