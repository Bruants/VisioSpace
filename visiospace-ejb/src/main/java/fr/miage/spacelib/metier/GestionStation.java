/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Navette;
import fr.miage.spacelib.entities.Quai;
import fr.miage.spacelib.entities.Station;
import fr.miage.spacelib.facades.NavetteFacadeLocal;
import fr.miage.spacelib.facades.QuaiFacadeLocal;
import fr.miage.spacelib.facades.StationFacadeLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucunQuaiException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneStationException;
import fr.miage.spacelib.vspaceshared.utilities.CoordonneesInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombreNavetteInvalideException;
import fr.miage.spacelib.vspaceshared.utilities.NombrePlacesInvalideException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Gerer les stations traités par le systéme
 *
 * @author AlexisVivier
 */
@Stateless
public class GestionStation implements GestionStationLocal {

    @EJB
    private StationFacadeLocal stationFacade;

    @EJB
    private QuaiFacadeLocal quaiFacade;

    @EJB
    private NavetteFacadeLocal navetteFacade;

    /**
     * Crée une station avec les navettes amarés à hauteur de 50% de la capacité
     * total des quai de la station. Le nombre de quai seras le nombre de
     * navettes*2
     *
     * @param coordonnees Coordonée spatiale de la station
     * @param navettes Liste des navettes a ajouter
     */
    @Override
    public long creerStation(String coordonnees, List<Long> navettes)
            throws NombreNavetteInvalideException, CoordonneesInvalideException {
        Station station = new Station();
        List<Quai> quais = new ArrayList<>();

        List<Station> stations = stationFacade.findAll();

        // recherche d'une station possédant les mêmes coordonnées
        for (Station s : stations) {

            if (s.getCoordonnee().equals(coordonnees)) {
                throw new CoordonneesInvalideException();
            }
        }

        // aucune navette n'est affectée à la nouvelle station
        if (navettes.size() <= 0) {
            throw new NombreNavetteInvalideException();
        }

        station.setCoordonnee(coordonnees);
        for (Long idNavette : navettes) {
            // Ajout du quai avec la navette
            Quai nouveauQuai = new Quai(station, navetteFacade.find(idNavette));
            quais.add(nouveauQuai);
            navetteFacade.find(idNavette).setStationeSur(nouveauQuai);
            // Ajout du quai vide
            quais.add(new Quai(station));
        }

        for (int i = 0; i < quais.size(); i++) {
            quaiFacade.create(quais.get(i));
        }

        station.setQuais(quais);

        stationFacade.create(station);

        stations = stationFacade.findAll();

        // recherche d'une station possédant les mêmes coordonnées
        long idStation = -1;
        for (int i = 0; i < stations.size() && idStation == -1; i++) {
            if (stations.get(i).getCoordonnee().equals(coordonnees)) {
                idStation = stations.get(i).getId();
            }
        }
        return idStation;
    }

    /**
     * Reserve un quai pour acceuilir une navette et garantir l'arrivée de cette
     * derniére Ce quai est choisi arbitrairement
     *
     * @param idStation identifiant de la station
     * @param navette identifiant de la navette qui doit prendre place
     * @return quai qui a etait reservé
     */
    @Override
    public Quai reserverQuai(long idStation, long navette, Date dateReservation)
            throws AucuneStationException, AucuneNavetteException,
            AucunQuaiException {

        Quai quai;

        if (stationFacade.find(idStation) == null) {
            throw new AucuneStationException();
        }

        if (navetteFacade.find(navette) == null) {
            throw new AucuneNavetteException();
        }

        quai = quaiDisponible(idStation, dateReservation);

        if (quaiFacade.find(quai) == null) {
            throw new AucunQuaiException();
        }

        return quai;
    }

    /**
     * Occupe une place dans la station
     *
     * @param idQuai identifiant du quai d'arrivée
     * @param navette identifiant de la navette à stationné
     */
    @Override
    public void arrimerNavette(long idQuai, long navette)
            throws AucunQuaiException, AucuneNavetteException {

        if (quaiFacade.find(idQuai) == null) {
            throw new AucunQuaiException();
        }

        if (navetteFacade.find(navette) == null) {
            throw new AucuneNavetteException();
        }

        Quai quai = quaiFacade.find(idQuai);

        //La navette est attachée au quai
        quai.setStationne(navetteFacade.find(navette));
    }

    /**
     * Occupe une place dans la station
     *
     * @param idNavette identifiant de la navette à stationné
     */
    @Override
    public void arrimerNavette(long idNavette) throws AucuneNavetteException, AucunQuaiException {
        Navette navette = navetteFacade.find(idNavette);

        if (navette == null) {
            throw new AucuneNavetteException();
        }

        Quai quai = quaiFacade.findNavette(idNavette);

        if (quai == null) {
            throw new AucunQuaiException();
        }

        quai.setStationne(navetteFacade.find(navette.getId()));   //La navette est attachée au quai
    }

    /**
     * Libére le quai pour acceuilir une nouvelle navette
     *
     * @param idQuai identifiant du quai a liberer
     */
    @Override
    public void libererQuai(long idQuai) throws AucunQuaiException {
        Quai quai = quaiFacade.find(idQuai);

        if (quai == null) {
            throw new AucunQuaiException();
        }

        //Si il y avait une réservation de quai, cette dernière disparait
        //TODO: Retirer seulement la derniére commande
        quai.setReservation(null);

        //La navette n'est plus attachée au quai
        quai.setStationne(null);
    }

    /**
     * Récupére une navette disponible repondant aux caractéristiques. Ce choix
     * se fait arbitrairement.
     *
     * @param idStation La station dans laquelle on recherche une navette
     * @param nbPlaces nombres de places nécessaire pour la navette
     * @return identifiant d'une navette disponible
     */
    @Override
    public Navette navettesDispo(long idStation, int nbPlaces) throws AucuneStationException, NombrePlacesInvalideException {

        if (stationFacade.find(idStation) == null) {
            throw new AucuneStationException();
        }

        if (nbPlaces != 2 || nbPlaces != 5 || nbPlaces != 10 || nbPlaces != 15) {
            throw new NombrePlacesInvalideException();
        }

        return quaiFacade.navetteDisponible(idStation, nbPlaces);
    }

    /**
     * Récupére un quai disponible dans la station
     *
     * @param idStation La station dans laquelle on recherche une navette
     * @return Quai disponible
     */
    @Override
    public Quai quaiDisponible(long idStation, Date dateReservation) throws AucuneStationException, AucunQuaiException {

        if (stationFacade.find(idStation) == null) {
            throw new AucuneStationException();
        }

        return quaiFacade.quaiDisponible(idStation, dateReservation);
    }

    /**
     * Récupére un quai disponible dans la station
     *
     * @param idStation La station dans laquelle on recherche une navette
     * @return Quai disponible
     */
    @Override
    public List<Quai> quaisDisponible(long idStation, Date dateReservation) throws AucuneStationException, AucunQuaiException {

        if (stationFacade.find(idStation) == null) {
            throw new AucuneStationException();
        }

        return quaiFacade.quaisDisponible(idStation, dateReservation);
    }

    /**
     * Recupére la liste des navettes de la station a revisé
     *
     * @param idStation identifiant de la station de recherche
     * @return liste des navettes a revisé
     */
    @Override
    public List<Long> navettesAReviser(long idStation) throws AucuneStationException {
        //TODO: faire une requéte SQL trié de la plus 
        //      ancienne en attente à la dernière

        if (stationFacade.find(idStation) == null) {
            throw new AucuneStationException();
        }

        return null;
    }

    @Override
    public List<Station> toutesStations() {
        return stationFacade.findAll();
    }

    @Override
    public Station trouverStation(long id) {
        return stationFacade.find(id);
    }

    @Override
    public boolean stationExiste(long idStation) {
        return stationFacade.find(idStation) != null;
    }

}
