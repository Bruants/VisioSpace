/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.facades;

import fr.miage.spacelib.entities.Mecanicien;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@Local
public interface MecanicienFacadeLocal {

    void create(Mecanicien mecanicien);

    void edit(Mecanicien mecanicien);

    void remove(Mecanicien mecanicien);

    Mecanicien find(Object id);

    List<Mecanicien> findAll();

    List<Mecanicien> findRange(int[] range);

    int count();

    Mecanicien findWithNames(String nom, String prenom);
    
}
