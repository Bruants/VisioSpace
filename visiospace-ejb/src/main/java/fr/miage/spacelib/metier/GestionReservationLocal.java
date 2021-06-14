/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Reservation;
import fr.miage.spacelib.entities.Station;
import fr.miage.spacelib.vspaceshared.utilities.AucunQuaiException;
import fr.miage.spacelib.vspaceshared.utilities.AucunVoyageException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePassagersInvalideException;
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
            throws AucunQuaiException, AucuneStationException, 
            NombrePassagersInvalideException, AucuneNavetteException;

    void departVoyage(long idVoyage)
            throws AucuneNavetteException, AucunVoyageException, 
            AucunQuaiException;

    void arriveeVoyage(long idVoyage) throws AucunVoyageException;
    
    public List<Station> toutesStations();
    
}
