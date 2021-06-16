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
import fr.miage.spacelib.vspaceshared.utilities.NombrePlacesInvalideException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 * Gestions des navettes traités dans le systéme
 * @author AlexisVivier
 */
@Stateless
public class GestionNavette implements GestionNavetteLocal {

    @EJB
    private GestionStationLocal gestionStation;

    @EJB
    private NavetteFacadeLocal navetteFacade;

    /**
     * Créer une nouvelle navette
     * @param nbPlaces Nombre de place de la navette
     * @param idStation Identifiant de la station ou est stationné la navette
     */
    @Override
    public Long creerNavette(int nbPlaces) throws NombrePlacesInvalideException, AucuneStationException {
        
        if (nbPlaces != 2 && nbPlaces != 5 && nbPlaces != 10 && nbPlaces != 15) {
            throw new NombrePlacesInvalideException();
        }
        
        Navette navette = new Navette(nbPlaces);
        
        navetteFacade.create(navette);
        return this.derniereNavetteAjoutee();
    }

    /**
     * Donne l'état actuelle de la navette
     * @param identifiant identifiant de la navette 
     * @return  true : aucun entretien de prévu
     *          false : en attente d'entretien
     */
    @Override
    public boolean etatNavettePourRevision(long identifiant) throws AucuneNavetteException {
        
        if (navetteFacade.find(identifiant) == null) {
            throw new AucuneNavetteException();
        }
        
        return navetteFacade.estDisponiblePourRevision(identifiant);
    }
    
        /**
     * Donne l'état actuelle de la navette
     * @param identifiant identifiant de la navette 
     * @return  true : aucun entretien de prévu
     *          false : en attente d'entretien
     */
    @Override
    public boolean etatNavettePourVoyage(long identifiant) throws AucuneNavetteException {
        
        if (navetteFacade.find(identifiant) == null) {
            throw new AucuneNavetteException();
        }
       return navetteFacade.estDisponiblePourVoyage(identifiant);
        
    }

    /**
     * Recupére le quai d'une navette
     * @param id d'une navette
     * @return quai ou la navette est arrimé
     */
    @Override
        public Quai quai(long id) throws AucuneNavetteException {
        
            if (navetteFacade.find(id) == null) {
                throw new AucuneNavetteException();
            }
        
        return null;
    }

    /**
     * La navette démarre son trajet
     * @param id identifiant de la navette à lancer
     * @throws AucuneNavetteException  -> l'id ne correspond à aucune navette existante
     * @throws AucunQuaiException -> Le quai à libérer n'existe pas
     */
    @Override
    public void lancerNavette(long id) throws AucuneNavetteException, AucunQuaiException {
        Navette navette = navetteFacade.find(id);
        
        if (navette == null) {
            throw new AucuneNavetteException();
        }
        gestionStation.libererQuai(navette.getStationeSur().getId());
    }

    /**
     * La navette arrive à destination
     * @param id identifiant de la navette
     * @throws AucuneNavetteException  -> l'id ne correspond à aucune navette existante
     * @throws AucunQuaiException -> Le quai à libérer n'existe pas
     */
    @Override
    public void arriveeNavette(long id) throws AucuneNavetteException, AucunQuaiException {
        Navette navette = navetteFacade.find(id);
        
        if (navette == null) {
            throw new AucuneNavetteException();
        }
        navette.addCompteurVoyage();
        gestionStation.arrimerNavette(id);
    }

    /**
     *
     * @return
     */
    @Override
    public Long derniereNavetteAjoutee() {
        return navetteFacade.derniereNavette();
    }
}
