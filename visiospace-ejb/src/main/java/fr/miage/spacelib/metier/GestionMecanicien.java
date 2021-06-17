/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Mecanicien;
import fr.miage.spacelib.entities.Operation;
import fr.miage.spacelib.facades.MecanicienFacadeLocal;
import fr.miage.spacelib.facades.NavetteFacadeLocal;
import fr.miage.spacelib.facades.OperationFacadeLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucunMecanicienException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author AlexisVivier
 */
@Stateless
public class GestionMecanicien implements GestionMecanicienLocal {

    @EJB
    private NavetteFacadeLocal navetteFacade;

    @EJB
    private GestionNavetteLocal gestionNavette;

    @EJB
    private GestionStationLocal gestionStation;

    @EJB
    private OperationFacadeLocal operationFacade;

    @EJB
    private MecanicienFacadeLocal mecanicienFacade;
    
    /**
     * Crée une opération de révision
     * 
     * @param navette navette à réviser
     * @return l'id du quai ou se situe la navette à réviser
     * @throws AucuneNavetteException -> si l'identifiant n'existe pas
     */
    @Override
    public Long debutRevision(long navette, long idMecanicien) throws AucuneNavetteException {
        Operation operation = operationFacade.revisionNavette(navette, mecanicienFacade.find(idMecanicien));
        navetteFacade.ajouterOperation(navette, operation);
        return navetteFacade.find(navette).getStationeSur().getId();
    }

    /**
     * Termine une révision
     * 
     * @param navette navette révisée
     * @throws AucuneNavetteException -> si l'identifiant n'existe pas
     */
    @Override
    public void clotureRevision(long navette) throws AucuneNavetteException {
        operationFacade.terminerRevisionNavette(navette);
        navetteFacade.razNbOperationsDepuisDerniereRevision(navette);
    }

    @Override
    public Mecanicien creerMecanicien(String nom, String prenom) {
        mecanicienFacade.create(new Mecanicien(prenom, nom));
        return mecanicienFacade.findWithNames(nom, prenom);
    }

    @Override
    public Mecanicien connexion(long id, long idStation) throws AucunMecanicienException, AucuneStationException {
        Mecanicien mecanicien = mecanicienFacade.find(id);
        if(mecanicien == null) {
            throw new AucunMecanicienException(Long.toString(id));
        }
        if(gestionStation.stationExiste(idStation) == false) {
            throw new AucuneStationException();
        }
        return mecanicien;
    }

    @Override
    public List<Long> navettesAReviser(long idStation) {
        return gestionNavette.navettesAReviser(idStation);
    }

    @Override
    public List<Long> navettesEnCoursDeRevision(long idStation) {
        return gestionNavette.navettesEnCoursDeRevision(idStation);
    }
    
    
    
    
}
