/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.ws;

import fr.miage.spacelib.exposition.ExpoWebMecanicienLegLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@WebService(serviceName = "WSMecanicien")
@Stateless()
public class WSMecanicien {

    @EJB
    private ExpoWebMecanicienLegLocal expoWebMecanicienLeg;
    
    @WebMethod(operationName = "debutRevision")
    public long debutRevision(@WebParam(name = "navette") String navette, @WebParam(name = "idMecanicien") String idMecanicien) {
        Long idn = Long.parseLong(navette);
        Long idm = Long.parseLong(idMecanicien);
        return expoWebMecanicienLeg.debutRevision(idn, idm);
    }

    @WebMethod(operationName = "clotureRevision")
    public boolean clotureRevision(@WebParam(name = "navette") String navette) {
        Long idn = Long.parseLong(navette);
        return expoWebMecanicienLeg.clotureRevision(idn);
    }

    @WebMethod(operationName = "creerMecanicien")
    public Long creerMecanicien(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom) {
        return expoWebMecanicienLeg.creerMecanicien(nom, prenom);
    }

    @WebMethod(operationName = "connexionMecanicien")
    public boolean connexionMecanicien(@WebParam(name = "idMecanicien") String idMecanicien, @WebParam(name = "idStation") String idStation) {
        Long idm = Long.parseLong(idMecanicien);
        Long ids = Long.parseLong(idStation);
       return expoWebMecanicienLeg.connexion(idm, ids);
    }

    @WebMethod(operationName = "navettesAReviser")
    public List<Long> navettesAReviser(@WebParam(name = "idStation") String idStation) {
        Long ids = Long.parseLong(idStation);
        return expoWebMecanicienLeg.navettesAReviser(ids);
    }
    
    @WebMethod(operationName = "navettesEnCoursDeRevision")
    public List<Long> navettesEnCoursDeRevision(@WebParam(name = "idStation") String idStation) {
        Long ids = Long.parseLong(idStation);
        return expoWebMecanicienLeg.navettesEnCoursDeRevision(ids);
    }
}
