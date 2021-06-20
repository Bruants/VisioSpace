/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.vspaceshared.utilities.AucunConducteurException;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.ejb.Local;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@Local
public interface GestionConducteurLocal {

    Long creerConducteur(String nom, String prenom);

    Long connexion(long idConducteur) throws AucunConducteurException;

    HashMap<String,TreeMap<Long,Long>> ratioQuaisNavettesDisponibles();

    Long ratioQuaisNavettesDisponiblesParStation(long idStation);
    
}
