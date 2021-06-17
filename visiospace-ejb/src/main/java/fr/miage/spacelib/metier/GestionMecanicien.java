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
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.Query;

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
    public void debutRevision(long navette, long idMecanicien) throws AucuneNavetteException {
        Operation operation = operationFacade.revisionNavette(navette, mecanicienFacade.find(idMecanicien));
        navetteFacade.ajouterOperation(navette, operation);
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
    }

    @Override
    public Mecanicien creerMecanicien(String nom, String prenom) {
        mecanicienFacade.create(new Mecanicien(prenom, nom));
        return mecanicienFacade.findWithNames(nom, prenom);
    }
    
    
    
    
    
}
