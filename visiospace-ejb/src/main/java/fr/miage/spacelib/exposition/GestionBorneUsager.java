/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.exposition;

import fr.miage.spacelib.metier.GestionReservationLocal;
import fr.miage.spacelib.vspaceshared.interfremote.GestionBorneUsagerRemote;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author AlexisVivier
 */
@Stateless
public class GestionBorneUsager implements GestionBorneUsagerRemote {

    @EJB
    private GestionReservationLocal gestionReservation;

    @Override
    public void reserverVoyage(long idUsager, int nbPassagers, Date dateDepart, Date dateArrivee, long stationDepart, long stationArrivee) {
        this.gestionReservation.reserverVoyage(idUsager, nbPassagers, dateDepart, dateArrivee, stationDepart, stationArrivee);
    }

    @Override
    public void departVoyage(long idVoyage) {
        this.gestionReservation.departVoyage(idVoyage);
    }

    @Override
    public void arriveeVoyage(long idVoyage) {
        this.gestionReservation.arriveeVoyage(idVoyage);
    }
    
}
