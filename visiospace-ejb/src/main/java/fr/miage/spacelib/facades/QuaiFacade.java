/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.facades;

import fr.miage.spacelib.entities.Navette;
import fr.miage.spacelib.entities.Operation;
import fr.miage.spacelib.entities.Quai;
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
    public Quai findNavette(long navette) {
        Query recupererNavetteQuiStationne = this.em.createQuery("SELECT Q.id FROM Quai Q JOIN Q.stationne N WHERE N.id = :idNavette");
        recupererNavetteQuiStationne.setParameter("idStation", navette);
        return find((Long)recupererNavetteQuiStationne.getSingleResult());
    }

    @Override
    public Quai quaiDisponible(long idStation) {
        Query recupererNavetteQuiStationne = this.em.createQuery("SELECT Q FROM Quai Q JOIN Q.station S WHERE S.id = :idStation AND Q.reservee IS NOT NULL");
        recupererNavetteQuiStationne.setParameter("idStation", idStation);
        return (Quai)recupererNavetteQuiStationne.getResultList().get(0);
    }
    
    
    @Override
    public Navette navetteDisponible(long idStation, int nbPlaces) {
        Query recupererNavetteQuiStationne = this.em.createQuery("SELECT N FROM Navette N JOIN N.stationeSur Q, Q.station S JOIN N.historique O WHERE S.id = :idStation AND N.nbVoyagesDepuisDernierEntretien < :nbVoyagesEntretiens AND N.nbPlace = :nbPlaces");
        recupererNavetteQuiStationne.setParameter("idStation", idStation);
        recupererNavetteQuiStationne.setParameter("nbVoyagesEntretiens", StationFacade.NB_VOYAGES_ENTRETIENS);
        recupererNavetteQuiStationne.setParameter("nbPlaces", nbPlaces);
        List<Navette> navettes = recupererNavetteQuiStationne.getResultList();
        boolean valide = false;
        for(int i = 0 ; i < navettes.size() && !valide; i++) {
            Operation operation = navettes.get(i).getDerniereOperation();
            valide = operation.isTerminee();
        }
        
        return navettes.get(0); // TODO Exception pas de navette dispo
    }
    
    
}
