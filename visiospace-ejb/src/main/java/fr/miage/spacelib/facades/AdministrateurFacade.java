/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.facades;

import fr.miage.spacelib.entities.Administrateur;
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
public class AdministrateurFacade extends AbstractFacade<Administrateur> implements AdministrateurFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.spacelib_visiospace-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdministrateurFacade() {
        super(Administrateur.class);
    }
    
    @Override
    public Administrateur findWithNames(String nom, String prenom) {
        Query recupererNavettePourEntretien = this.em.createQuery("SELECT A.id FROM Administrateur A WHERE A.nom = :nom AND A.prenom = :prenom");
        recupererNavettePourEntretien.setParameter("nom", nom);
        recupererNavettePourEntretien.setParameter("prenom", prenom);
        return find((Long) recupererNavettePourEntretien.getSingleResult());
    }
    
}
