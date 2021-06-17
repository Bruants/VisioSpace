/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.ws;

import fr.miage.spacelib.entities.Administrateur;
import fr.miage.spacelib.exposition.ExpoWebAdminLegLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Kevin
 */
@WebService(serviceName = "WSAdministrateur")
public class WSAdministrateur {

    @EJB
    private ExpoWebAdminLegLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "creerStation")
    public boolean creerStation(@WebParam(name = "coordonnees") String coordonnees, @WebParam(name = "navettes") List<Long> navettes) {
        return ejbRef.creerStation(coordonnees, navettes);
    }

    @WebMethod(operationName = "creerAdministrateur")
    public Administrateur creerAdministrateur(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom) {
        return ejbRef.creerAdministrateur(nom, prenom);
    }
    
}
