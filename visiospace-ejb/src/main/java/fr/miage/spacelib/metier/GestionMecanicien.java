/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Mecanicien;
import fr.miage.spacelib.entities.Navette;
import fr.miage.spacelib.entities.Operation;
import fr.miage.spacelib.facades.MecanicienFacadeLocal;
import fr.miage.spacelib.facades.NavetteFacadeLocal;
import fr.miage.spacelib.facades.OperationFacadeLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucunMecanicienException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneOperationException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import fr.miage.spacelib.vspaceshared.utilities.NavetteIndisponibleException;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
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
     * @param navetteId La navette a reviser
     * @param idMecanicien Le mécanicien qui fait la révision
     * @return l'id du quai ou se situe la navette à réviser
     * @throws AucuneNavetteException -> si l'identifiant n'existe pas
     * @throws fr.miage.spacelib.vspaceshared.utilities.AucunMecanicienException
     * @throws fr.miage.spacelib.vspaceshared.utilities.NavetteIndisponibleException
     */
    @Override
    public Long debutRevision(long navetteId, long idMecanicien) throws AucuneNavetteException, AucunMecanicienException, NavetteIndisponibleException {
        Mecanicien mecanicien = mecanicienFacade.find(idMecanicien);
        if(mecanicien == null) {
            throw new AucunMecanicienException(Objects.toString(idMecanicien));
        }
        Navette navette = navetteFacade.find(navetteId);
        if(navette == null) {
            throw new AucuneNavetteException(Objects.toString(idMecanicien));
        }
        if(navette.getDerniereOperation() != null && !navette.getDerniereOperation().isTerminee()) {
            throw new NavetteIndisponibleException(Objects.toString(navetteId));
        }
        Operation operation = operationFacade.revisionNavette(navetteId, mecanicien);
        navetteFacade.ajouterOperation(navetteId, operation);
        return navetteFacade.find(navetteId).getStationeSur().getId();
    }

    /**
     * Termine une révision
     * 
     * @param navette la navette en révision
     * @throws AucuneNavetteException -> si l'identifiant n'existe pas
     * @throws AucuneOperationException 
     */
    @Override
    public void clotureRevision(long navette) throws AucuneNavetteException, AucuneOperationException  {
        operationFacade.terminerRevisionNavette(navette);
        navetteFacade.razNbOperationsDepuisDerniereRevision(navette);
    }

    /**
     * Créer un mecanicien
     * @param nom -> nom du mécanicien
     * @param prenom -> prénom du mécanicien
     * @return Le mecanicien a creer
     */
    @Override
    public Long creerMecanicien(String nom, String prenom) {
        mecanicienFacade.create(new Mecanicien(prenom, nom));
        return mecanicienFacade.findWithNames(nom, prenom).getId();
    }

    /**
     * Teste la connexion d'un conducteur
     * TODO : Token ou Variable Session
     * @param id L'id du mecanicien
     * @param idStation Sa station de rattachement
     * @throws AucunMecanicienException -> le mécanicien n'existe pas
     * @throws AucuneStationException -> la station n'existe pas
     */
    @Override
    public Long connexion(long id, long idStation) throws AucunMecanicienException, AucuneStationException {
        Mecanicien mecanicien = mecanicienFacade.find(id);
        if(mecanicien == null) {
            throw new AucunMecanicienException(Long.toString(id));
        }
        if(gestionStation.stationExiste(idStation) == false) {
            throw new AucuneStationException();
        }
        return mecanicien.getId();
    }

    /**
     * Les navettes qui ont 3 voyages ou plus a leur actif sans révision
     * @param idStation La station sur laquelle le mecanicien est rattaché
     * @return Les navettes a réviser
     * @throws AucuneStationException 
     */
    @Override
    public List<Long> navettesAReviser(long idStation) throws AucuneStationException {
        if(!gestionStation.stationExiste(idStation)) {
            throw new AucuneStationException(Objects.toString(idStation));
        }
        return gestionNavette.navettesAReviser(idStation);
    }

    /**
     * @param idStation La station sur laquelle le mecanicien est rattaché
     * @return Les navettes en cours de révisions dans la station
     * @throws AucuneStationException -> la station n'existe pas
     */
    @Override
    public List<Long> navettesEnCoursDeRevision(long idStation) throws AucuneStationException {
        if(!gestionStation.stationExiste(idStation)) {
            throw new AucuneStationException(Objects.toString(idStation));
        }
        return gestionNavette.navettesEnCoursDeRevision(idStation);
    }

}
