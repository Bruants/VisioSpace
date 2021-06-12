/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.facades;

import fr.miage.spacelib.entities.Navette;
import fr.miage.spacelib.entities.Quai;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author AlexisVivier
 */
@Local
public interface QuaiFacadeLocal {

    void create(Quai quai);

    void edit(Quai quai);

    void remove(Quai quai);

    Quai find(Object id);

    List<Quai> findAll();

    List<Quai> findRange(int[] range);

    int count();

    long findNavette(long navette, long station);

    Quai quaiDisponible(long idStation);
    
    Navette navetteDisponible(long idStation, int nbPlaces);
    
}
