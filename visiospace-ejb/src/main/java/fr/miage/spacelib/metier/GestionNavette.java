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
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Gestions des navettes traités dans le systéme
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
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
     * @throws fr.miage.spacelib.vspaceshared.utilities.NombrePlacesInvalideException
     * @throws fr.miage.spacelib.vspaceshared.utilities.AucuneStationException
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
        navette.setStationeSur(null);
        navetteFacade.edit(navette);
    }

    /**
     * @return La derniere navette qui a été créée
     */
    private Long derniereNavetteAjoutee() {
        return navetteFacade.derniereNavette();
    }

    /**
     * @param idStation La station où l'on cherche les navettes
     * @return Les navettes qui doivent être révisée
     */
    @Override
    public List<Long> navettesAReviser(long idStation) {
        return navetteFacade.sontAReviser(idStation);
    }

    /**
     * @param idStation La station où l'on cherche les navettes
     * @return Les navettes en révision
     */
    @Override
    public List<Long> navettesEnCoursDeRevision(long idStation) {
        return navetteFacade.sontEnRevision(idStation);
    }
    
    
    
}
