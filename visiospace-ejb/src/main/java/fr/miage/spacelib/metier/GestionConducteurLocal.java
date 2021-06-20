/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.vspaceshared.utilities.AucunConducteurException;
import javax.ejb.Local;

/**
 *
 * @author Kevin
 */
@Local
public interface GestionConducteurLocal {

    Long creerConducteur(String nom, String prenom);

    Long connexion(long idConducteur) throws AucunConducteurException;
    
}
