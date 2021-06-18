/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.exposition;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Kevin
 */
@Local
public interface ExpoWebMecanicienLegLocal {
    long debutRevision(long navette, long idMecanicien);

    boolean clotureRevision(long navette);

    Long creerMecanicien(String nom, String prenom);

    boolean connexion(long id, long idStation);

    List<Long> navettesAReviser(long idStation);

    List<Long> navettesEnCoursDeRevision(long idStation);
}
