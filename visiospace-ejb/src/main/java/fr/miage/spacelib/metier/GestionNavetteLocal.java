/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Quai;
import fr.miage.spacelib.vspaceshared.utilities.AucunQuaiException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePassagersInvalideException;
import javax.ejb.Local;

/**
 *
 * @author AlexisVivier
 */
@Local
public interface GestionNavetteLocal {

    void creerNavette(int nbPlaces, long idStation) 
        throws NombrePassagersInvalideException, AucuneStationException;

    boolean etatNavette(long identifiant) throws AucuneStationException;

    Quai quai(long id) throws AucunQuaiException;

    void lancerNavette(long id) 
        throws AucuneNavetteException, AucunQuaiException;

    void arriveeNavette(long id) throws AucuneNavetteException;
    
}
