/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.ws;

import fr.miage.spacelib.exposition.ExpoWebMecanicienLegLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
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
    public long debutRevision(@WebParam(name = "navette") long navette) throws AucuneNavetteException {
        return expoWebMecanicienLeg.debutRevision(navette);
    }

    @WebMethod(operationName = "clotureRevision")
    public void clotureRevision(@WebParam(name = "navette") long navette) throws AucuneNavetteException {
        expoWebMecanicienLeg.clotureRevision(navette);
    }
    
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
}
