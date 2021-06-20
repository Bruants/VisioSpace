/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.exposition;

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
public interface ExpoWebConducteurLocal {

    Long creerConducteur(String nom, String prenom);

    boolean connexion(long idConducteur);

    HashMap<String,TreeMap<Long,Long>> transfertsAFaire();
    
}
