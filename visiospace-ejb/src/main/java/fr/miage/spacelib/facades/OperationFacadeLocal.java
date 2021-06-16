/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.facades;

import fr.miage.spacelib.entities.Operation;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AlexisVivier
 */
@Local
public interface OperationFacadeLocal {

    void create(Operation operation);

    void edit(Operation operation);

    void remove(Operation operation);

    Operation find(Object id);

    List<Operation> findAll();

    List<Operation> findRange(int[] range);

    int count();

    void revisionNavette(long idNavette) throws AucuneNavetteException;
    
}
