/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.ws;

import fr.miage.spacelib.exposition.ExpoWebConducteurLocal;
import fr.miage.spacelib.exposition.ExpoWebUsagerLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucunQuaiException;
import fr.miage.spacelib.vspaceshared.utilities.AucunUsagerException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import fr.miage.spacelib.vspaceshared.utilities.DateInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePassagersInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePlacesInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.ReservationExport;
import fr.miage.spacelib.vspaceshared.utilities.WrapperForGetionChargeConducteur;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@WebService(serviceName = "WSConducteur")
public class WSConducteur {

    @EJB
    private ExpoWebUsagerLocal expoWebUsager;

    @EJB
    private ExpoWebConducteurLocal ejbRef;


    @WebMethod(operationName = "creerConducteur")
    public Long creerConducteur(@WebParam(name = "nom") String nom, @WebParam(name = "prenom") String prenom) {
        return ejbRef.creerConducteur(nom, prenom);
    }

    @WebMethod(operationName = "connexion")
    public boolean connexion(@WebParam(name = "idConducteur") long idConducteur) {
        return ejbRef.connexion(idConducteur);
    }

    @WebMethod(operationName = "reserverVoyage")
    public ReservationExport reserverVoyage(@WebParam(name = "idUsager") String idUsager, @WebParam(name = "nbPassagers") String nbPassagers, @WebParam(name = "dateDepart") String dateDepart, @WebParam(name = "dateArrivee") String dateArrivee, @WebParam(name = "stationDepart") String stationDepart, @WebParam(name = "stationArrivee") String stationArrivee) throws AucunQuaiException, AucuneStationException, AucuneNavetteException, NombrePlacesInvalideException, AucunUsagerException, DateInvalideException, NombrePassagersInvalideException {
        return expoWebUsager.reserverVoyage(Long.parseLong(idUsager), Integer.parseInt(nbPassagers), new Date(dateDepart), new Date(dateArrivee), Long.parseLong(stationDepart), Long.parseLong(stationArrivee));
    }

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "gestionCharge")
    public WrapperForGetionChargeConducteur gestionCharge() {
        HashMap<String,TreeMap<Long,Long>> results = ejbRef.transfertsAFaire();
        WrapperForGetionChargeConducteur wrapper = new WrapperForGetionChargeConducteur(results);
        return wrapper;
    }
}
