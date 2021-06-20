/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.exposition;

import fr.miage.spacelib.metier.GestionConducteurLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucunConducteurException;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@Stateless
public class ExpoWebConducteur implements ExpoWebConducteurLocal {

    @EJB
    private GestionConducteurLocal gestionConducteur;

    
    
    @Override
    public Long creerConducteur(String nom, String prenom) {
        return gestionConducteur.creerConducteur(nom, prenom);
    }
    
    @Override
    public boolean connexion(long idConducteur) {
        try {
            gestionConducteur.connexion(idConducteur);
            return true;
        } catch (AucunConducteurException ex) {
            return false;
        }
    }
}
