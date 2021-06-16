/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.exposition;

import javax.ejb.Local;

/**
 *
 * @author Kevin
 */
@Local
public interface ExpoWebMecanicienLegLocal {
    long debutRevision(long navette);

    void clotureRevision(long navette);
    
}
