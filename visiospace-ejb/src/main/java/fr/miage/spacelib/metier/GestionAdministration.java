/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Administrateur;
import fr.miage.spacelib.entities.Station;
import fr.miage.spacelib.facades.AdministrateurFacadeLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import fr.miage.spacelib.vspaceshared.utilities.CoordonneesInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombreNavetteInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePlacesInvalideException;
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
        try {
            for(Station elt : gestionStation.toutesStations()) {
                if(elt.getCoordonnee().equals(coordonnees)) {
                    throw new CoordonneesInvalideException();
                }
            }
            List<Long> idNavettes = new ArrayList<>();
            for(int i = 0 ; i < navettes.size() ; i++) {
                idNavettes.add(this.creerNavette(navettes.get(i)));
            }
            gestionStation.creerStation(coordonnees, idNavettes);
        } catch (AucuneStationException | NombrePlacesInvalideException ex) {
            Logger.getLogger(GestionAdministration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Créer une nouvelle navette
     * @param nbPlaces le nombre de places de la navette
     * @return L'id de la navette
     * @throws AucuneStationException
     * @throws NombrePlacesInvalideException 
     */
    private Long creerNavette(long nbPlaces) throws AucuneStationException, NombrePlacesInvalideException {
        return gestionNavette.creerNavette((int)nbPlaces);
    }

    /**
     * Créer un nouvel administrateur
     * @param nom -> nom de l'administrateur
     * @param prenom -> prénom de l'administrateur
     * @return L'id de l'administrateur
     */
    @Override
    public Long creerAdministrateur(String nom, String prenom) {
        administrateurFacade.create(new Administrateur(prenom, nom));
        return administrateurFacade.findWithNames(nom, prenom).getId();
    }
    
    
}
