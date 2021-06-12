/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Navette;
import fr.miage.spacelib.entities.Quai;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AlexisVivier
 */
@Local
public interface GestionStationLocal {

    void creerStation(String coordonnees, List<Long> navettes);

    Quai reserverQuai(Quai quai, long navette);
    
    Quai reserverQuai(long idStation, long navette);

    void arrimerNavette(long quai, long navette);
    
    void arrimerNavette(long navette);

    void liberaiQuai(long quai);

    Navette navettesDispo(long idStation, int nbPlaces);

    Quai quaiDisponible(long idStation);

    List<Long> navettesAReviser(long idStation);
    
}
