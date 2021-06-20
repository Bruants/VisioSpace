/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.exposition;

import fr.miage.spacelib.entities.Reservation;
import fr.miage.spacelib.entities.Station;
import fr.miage.spacelib.metier.GestionReservationLocal;
import fr.miage.spacelib.metier.GestionStationLocal;
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
import fr.miage.spacelib.vspaceshared.utilities.VoyageDejaCommenceException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author AlexisVivier
 */
@Stateless
public class ExpoWebUsager implements ExpoWebUsagerLocal {

    @EJB
    private GestionStationLocal gestionStation;

    @EJB
    private GestionReservationLocal gestionReservation;

    @Override
    public List<StationExport> carteSpacelib() {
        List<Station> stations = gestionStation.toutesStations();
        List<StationExport> stationsExport = new ArrayList<>();

        for (Station s : stations) {
            stationsExport.add(new StationExport(s.getId(), s.getCoordonnee()));
        }

        return stationsExport;
    }

    @Override
    public ReservationExport reserverVoyage(long idUsager, int nbPassagers, Date dateDepart, Date dateArrivee, long stationDepart, long stationArrivee)
            throws AucunQuaiException, AucuneStationException,
            AucuneNavetteException, NombrePlacesInvalideException,
            AucunUsagerException, DateInvalideException,
            NombrePassagersInvalideException {

        Reservation res = gestionReservation.reserverVoyage(idUsager, nbPassagers, dateDepart, dateArrivee, stationDepart, stationArrivee);

        return new ReservationExport(res.getId(),
                new QuaiExport(res.getDepart().getId(), new StationExport(res.getDepart().getStation().getId(), res.getDepart().getStation().getCoordonnee())),
                new QuaiExport(res.getArrivee().getId(), new StationExport(res.getArrivee().getStation().getId(), res.getArrivee().getStation().getCoordonnee())),
                new UsagerExport(res.getUsager().getId(), res.getUsager().getNom(), res.getUsager().getPrenom()),
                res.getNbPassagers(),
                res.getUtilisee().getId());
    }

    @Override
    public void annulerReservation(long idUsager, long idReservation) throws AucunUsagerException, AucunVoyageException, VoyageDejaCommenceException {
        gestionReservation.annulerReservation(idUsager, idReservation);
    }
}
