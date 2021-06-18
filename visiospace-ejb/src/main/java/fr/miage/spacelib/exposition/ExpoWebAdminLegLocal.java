/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.exposition;

import fr.miage.spacelib.entities.Administrateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kevin
 */
@Local
public interface ExpoWebAdminLegLocal {

    boolean creerStation(String coordonnees, List<Long> navettes);

    Long creerAdministrateur(String nom, String prenom);
    
    
  
}
