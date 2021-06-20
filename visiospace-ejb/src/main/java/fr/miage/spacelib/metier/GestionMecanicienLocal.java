/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.vspaceshared.utilities.AucunMecanicienException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneOperationException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import fr.miage.spacelib.vspaceshared.utilities.NavetteIndisponibleException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@Local
public interface GestionMecanicienLocal {

    Long debutRevision(long navette, long idMecanicien) throws AucuneNavetteException, AucunMecanicienException, NavetteIndisponibleException;

    /**
     * Termine une révision
     * 
     * @param navette la navette en révision
     * @throws AucuneNavetteException -> si l'identifiant n'existe pas
     * @throws AucuneOperationException
     */
    void clotureRevision(long navette) throws AucuneNavetteException, AucuneOperationException;

    Long creerMecanicien(String nom, String prenom);

    Long connexion(long id, long idStation) throws AucunMecanicienException, AucuneStationException;

    List<Long> navettesAReviser(long idStation) throws AucuneStationException;

    List<Long> navettesEnCoursDeRevision(long idStation) throws AucuneStationException;
    
}
