/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.facades.AdministrateurFacadeLocal;
import fr.miage.spacelib.vspaceshared.utilities.CoordonneesInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombreNavetteInvalideException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author AlexisVivier
 */
@Stateless
public class GestionAdministration implements GestionAdministrationLocal {

    @EJB(beanName = "AdministrateurEJB")
    private AdministrateurFacadeLocal administrateur;
    
    @EJB(beanName = "GestionStationAdministrateurEJB")
    private GestionStationLocal gestionStation;
    
    @EJB(beanName = "GestionNavetteAdministrateurEJB")
    private GestionNavetteLocal gestionNavette;

    /**
     * Get the value of gestionNavette
     *
     * @return the value of gestionNavette
     */
    public GestionNavetteLocal getGestionNavette() {
        return gestionNavette;
    }

    /**
     * Set the value of gestionNavette
     *
     * @param gestionNavette new value of gestionNavette
     */
    public void setGestionNavette(GestionNavetteLocal gestionNavette) {
        this.gestionNavette = gestionNavette;
    }


    /**
     * Get the value of gestionStation
     *
     * @return the value of gestionStation
     */
    public GestionStationLocal getGestionStation() {
        return gestionStation;
    }

    /**
     * Set the value of gestionStation
     *
     * @param gestionStation new value of gestionStation
     */
    public void setGestionStation(GestionStationLocal gestionStation) {
        this.gestionStation = gestionStation;
    }


    /**
     * Get the value of administrateur
     *
     * @return the value of administrateur
     */
    public AdministrateurFacadeLocal getAdministrateur() {
        return administrateur;
    }

    /**
     * Set the value of administrateur
     *
     * @param administrateur new value of administrateur
     */
    public void setAdministrateur(AdministrateurFacadeLocal administrateur) {
        this.administrateur = administrateur;
    }
    
    /**
     * Crée une nouvelle station
     * @param coordonnees coordonnées de la station à ajouter
     * @param navettes liste des navettes à ajouter dans la nouvelle station
     * @throws CoordonneesInvalideException 
     *          -> les coordonnées sont invalides
     *          -> une station est déjà présente aux coordonnées indiquées
     * @throws NombreNavetteInvalideException
     *          -> le nombre de navette doit être supérieur à zéro
     */
    @Override
    public void creerStation(String coordonnees, List<Long> navettes) 
            throws CoordonneesInvalideException, NombreNavetteInvalideException {
        
        gestionStation.creerStation(coordonnees, navettes);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
