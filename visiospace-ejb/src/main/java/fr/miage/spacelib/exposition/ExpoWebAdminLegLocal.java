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
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@Local
public interface ExpoWebAdminLegLocal {

    /**
     * Crée une nouvelle station
     * @param coordonnees coordonnées de la station à ajouter
     * @param navettes liste des navettes à ajouter dans la nouvelle station
     * @return 
     */
    boolean creerStation(String coordonnees, List<Long> navettes);

    /**
     * Créer un nouvel administrateur
     * @param nom -> nom de l'administrateur
     * @param prenom -> prénom de l'administrateur
     * @return L'id de l'administrateur
     */
    Long creerAdministrateur(String nom, String prenom);
    
    
  
}
