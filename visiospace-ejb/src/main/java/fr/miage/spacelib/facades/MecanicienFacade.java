/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.facades;

import fr.miage.spacelib.entities.Mecanicien;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@Stateless
public class MecanicienFacade extends AbstractFacade<Mecanicien> implements MecanicienFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.spacelib_visiospace-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MecanicienFacade() {
        super(Mecanicien.class);
    }
    /**
     * Trouve un mecanicien en fonction de son nom/prenom
     * @param nom
     * @param prenom
     * @return Le mecanicien correspondant
     */
    @Override
    public Mecanicien findWithNames(String nom, String prenom) {
        Query idDuMecanicien = this.em.createQuery("SELECT M.id FROM Mecanicien M WHERE M.nom = :nom AND M.prenom = :prenom");
        idDuMecanicien.setParameter("nom", nom);
        idDuMecanicien.setParameter("prenom", prenom);
        return find((Long) idDuMecanicien.getSingleResult());
    }
    
    
    
}
