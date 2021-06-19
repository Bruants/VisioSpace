/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.exposition;

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
import javax.ejb.Local;

/**
 *
 * @author AlexisVivier
 */
@Local
public interface ExpoWebUsagerLocal {

    List<StationExport> carteSpacelib();

    ReservationExport reserverVoyage(long idUsager, int nbPassagers, Date dateDepart, Date dateArrivee, long stationDepart, long stationArrivee) 
            throws AucunQuaiException, AucuneStationException, 
            AucuneNavetteException, NombrePlacesInvalideException, 
            AucunUsagerException, DateInvalideException, 
            NombrePassagersInvalideException;

    void annulerReservation(long idUsager, long idReservation) throws AucunUsagerException, AucunVoyageException, VoyageDejaCommenceException;
    
}
