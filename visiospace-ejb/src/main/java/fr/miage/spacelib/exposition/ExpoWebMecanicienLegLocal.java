/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.exposition;

import fr.miage.spacelib.entities.Mecanicien;
import fr.miage.spacelib.vspaceshared.utilities.AucunMecanicienException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kevin
 */
@Local
public interface ExpoWebMecanicienLegLocal {
    long debutRevision(long navette, long idMecanicien);

    void clotureRevision(long navette);

    Mecanicien creerMecanicien(String nom, String prenom);

    Mecanicien connexion(long id, long idStation) throws AucunMecanicienException, AucuneStationException;//

    List<Long> navettesAReviser(long idStation);

    List<Long> navettesEnCoursDeRevision(long idStation);
}
