/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Conducteur;
import fr.miage.spacelib.facades.ConducteurFacadeLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucunConducteurException;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Kevin
 */
@Stateless
public class GestionConducteur implements GestionConducteurLocal {

    @EJB
    private ConducteurFacadeLocal conducteurFacade;

    @Override
    public Long creerConducteur(String nom, String prenom) {
                conducteurFacade.create(new Conducteur(prenom, nom));
        return conducteurFacade.findWithNames(nom, prenom).getId();
    }

    @Override
    public Long connexion(long idConducteur) throws AucunConducteurException {
        Conducteur conducteur = conducteurFacade.find(idConducteur);
        if(conducteur == null) {
            throw new AucunConducteurException(Long.toString(idConducteur));
        }
        return conducteur.getId();
    }
}
