/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.facades;

import fr.miage.spacelib.entities.Navette;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author AlexisVivier
 */
@Stateless
public class NavetteFacade extends AbstractFacade<Navette> implements NavetteFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.spacelib_visiospace-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NavetteFacade() {
        super(Navette.class);
    }

    @Override
    public boolean estDisponiblePourRevision(long idNavette) {
        Query recupererNavettePourEntretien = this.em.createQuery("SELECT N FROM Navette N WHERE N.id = :idNavette");
        recupererNavettePourEntretien.setParameter("idNavette", idNavette);

        Navette resultat = (Navette)recupererNavettePourEntretien.getResultList();
        
        return resultat.getDerniereOperation().isTerminee();
    }
    
    @Override
    public boolean estDisponiblePourVoyage(long idNavette) {
        Query recupererNavettePourEntretien = this.em.createQuery("SELECT N FROM Navette N WHERE N.id = :idNavette AND N.nbVoyagesDepuisDernierEntretien < 3");
        recupererNavettePourEntretien.setParameter("idNavette", idNavette);

        Navette resultat = (Navette)recupererNavettePourEntretien.getResultList();
        
        return resultat.getDerniereOperation().isTerminee();
    }

    @Override
    public Long derniereNavette() {
        Query dernierNavette = this.em.createNamedQuery("get last ID navette added");
        System.out.println(dernierNavette.getResultList());
        return (Long)(long)dernierNavette.getResultList().get(0);
    }
    
}
