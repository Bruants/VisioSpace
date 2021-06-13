/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.exposition;

import fr.miage.spacelib.metier.GestionMecanicienLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author AlexisVivier
 */
@Stateless
public class ExpoWebMecanicien implements ExpoWebMecanicienLocal {

    @EJB(beanName = "GestionMecanicienExpoEJB")
    private GestionMecanicienLocal gestionMecanicien;

    @Override
    public long debutRevision(long navette) throws AucuneNavetteException {
        return this.gestionMecanicien.debutRevision(navette);
    }

    @Override
    public void clotureRevision(long navette) throws AucuneNavetteException {
        this.gestionMecanicien.clotureRevision(navette);
    }
}
