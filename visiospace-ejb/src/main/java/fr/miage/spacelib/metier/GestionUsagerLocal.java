/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Usager;
import fr.miage.spacelib.vspaceshared.utilities.AucunUsagerException;
import javax.ejb.Local;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@Local
public interface GestionUsagerLocal {

    Usager connecter(long idUtilisateur) throws AucunUsagerException;
    
    Usager inscrire(String nom, String prenom);
    
}
