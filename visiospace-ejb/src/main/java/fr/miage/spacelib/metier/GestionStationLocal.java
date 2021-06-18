/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Navette;
import fr.miage.spacelib.entities.Quai;
import fr.miage.spacelib.entities.Station;
import fr.miage.spacelib.vspaceshared.utilities.AucunQuaiException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import fr.miage.spacelib.vspaceshared.utilities.CoordonneesInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombreNavetteInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePlacesInvalideException;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AlexisVivier
 */
@Local
public interface GestionStationLocal {

    long creerStation(String coordonnees, List<Long> navettes) 
        throws NombreNavetteInvalideException, CoordonneesInvalideException ;
    
    Quai reserverQuai(long idStation, long navette, Date dateReservation) 
            throws AucuneStationException, AucuneNavetteException,
            AucunQuaiException;

    void arrimerNavette(long quai, long navette) throws AucunQuaiException, AucuneNavetteException;
    
    void arrimerNavette(long navette) throws AucuneNavetteException, AucunQuaiException;

    void libererQuai(long quai) throws AucunQuaiException;

    Navette navettesDispo(long idStation, int nbPlaces) 
            throws AucuneStationException, AucuneNavetteException;

    Quai quaiDisponible(long idStation, Date dateReservation) throws AucuneStationException, AucunQuaiException;
    
    List<Quai> quaisDisponible(long idStation, Date dateReservation) throws AucuneStationException, AucunQuaiException;

    List<Long> navettesAReviser(long idStation) throws AucuneStationException;
    
    public List<Station> toutesStations();

    Station trouverStation(long id);

    boolean stationExiste(long idStation);
    
}
