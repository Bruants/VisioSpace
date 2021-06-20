/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.facades;

import fr.miage.spacelib.entities.Conducteur;
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
public class ConducteurFacade extends AbstractFacade<Conducteur> implements ConducteurFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.spacelib_visiospace-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConducteurFacade() {
        super(Conducteur.class);
    }
    
    /**
     * Trouve un conducteur en fonction de son nom/prenom
     * @param nom
     * @param prenom
     * @return Le conducteur correspondant
     */
    @Override
    public Conducteur findWithNames(String nom, String prenom) {
        Query recupererIdConducteur = this.em.createQuery("SELECT C.id FROM Conducteur C WHERE C.nom = :nom AND C.prenom = :prenom");
        recupererIdConducteur.setParameter("nom", nom);
        recupererIdConducteur.setParameter("prenom", prenom);
        return find((Long) recupererIdConducteur.getSingleResult());
    }
    
}
