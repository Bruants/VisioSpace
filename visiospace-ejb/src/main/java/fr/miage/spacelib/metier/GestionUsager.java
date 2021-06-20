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
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@Stateless
public class GestionUsager implements GestionUsagerLocal {

    @EJB
    private UsagerFacadeLocal usagerFacade;

    /**
     * Teste la connexion d'un usager
     * TODO : Token ou Variable Session
     * @param idUtilisateur l'id de l'usager
     * @return l'objet usager
     * @throws AucunUsagerException 
     */
    @Override
    public Usager connecter(long idUtilisateur) throws AucunUsagerException {
        Usager usager = usagerFacade.find(idUtilisateur);
        if(usager == null){
            throw new AucunUsagerException(Long.toString(idUtilisateur));
        }
        return usager;
    }

    /**
     * Crée un usager
     * @param nom
     * @param prenom
     * @return L'objet usager correspondant
     */
    @Override
    public Usager inscrire(String nom, String prenom){
        usagerFacade.create(new Usager(prenom, nom));
        return usagerFacade.findWithNames(nom, prenom);
    }
    
}
