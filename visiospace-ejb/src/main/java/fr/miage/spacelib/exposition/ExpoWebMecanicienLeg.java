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
import fr.miage.spacelib.vspaceshared.utilities.AucuneOperationException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import fr.miage.spacelib.vspaceshared.utilities.NavetteIndisponibleException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@Stateless
public class ExpoWebMecanicienLeg implements ExpoWebMecanicienLegLocal {

    @EJB
    private GestionMecanicienLocal gestionMecanicien;
    
    @Override
    public long debutRevision(long navette, long idMecanicien) {
        try {
            return gestionMecanicien.debutRevision(navette, idMecanicien);
        } catch (AucuneNavetteException | AucunMecanicienException | NavetteIndisponibleException ex) {
            return -1;
        }
    }
  
    @Override
    public boolean clotureRevision(long navette) {
        try {
            gestionMecanicien.clotureRevision(navette);
            return true;
        } catch (AucuneNavetteException | AucuneOperationException ex) {
            return false;
        }
    }

    @Override
    public Long creerMecanicien(String nom, String prenom) {
        return gestionMecanicien.creerMecanicien(nom, prenom);
    }

    @Override
    public boolean connexion(long id, long idStation) {
        try {
            gestionMecanicien.connexion(id, idStation);
            return true;
        } catch (AucunMecanicienException | AucuneStationException ex) {
            return false;
        }
    }

    @Override
    public List<Long> navettesAReviser(long idStation) {
        try {
            return gestionMecanicien.navettesAReviser(idStation);
        } catch (AucuneStationException ex) {
            ArrayList<Long> resultats = new ArrayList<>();
            resultats.add(-1L);
            return resultats;
        }
    }

    @Override
    public List<Long> navettesEnCoursDeRevision(long idStation) {
        try {
            return gestionMecanicien.navettesEnCoursDeRevision(idStation);
        } catch (AucuneStationException ex) {
            List<Long> resultats = new ArrayList<>();
            resultats.add(-1L);
            return resultats;
        }
    }
}
