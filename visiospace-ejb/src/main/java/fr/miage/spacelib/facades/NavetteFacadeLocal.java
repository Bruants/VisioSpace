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
import javax.ejb.Local;

/**
 *
 * @author AlexisVivier
 */
@Local
public interface NavetteFacadeLocal {

    void create(Navette navette);

    void edit(Navette navette);

    void remove(Navette navette);

    Navette find(Object id);

    List<Navette> findAll();

    List<Navette> findRange(int[] range);

    int count();

    boolean estDisponiblePourRevision(long idNavette);
    
    Long derniereNavette();

    void ajouterOperation(long idNavette, Operation operation);

    List<Long> sontAReviser(long idStation);

    List<Long> sontEnRevision(long idStation);

    void razNbOperationsDepuisDerniereRevision(long idNavette) throws AucuneNavetteException;

}
