/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.facades;

import fr.miage.spacelib.entities.Navette;
import fr.miage.spacelib.entities.Operation;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
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
        Navette resultat = (Navette)recupererNavettePourEntretien.getSingleResult();
        if(resultat.getDerniereOperation() == null) {
            return true;
        }
        return resultat.getDerniereOperation().isTerminee();
    }

    @Override
    public Long derniereNavette() {
        Query dernierNavette = this.em.createNamedQuery("get last ID navette added");
        return (Long)(long)dernierNavette.getResultList().get(0);
    }

    @Override
    public void ajouterOperation(long idNavette, Operation operation) {
        Navette navette = this.find(idNavette);
        if(navette.getDerniereOperation() != null) {
            operation.setPrecedenteOperation(navette.getDerniereOperation());
        }
        navette.setDerniereOperation(operation);
    }
    
    

    @Override
    public List<Long> sontAReviser(long idStation) {
        Query navettesAReviser = this.em.createQuery("SELECT N.id FROM Navette N JOIN N.stationeSur Q JOIN Q.station S JOIN N.derniereOperation O WHERE S.id = :idStation AND N.nbVoyagesDepuisDernierEntretien >= 3 AND O.terminee = true");
        navettesAReviser.setParameter("idStation", idStation);

        List<Long> resultats = (List<Long>)navettesAReviser.getResultList();
        for(int i = 0; i < resultats.size() ; i++) {
            if(!estDisponiblePourRevision(resultats.get(i))) {
                resultats.remove(i);
                i--;
            }
        }
        return resultats;
    }

    @Override
    public List<Long> sontEnRevision(long idStation) {
        Query navettesAReviser = this.em.createQuery("SELECT N.id FROM Navette N JOIN N.derniereOperation O JOIN N.stationeSur Q JOIN Q.station S WHERE S.id = :idStation AND O.terminee = false AND O.typeOperation = :typeAReviser");
        navettesAReviser.setParameter("idStation", idStation);
        navettesAReviser.setParameter("typeAReviser", Operation.TYPES.REVISION);

        return (List<Long>)navettesAReviser.getResultList();
    }

    @Override
    public void razNbOperationsDepuisDerniereRevision(long idNavette) throws AucuneNavetteException {
        Navette navette = this.find(idNavette);
        if (navette == null) {
            throw new AucuneNavetteException(Objects.toString(idNavette));
        }
        navette.setNbVoyagesDepuisDernierEntretien(0);
    }

}
