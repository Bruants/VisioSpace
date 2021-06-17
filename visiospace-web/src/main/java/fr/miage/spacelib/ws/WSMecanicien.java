/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.ws;

import fr.miage.spacelib.entities.Mecanicien;
import fr.miage.spacelib.exposition.ExpoWebMecanicienLegLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucunMecanicienException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author AlexisVivier
 */
@WebService(serviceName = "WSMecanicien")
@Stateless()
public class WSMecanicien {

    @EJB
    private ExpoWebMecanicienLegLocal expoWebMecanicienLeg;
    
    @WebMethod(operationName = "debutRevision")
    public long debutRevision(@WebParam(name = "navette") long navette, @WebParam(name = "idMecanicien") long idMecanicien) throws AucuneNavetteException {
        return expoWebMecanicienLeg.debutRevision(navette, idMecanicien);
    }

    @WebMethod(operationName = "clotureRevision")
    public void clotureRevision(@WebParam(name = "navette") long navette) throws AucuneNavetteException {
        expoWebMecanicienLeg.clotureRevision(navette);
    }
    
    @WebMethod(operationName = "creerMecanicien")
    public Mecanicien creerMecanicien(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom) {
        return expoWebMecanicienLeg.creerMecanicien(nom, prenom);
    }
    
    @WebMethod(operationName = "connexionMecanicien")
    public boolean connexionMecanicien(@WebParam(name = "id") long id, @WebParam(name = "idStation") long idStation) {
        try {
            expoWebMecanicienLeg.connexion(id, idStation);
            return true;//
        } catch (AucunMecanicienException | AucuneStationException e) {
            return false;
        }
    }

    @WebMethod(operationName = "navettesAReviser")
    public List<Long> navettesAReviser(@WebParam(name = "idStation") long idStation) {
        return expoWebMecanicienLeg.navettesAReviser(idStation);
    }
    
    @WebMethod(operationName = "navettesEnCoursDeRevision")
    public List<Long> navettesEnCoursDeRevision(@WebParam(name = "idStation") long idStation) {
        return expoWebMecanicienLeg.navettesEnCoursDeRevision(idStation);
    }
    
    

}
