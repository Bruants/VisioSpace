/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.facades;

import fr.miage.spacelib.entities.Station;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author AlexisVivier
 */
@Stateless
public class StationFacade extends AbstractFacade<Station> implements StationFacadeLocal {
    
    public final static int NB_VOYAGES_ENTRETIENS = 3;
    
    @PersistenceContext(unitName = "fr.miage.spacelib_visiospace-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StationFacade() {
        super(Station.class);
    }

    @Override
    public List<Station> findNavettePourEntretien(long id) {
        Query recupererNavettePourEntretien = this.em.createQuery("SELECT N.id FROM Station S JOIN S.quais Q JOIN Q.stationne N WHERE N.nbVoyagesDepuisDernierEntretien >= :nbVoyagesRevisions AND S.id = :idStation");
        recupererNavettePourEntretien.setParameter("nbVoyagesRevisions", NB_VOYAGES_ENTRETIENS);
        recupererNavettePourEntretien.setParameter("idStation", id);
        return recupererNavettePourEntretien.getResultList();
    }
}
