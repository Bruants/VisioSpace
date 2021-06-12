/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AlexisVivier
 */
@Local
public interface GestionStationLocal {

    void creerStation(String coordonnees, List<Long> navettes);

    long reserverQuai(long quai, long navette);
    
    long reserverQuai(long navette);

    void arrimerNavette(long quai, long navette);

    void liberaiQuai(long quai);

    long navettesDispo(int nbPlaces);

    long quaiDisponible();

    List<Long> navettesAReviser(long idStation);
    
}
