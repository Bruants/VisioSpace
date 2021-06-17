/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Mecanicien;
import fr.miage.spacelib.vspaceshared.utilities.AucunMecanicienException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AlexisVivier
 */
@Local
public interface GestionMecanicienLocal {

    Long debutRevision(long navette, long idMecanicien) throws AucuneNavetteException;

    void clotureRevision(long navette) throws AucuneNavetteException;

    Mecanicien creerMecanicien(String nom, String prenom);

    Mecanicien connexion(long id, long idStation) throws AucunMecanicienException, AucuneStationException;

    List<Long> navettesAReviser(long idStation);

    List<Long> navettesEnCoursDeRevision(long idStation);
    
}
