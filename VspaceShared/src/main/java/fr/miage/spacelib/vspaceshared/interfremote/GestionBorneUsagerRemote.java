/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.vspaceshared.interfremote;

import fr.miage.spacelib.vspaceshared.utilities.AucunQuaiException;
import fr.miage.spacelib.vspaceshared.utilities.AucunUsagerException;
import fr.miage.spacelib.vspaceshared.utilities.AucunVoyageException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import fr.miage.spacelib.vspaceshared.utilities.DateInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePassagersInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.ReservationExport;
import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author AlexisVivier
 */
@Remote
public interface GestionBorneUsagerRemote {
    ReservationExport reserverVoyage(long idUsager, int nbPassagers, Date dateDepart, Date dateArrivee, long stationDepart, long stationArrivee)
             throws AucunUsagerException, NombrePassagersInvalideException, DateInvalideException, AucuneStationException, AucunQuaiException, AucuneNavetteException ;

    void departVoyage(long idVoyage) throws AucunVoyageException, AucuneNavetteException, AucunQuaiException;

    void arriveeVoyage(long idVoyage)  throws AucunVoyageException, AucuneNavetteException, AucunQuaiException;
}
