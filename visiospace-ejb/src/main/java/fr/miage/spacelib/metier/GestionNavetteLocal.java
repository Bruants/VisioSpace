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
import fr.miage.spacelib.vspaceshared.utilities.NombrePlacesInvalideException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AlexisVivier
 */
@Local
public interface GestionNavetteLocal {

    Long creerNavette(int nbPlaces) 
        throws NombrePlacesInvalideException, AucuneStationException;

    boolean etatNavettePourRevision(long identifiant) throws AucuneNavetteException;
    boolean etatNavettePourVoyage(long identifiant) throws AucuneNavetteException;


    Quai quai(long id) throws AucuneNavetteException;

    void lancerNavette(long id) 
        throws AucuneNavetteException, AucunQuaiException;

    void arriveeNavette(long id) throws AucuneNavetteException, AucunQuaiException;

    Long derniereNavetteAjoutee();

    List<Long> navettesAReviser(long idStation);

    List<Long> navettesEnCoursDeRevision(long idStation);
    
}
