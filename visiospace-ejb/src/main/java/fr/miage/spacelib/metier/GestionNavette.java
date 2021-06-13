/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Navette;
import fr.miage.spacelib.entities.Quai;
import fr.miage.spacelib.facades.NavetteFacadeLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucunQuaiException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePassagersInvalideException;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Gestions des navettes traités dans le systéme
 * @author AlexisVivier
 */
@Stateless
public class GestionNavette implements GestionNavetteLocal {
    
    @EJB
    private NavetteFacadeLocal navetteFacade;
    
    private GestionStationLocal gestionStation;

    /**
     * Créer une nouvelle navette
     * @param nbPlaces Nombre de place de la navette
     * @param idStation Identifiant de la station ou est stationné la navette
     */
    @Override
    public void creerNavette(int nbPlaces, long idStation) throws NombrePassagersInvalideException, AucuneStationException {
    }

    /**
     * Donne l'état actuelle de la navette
     * @param identifiant identifiant de la navette 
     * @return  true : aucun entretien de prévu
     *          false : en attente d'entretien
     */
    @Override
    public boolean etatNavette(long identifiant) throws AucuneStationException {
        return false;
    }

    /**
     * Recupére le quai d'une navette
     * @param id d'une navette
     * @return quai ou la navette est arrimé
     */
    @Override
    public Quai quai(long id) throws AucunQuaiException {
        return null;
    }

    @Override
    public void lancerNavette(long id) throws AucuneNavetteException, AucunQuaiException {
        Navette navette = navetteFacade.find(id);
        gestionStation.libererQuai(navette.getStationeSur().getId());
    }

    @Override
    public void arriveeNavette(long id) throws AucuneNavetteException {
        Navette navette = navetteFacade.find(id);
        navette.addCompteurVoyage();
        gestionStation.arrimerNavette(id);
    }
    
    
    
    

}
