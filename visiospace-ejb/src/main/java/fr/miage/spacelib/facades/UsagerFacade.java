/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.facades;

import fr.miage.spacelib.entities.Usager;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@Stateless
public class UsagerFacade extends AbstractFacade<Usager> implements UsagerFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.spacelib_visiospace-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsagerFacade() {
        super(Usager.class);
    }
    
    /**
     * Trouve un usager en fonction de son nom/prenom
     * @param nom
     * @param prenom
     * @return L'Usager correspondant
     */
    @Override
    public Usager findWithNames(String nom, String prenom) {
        Query recupererNavettePourEntretien = this.em.createQuery("SELECT U.id FROM Usager U WHERE U.nom = :nom AND U.prenom = :prenom");
        recupererNavettePourEntretien.setParameter("nom", nom);
        recupererNavettePourEntretien.setParameter("prenom", prenom);
        return find((Long) recupererNavettePourEntretien.getSingleResult());
    }
    
}
