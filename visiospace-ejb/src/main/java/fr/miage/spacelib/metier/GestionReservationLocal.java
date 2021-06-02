/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import java.util.Date;
import javax.ejb.Local;

/**
 *
 * @author AlexisVivier
 */
@Local
public interface GestionReservationLocal {

    void reserverVoyage(long id, int passagers, Date dateDepart, Date dateArrivee, long stationDepart, long stationArrivee);
    
}
