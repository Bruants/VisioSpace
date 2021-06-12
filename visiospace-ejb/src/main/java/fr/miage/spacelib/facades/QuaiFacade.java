/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.facades;

import fr.miage.spacelib.entities.Quai;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author AlexisVivier
 */
@Stateless
public class QuaiFacade extends AbstractFacade<Quai> implements QuaiFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.spacelib_visiospace-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuaiFacade() {
        super(Quai.class);
    }

    @Override
    public long findNavette(long navette, long station) {
        Query recupererNavetteQuiStationne = this.em.createQuery("SELECT Q.id FROM Quai Q JOIN Q.station S JOIN Q.stationne N WHERE S.id LIKE ':idStation' AND N.id = :idNavette");
        recupererNavetteQuiStationne.setParameter("idStation", navette);
        recupererNavetteQuiStationne.setParameter("idNavette", station);
        return (Long)recupererNavetteQuiStationne.getSingleResult();
    }
}
