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
import fr.miage.spacelib.vspaceshared.utilities.NombrePlacesInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.ReservationExport;
import fr.miage.spacelib.vspaceshared.utilities.StationExport;
import fr.miage.spacelib.vspaceshared.utilities.UsagerExport;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@Remote
public interface ExpoGestionBorneRemote {
    ReservationExport reserverVoyage(long idUsager, int nbPassagers, Date dateDepart, Date dateArrivee, long stationDepart, long stationArrivee)
             throws AucunUsagerException, NombrePlacesInvalideException, DateInvalideException, AucuneStationException, AucunQuaiException, AucuneNavetteException,NombrePassagersInvalideException ;

    void departVoyage(long idReservation) throws AucunVoyageException, AucuneNavetteException, AucunQuaiException;

    void arriveeVoyage(long idReservation)  throws AucunVoyageException, AucuneNavetteException, AucunQuaiException;
    
    UsagerExport connecter(long idUtilisateur) throws AucunUsagerException;
    
    UsagerExport inscrire(String nom, String prenom);
    
    public List<StationExport> toutesStations();
    
    public ReservationExport reservationEnCours(long idUtilisateur) throws AucunVoyageException;
    
    public boolean isReservationArrivee(long idUtilisateur) throws AucunVoyageException;
    
    public void testNul(String chaine);
}
