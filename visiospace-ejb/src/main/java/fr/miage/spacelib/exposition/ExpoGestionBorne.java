/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.exposition;

import fr.miage.spacelib.entities.Quai;
import fr.miage.spacelib.entities.Reservation;
import fr.miage.spacelib.entities.Station;
import fr.miage.spacelib.entities.Usager;
import fr.miage.spacelib.metier.GestionReservationLocal;
import fr.miage.spacelib.metier.GestionUsagerLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucunQuaiException;
import fr.miage.spacelib.vspaceshared.utilities.AucunUsagerException;
import fr.miage.spacelib.vspaceshared.utilities.AucunVoyageException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import fr.miage.spacelib.vspaceshared.utilities.DateInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePassagersInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePlacesInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.QuaiExport;
import fr.miage.spacelib.vspaceshared.utilities.ReservationExport;
import fr.miage.spacelib.vspaceshared.utilities.StationExport;
import fr.miage.spacelib.vspaceshared.utilities.UsagerExport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import fr.miage.spacelib.vspaceshared.interfremote.ExpoGestionBorneRemote;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@Stateless
public class ExpoGestionBorne implements ExpoGestionBorneRemote {

    @EJB
    private GestionUsagerLocal gestionUsager;

    @EJB
    private GestionReservationLocal gestionReservation;

    @Override
    public ReservationExport reserverVoyage(long idUsager, int nbPassagers, Date dateDepart, Date dateArrivee, long stationDepart, long stationArrivee)
            throws AucunUsagerException, NombrePlacesInvalideException, DateInvalideException, AucuneStationException, AucunQuaiException, AucuneNavetteException, NombrePassagersInvalideException {     
        Reservation res = this.gestionReservation.reserverVoyage(idUsager, nbPassagers, dateDepart, dateArrivee, stationDepart, stationArrivee);
        Quai quaiDepart = res.getDepart();
        Quai quaiArrivee = res.getArrivee();
        Usager usager = res.getUsager();
        
        return new ReservationExport(res.getId(),
                new QuaiExport(quaiDepart.getId(), new StationExport(quaiDepart.getStation().getId(), quaiDepart.getStation().getCoordonnee())),
                new QuaiExport(quaiArrivee.getId(), new StationExport(quaiArrivee.getStation().getId(), quaiArrivee.getStation().getCoordonnee())),
                new UsagerExport(usager.getId(), usager.getNom(), usager.getPrenom()),
                res.getNbPassagers());
    }

    @Override
    public void departVoyage(long idReservation) throws AucunVoyageException, AucuneNavetteException, AucunQuaiException {
        this.gestionReservation.departVoyage(idReservation);
    }

    @Override
    public void arriveeVoyage(long idReservation) throws AucunVoyageException, AucuneNavetteException, AucunQuaiException {
        this.gestionReservation.arriveeVoyage(idReservation);
    }

    @Override
    public UsagerExport connecter(long idUtilisateur) throws AucunUsagerException {
        Usager nouveauUsager = this.gestionUsager.connecter(idUtilisateur);
        return new UsagerExport(nouveauUsager.getId(),
                nouveauUsager.getNom(),
                nouveauUsager.getPrenom());
    }

    @Override
    public UsagerExport inscrire(String nom, String prenom) {
        Usager nouveauUsager = this.gestionUsager.inscrire(nom, prenom);
        return new UsagerExport(nouveauUsager.getId(),
                nouveauUsager.getNom(),
                nouveauUsager.getPrenom());
    }

    @Override
    public List<StationExport> toutesStations() {
        List<Station> stations = gestionReservation.toutesStations();
        List<StationExport> stationsExp = new ArrayList<>();
        for (Station stat : stations) {
            stationsExp.add(new StationExport(stat.getId(), stat.getCoordonnee()));
        }
        return stationsExp;
    }

    @Override
    public ReservationExport reservationEnCours(long idUtilisateur) throws AucunVoyageException {
        Reservation enCours = gestionReservation.lastReservation(idUtilisateur);
 
        // Si la date de départ est déjà commencée et que le voyage n'est pas terminé
        if (enCours.getVoyage().getDateDepart().before(new Date())) {
            if (!enCours.getVoyage().isTerminee()) { 
                return new ReservationExport(enCours.getId(),
                        new QuaiExport(enCours.getDepart().getId(), new StationExport(enCours.getDepart().getStation().getId(), enCours.getDepart().getStation().getCoordonnee())),
                        new QuaiExport(enCours.getArrivee().getId(), new StationExport(enCours.getArrivee().getStation().getId(), enCours.getArrivee().getStation().getCoordonnee())),
                        new UsagerExport(enCours.getUsager().getId(), enCours.getUsager().getNom(), enCours.getUsager().getPrenom()),
                        enCours.getNbPassagers());
            }
            throw new AucunVoyageException("La réservation est terminée !");
        }      
        throw new AucunVoyageException("Aucune réservation n'est en cours");
    }

    @Override
    public boolean isReservationArrivee(long idUtilisateur) throws AucunVoyageException {
        Reservation res = gestionReservation.trouver(reservationEnCours(idUtilisateur).getId());
                
        return res.getVoyage().getDateArrivee().before(new Date());
    }
}
