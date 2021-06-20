/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.ws;

import fr.miage.spacelib.exposition.ExpoWebUsagerLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucunQuaiException;
import fr.miage.spacelib.vspaceshared.utilities.AucunUsagerException;
import fr.miage.spacelib.vspaceshared.utilities.AucunVoyageException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import fr.miage.spacelib.vspaceshared.utilities.DateInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePassagersInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePlacesInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.ReservationExport;
import fr.miage.spacelib.vspaceshared.utilities.StationExport;
import fr.miage.spacelib.vspaceshared.utilities.VoyageDejaCommenceException;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@WebService(serviceName = "WSUsager")
public class WSUsager {

    @EJB
    private ExpoWebUsagerLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Web Service > Add Operation"

    @WebMethod(operationName = "carteSpacelib")
    public List<StationExport> carteSpacelib() {
        return ejbRef.carteSpacelib();
    }

    @WebMethod(operationName = "reserverVoyage")
    public ReservationExport reserverVoyage(@WebParam(name = "idUsager") String idUsager, @WebParam(name = "nbPassagers") String nbPassagers, @WebParam(name = "dateDepart") String dateDepart, @WebParam(name = "dateArrivee") String dateArrivee, @WebParam(name = "stationDepart") String stationDepart, @WebParam(name = "stationArrivee") String stationArrivee) throws AucunQuaiException, AucuneStationException, AucuneNavetteException, NombrePlacesInvalideException, AucunUsagerException, DateInvalideException, NombrePassagersInvalideException {
        return ejbRef.reserverVoyage(Long.parseLong(idUsager), Integer.parseInt(nbPassagers), new Date(dateDepart), new Date(dateArrivee), Long.parseLong(stationDepart), Long.parseLong(stationArrivee));
    }

    @WebMethod(operationName = "annulerReservation")
    public void annulerReservation(@WebParam(name = "idUsager") String idUsager, @WebParam(name = "idReservation") String idReservation) throws AucunUsagerException, AucunVoyageException, VoyageDejaCommenceException {
        ejbRef.annulerReservation(Long.parseLong(idUsager), Long.parseLong(idReservation));
    }
    
}
