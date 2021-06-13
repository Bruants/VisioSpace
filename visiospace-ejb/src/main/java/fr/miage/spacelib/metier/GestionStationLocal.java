/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Navette;
import fr.miage.spacelib.entities.Quai;
import fr.miage.spacelib.vspaceshared.utilities.AucunQuaiException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import fr.miage.spacelib.vspaceshared.utilities.CoordonneesInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePassagersInvalideException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AlexisVivier
 */
@Local
public interface GestionStationLocal {

    void creerStation(String coordonnees, List<Long> navettes) 
        throws AucuneNavetteException, CoordonneesInvalideException ;

    Quai reserverQuai(Quai quai, long navette) throws AucunQuaiException, 
            AucuneNavetteException;
    
    Quai reserverQuai(long idStation, long navette) 
            throws AucuneStationException, AucuneNavetteException,
            AucunQuaiException;

    void arrimerNavette(long quai, long navette) throws AucunQuaiException, AucuneNavetteException;
    
    void arrimerNavette(long navette) throws AucuneNavetteException;

    void libererQuai(long quai) throws AucunQuaiException;

    Navette navettesDispo(long idStation, int nbPlaces) 
            throws AucuneStationException, NombrePassagersInvalideException;

    Quai quaiDisponible(long idStation) throws AucuneStationException;

    List<Long> navettesAReviser(long idStation) throws AucuneStationException;
    
}
