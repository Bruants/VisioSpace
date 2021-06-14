/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Reservation;
import fr.miage.spacelib.entities.Station;
import fr.miage.spacelib.vspaceshared.utilities.AucunQuaiException;
import fr.miage.spacelib.vspaceshared.utilities.AucunUsagerException;
import fr.miage.spacelib.vspaceshared.utilities.AucunVoyageException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import fr.miage.spacelib.vspaceshared.utilities.DateInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePassagersInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePlacesInvalideException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AlexisVivier
 */
@Local
public interface GestionReservationLocal {

    Reservation reserverVoyage(long idUsager, int nbPassagers, Date dateDepart, 
            Date dateArrivee, long stationDepart, long stationArrivee)
            throws 
            AucunQuaiException, AucuneStationException, 
            AucuneNavetteException, NombrePlacesInvalideException, 
            AucunUsagerException, DateInvalideException, 
            NombrePassagersInvalideException;

    void departVoyage(long idReservation)
            throws AucuneNavetteException, AucunVoyageException, 
            AucunQuaiException;

    void arriveeVoyage(long idReservation) throws AucunVoyageException;
    
    public List<Station> toutesStations();

    Reservation lastReservation(long idUsager) throws AucunVoyageException ;

    Reservation trouver(long idReservation);
    
}
