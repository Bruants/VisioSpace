/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.facades.NavetteFacadeLocal;
import javax.ejb.Stateless;

/**
 *
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

    @Override
    public void creerNavette(int nbPlaces) {
    }

    @Override
    public void etatNavette(long identifiant, boolean estActif) {
    }

}
