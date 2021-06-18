/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.exposition;

import fr.miage.spacelib.entities.Administrateur;
import fr.miage.spacelib.metier.GestionAdministrationLocal;
import fr.miage.spacelib.vspaceshared.utilities.CoordonneesInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombreNavetteInvalideException;
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
public class ExpoWebAdminLeg implements ExpoWebAdminLegLocal {

    @EJB
    private GestionAdministrationLocal gestionAdministration;

    @Override
    public boolean creerStation(String coordonnees, List<Long> navettes) {
        try {
            gestionAdministration.creerStation(coordonnees, navettes);
        } catch (CoordonneesInvalideException | NombreNavetteInvalideException ex) {
            return false;
        }
        return true;
    }

    @Override
    public Long creerAdministrateur(String nom, String prenom) {
        return gestionAdministration.creerAdministrateur(nom, prenom);
    }

    
}
