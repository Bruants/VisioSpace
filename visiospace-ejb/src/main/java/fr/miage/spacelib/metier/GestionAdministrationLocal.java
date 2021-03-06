/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.vspaceshared.utilities.CoordonneesInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombreNavetteInvalideException;
import java.util.List;
import javax.ejb.Local;

/**
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@Local
public interface GestionAdministrationLocal {

    /**
     * Crée une nouvelle station
     * @param coordonnees coordonnées de la station à ajouter
     * @param navettes liste des navettes à ajouter dans la nouvelle station
     * @throws CoordonneesInvalideException
     * @throws NombreNavetteInvalideException
     */
    void creerStation(String coordonnees, List<Long> navettes) 
            throws CoordonneesInvalideException, NombreNavetteInvalideException ;    

    /**
     * Créer un nouvel administrateur
     * @param nom -> nom de l'administrateur
     * @param prenom -> prénom de l'administrateur
     * @return L'id de l'administrateur
     */
    Long creerAdministrateur(String nom, String prenom);
}
