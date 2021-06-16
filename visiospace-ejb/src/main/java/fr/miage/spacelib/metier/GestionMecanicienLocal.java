/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import javax.ejb.Local;

/**
 *
 * @author AlexisVivier
 */
@Local
public interface GestionMecanicienLocal {

    long debutRevision(long navette) throws AucuneNavetteException;

    void clotureRevision(long navette) throws AucuneNavetteException;
    
}
