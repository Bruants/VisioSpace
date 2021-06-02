/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import javax.ejb.Local;

/**
 *
 * @author AlexisVivier
 */
@Local
public interface GestionStationLocal {

    void creerStation(String coordonnees, int nbQuais);

    void reserverQuai(long station, long quai, long navette);

    void ajouterNavette(long station, long navette);

    void liberaiQuai(long station, long quai, long navette);
    
}
