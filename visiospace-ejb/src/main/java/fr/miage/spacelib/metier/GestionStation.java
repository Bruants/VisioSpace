/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.facades.QuaiFacadeLocal;
import fr.miage.spacelib.facades.StationFacadeLocal;
import javax.ejb.Stateless;

/**
 *
 * @author AlexisVivier
 */
@Stateless
public class GestionStation implements GestionStationLocal {

    private QuaiFacadeLocal quai;
    
    private StationFacadeLocal station;

    /**
     * Get the value of station
     *
     * @return the value of station
     */
    public StationFacadeLocal getStation() {
        return station;
    }

    /**
     * Set the value of station
     *
     * @param station new value of station
     */
    public void setStation(StationFacadeLocal station) {
        this.station = station;
    }

    /**
     * Get the value of quai
     *
     * @return the value of quai
     */
    public QuaiFacadeLocal getQuai() {
        return quai;
    }

    /**
     * Set the value of quai
     *
     * @param quai new value of quai
     */
    public void setQuai(QuaiFacadeLocal quai) {
        this.quai = quai;
    }

    @Override
    public void creerStation(String coordonnees, int nbQuais) {
    }

    @Override
    public void reserverQuai(long station, long quai, long navette) {
    }

    @Override
    public void ajouterNavette(long station, long navette) {
    }

    @Override
    public void liberaiQuai(long station, long quai, long navette) {
    }

}
