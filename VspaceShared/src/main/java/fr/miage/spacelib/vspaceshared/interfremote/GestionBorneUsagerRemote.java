/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.vspaceshared.interfremote;

import java.util.Date;
import javax.ejb.Remote;

/**
 *
 * @author AlexisVivier
 */
@Remote
public interface GestionBorneUsagerRemote {
    void reserverVoyage(long idUsager, int nbPassagers, Date dateDepart, Date dateArrivee, long stationDepart, long stationArrivee);

    void departVoyage(long idVoyage);

    void arriveeVoyage(long idVoyage);
}
