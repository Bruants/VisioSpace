/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Quai;
import javax.ejb.Local;

/**
 *
 * @author AlexisVivier
 */
@Local
public interface GestionNavetteLocal {

    void creerNavette(int nbPlaces, long idStation);

    boolean etatNavette(long identifiant);

    Quai quai(long id);

    void lancerNavette(long id);

    void arriveeNavette(long id);
    
}
