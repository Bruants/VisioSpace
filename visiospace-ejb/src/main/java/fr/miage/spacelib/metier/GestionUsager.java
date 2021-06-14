/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Usager;
import fr.miage.spacelib.facades.UsagerFacadeLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucunUsagerException;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author AlexisVivier
 */
@Stateless
public class GestionUsager implements GestionUsagerLocal {

    @EJB
    private UsagerFacadeLocal usagerFacade;

    @Override
    public Usager connecter(long idUtilisateur) throws AucunUsagerException {
        Usager usager = usagerFacade.find(idUtilisateur);
        if(usager == null){
            throw new AucunUsagerException(Long.toString(idUtilisateur));
        }
        return usager;
    }

    @Override
    public Usager inscrire(String nom, String prenom){
        usagerFacade.create(new Usager(prenom, nom));
        return usagerFacade.findWithNames(nom, prenom);
    }
    
}
