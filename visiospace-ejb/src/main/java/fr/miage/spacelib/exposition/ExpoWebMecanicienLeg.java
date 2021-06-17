/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.exposition;

import fr.miage.spacelib.entities.Mecanicien;
import fr.miage.spacelib.metier.GestionMecanicienLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucunMecanicienException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import java.util.List;
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
            return gestionMecanicien.debutRevision(navette, idMecanicien);
        } catch (AucuneNavetteException ex) {
            Logger.getLogger(ExpoWebMecanicienLeg.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
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

    @Override
    public Mecanicien connexion(long id, long idStation) throws AucunMecanicienException, AucuneStationException { //
        return gestionMecanicien.connexion(id, idStation);
    }

    @Override
    public List<Long> navettesAReviser(long idStation) {
        return gestionMecanicien.navettesAReviser(idStation);
    }

    @Override
    public List<Long> navettesEnCoursDeRevision(long idStation) {
        return gestionMecanicien.navettesEnCoursDeRevision(idStation);
    }
}
