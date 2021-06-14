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

    @EJB
    private GestionNavetteLocal gestionNavette;

    @EJB
    private GestionStationLocal gestionStation;

    @EJB
    private AdministrateurFacadeLocal administrateurFacade;
    
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
