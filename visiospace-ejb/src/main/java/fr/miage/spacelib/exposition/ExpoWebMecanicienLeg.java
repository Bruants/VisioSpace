/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.exposition;

import fr.miage.spacelib.metier.GestionMecanicienLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Kevin
 */
@Stateless
public class ExpoWebMecanicienLeg implements ExpoWebMecanicienLegLocal {

    @EJB
    private GestionMecanicienLocal gestionMecanicien;

    
    
    @Override
    public long debutRevision(long navette) {
        try {
            return gestionMecanicien.debutRevision(navette);
        } catch (AucuneNavetteException ex) {
            Logger.getLogger(ExpoWebMecanicienLeg.class.getName()).log(Level.SEVERE, null, ex);
            return -1L;
        }
    }
  
    @Override
    public void clotureRevision(long navette) {
        try {
            gestionMecanicien.clotureRevision(navette);
        } catch (AucuneNavetteException ex) {
            Logger.getLogger(ExpoWebMecanicienLeg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
