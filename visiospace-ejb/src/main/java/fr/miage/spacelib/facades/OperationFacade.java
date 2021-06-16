/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.facades;

import fr.miage.spacelib.entities.Operation;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    public void revisionNavette(long idNavette) throws AucuneNavetteException {
    }
    
    
    
}
