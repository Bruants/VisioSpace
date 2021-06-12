/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Quai;
import fr.miage.spacelib.facades.NavetteFacadeLocal;
import javax.ejb.Stateless;

/**
 * Gestions des navettes traités dans le systéme
 * @author AlexisVivier
 */
@Stateless
public class GestionNavette implements GestionNavetteLocal {

    private NavetteFacadeLocal navette;

    /**
     * Get the value of navette
     *
     * @return the value of navette
     */
    public NavetteFacadeLocal getNavette() {
        return navette;
    }

    /**
     * Set the value of navette
     *
     * @param navette new value of navette
     */
    public void setNavette(NavetteFacadeLocal navette) {
        this.navette = navette;
    }

    /**
     * Créer une nouvelle navette
     * @param nbPlaces Nombre de place de la navette
     * @param idStation Identifiant de la station ou est stationné la navette
     */
    @Override
    public void creerNavette(int nbPlaces, long idStation) {
    }

    /**
     * Donne l'état actuelle de la navette
     * @param identifiant identifiant de la navette 
     * @return  true : aucun entretien de prévu
     *          false : en attente d'entretien
     */
    @Override
    public boolean etatNavette(long identifiant) {
        return false;
    }

    /**
     * Recupére le quai d'une navette
     * @param id d'une navette
     * @return quai ou la navette est arrimé
     */
    @Override
    public Quai quai(long id) {
        return null;
    }

}
