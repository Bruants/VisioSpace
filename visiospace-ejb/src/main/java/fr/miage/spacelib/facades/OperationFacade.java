/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.facades;

import fr.miage.spacelib.entities.Mecanicien;
import fr.miage.spacelib.entities.Operation;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneOperationException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@Stateless
public class OperationFacade extends AbstractFacade<Operation> implements OperationFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.spacelib_visiospace-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperationFacade() {
        super(Operation.class);
    }

    /**
     * Créée une operation de revision pour une navette
     * @param idNavette
     * @param mecanicien
     * @return L'opération de révision associée
     * @throws AucuneNavetteException 
     */
    @Override
    public Operation revisionNavette(long idNavette, Mecanicien mecanicien) throws AucuneNavetteException {
        this.create(new Operation(mecanicien));
        Query dernierNavette = this.em.createNamedQuery("get last ID operation added");
        return this.find((long)dernierNavette.getResultList().get(0));
    }

    /**
     * Termine la révision d'une navette
     * @param idNavette
     * @throws AucuneOperationException 
     */
    @Override
    public void terminerRevisionNavette(long idNavette) throws AucuneOperationException {
        Query idDernierOperationNavette = this.em.createQuery("SELECT N.derniereOperation FROM Navette N JOIN N.derniereOperation O WHERE N.id = :idNavette AND O.typeOperation = :typeOperation AND O.terminee = false");
        idDernierOperationNavette.setParameter("idNavette", idNavette);
        idDernierOperationNavette.setParameter("typeOperation", Operation.TYPES.REVISION);
        
        try {
            Operation revision = (Operation) idDernierOperationNavette.getSingleResult();
            revision.setTerminee(true);
        } catch(Exception e) {
            throw new AucuneOperationException();
        }
    }
}
