/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.exposition;

import fr.miage.spacelib.entities.Quai;
import fr.miage.spacelib.entities.Reservation;
import fr.miage.spacelib.entities.Usager;
import fr.miage.spacelib.metier.GestionReservationLocal;
import fr.miage.spacelib.vspaceshared.interfremote.GestionBorneUsagerRemote;
import fr.miage.spacelib.vspaceshared.utilities.QuaiExport;
import fr.miage.spacelib.vspaceshared.utilities.ReservationExport;
import fr.miage.spacelib.vspaceshared.utilities.StationExport;
import fr.miage.spacelib.vspaceshared.utilities.UsagerExport;
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
    public ReservationExport reserverVoyage(long idUsager, int nbPassagers, Date dateDepart, Date dateArrivee, long stationDepart, long stationArrivee) {
        Reservation res = this.gestionReservation.reserverVoyage(idUsager, nbPassagers, dateDepart, dateArrivee, stationDepart, stationArrivee);
        Quai quaiDepart = res.getArrivee();
        Quai quaiArrivee = res.getArrivee();
        Usager usager = res.getUsager();
        
        return new ReservationExport(res.getId(), 
                new QuaiExport(quaiDepart.getId(), new StationExport(quaiDepart.getStation().getId(), quaiDepart.getStation().getCoordonnee())),
                new QuaiExport(quaiArrivee.getId(), new StationExport(quaiArrivee.getStation().getId(), quaiArrivee.getStation().getCoordonnee())), 
                new UsagerExport(usager.getId(), usager.getNom(), usager.getPrenom()), 
                res.getNbPassagers());
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
