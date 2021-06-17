/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.facades;

import fr.miage.spacelib.entities.Mecanicien;
import fr.miage.spacelib.entities.Operation;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author AlexisVivier
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

    @Override
    public Operation revisionNavette(long idNavette, Mecanicien mecanicien) throws AucuneNavetteException {
        this.create(new Operation(mecanicien));
        Query dernierNavette = this.em.createNamedQuery("get last ID operation added");
        return this.find((long)dernierNavette.getResultList().get(0));
    }

    @Override
    public void terminerRevisionNavette(long idNavette) {
        Query idDuMecanicien = this.em.createQuery("SELECT N.derniereOperation FROM Navette N WHERE N.id = :idNavette");
        idDuMecanicien.setParameter("idNavette", idNavette);
        Operation revision = (Operation) idDuMecanicien.getSingleResult();
        revision.setTerminee(true);
    }
    
    
    
    
    
    
}
