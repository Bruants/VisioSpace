/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.exposition;

import fr.miage.spacelib.entities.Mecanicien;
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
    public long debutRevision(long navette, long idMecanicien) {
        try {
            gestionMecanicien.debutRevision(navette, idMecanicien);
            return 1;
        } catch (AucuneNavetteException ex) {
            Logger.getLogger(ExpoWebMecanicienLeg.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
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

    @Override
    public Mecanicien creerMecanicien(String nom, String prenom) {
        return gestionMecanicien.creerMecanicien(nom, prenom);
    }
    
    
}
