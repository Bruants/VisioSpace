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
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Gerer les stations traités par le systéme
 * @author AlexisVivier
 */
@Stateless
public class GestionStation implements GestionStationLocal {

    @EJB
    private NavetteFacadeLocal navetteFacade;

    @EJB
    private QuaiFacadeLocal quaiFacade;
    
    @EJB
    private StationFacadeLocal stationFacade;

    /**
     * Crée une station avec les navettes amarés
     * à hauteur de 50% de la capacité total des quai de la
     * station.
     * Le nombre de quai seras le nombre de navettes*2
     * 
     * @param coordonnees Coordonée spatiale de la station
     * @param navettes    Liste des navettes a ajouter
     */
    @Override
    public void creerStation(String coordonnees, List<Long> navettes) {
        Station station = new Station();
        List<Quai> quais = new ArrayList<>();
        
        station.setCoordonnee(coordonnees);
        for(Long idNavette:navettes){
            // Ajout du quai avec la navette
            quais.add(new Quai(station, navetteFacade.find(idNavette)));
            // Ajout du quai vide
            quais.add(new Quai(station));
        }
        
        station.setQuais(quais);
        
        stationFacade.create(station);
    }

    /**
     * Reserve un quai pour acceuilir une navette
     * et garantir l'arrivée de cette derniére
     * Ce quai est choisi arbitrairement
     * @param idStation identifiant de la station
     * @param navette identifiant de la navette qui doit prendre place
     * @return quai qui a etait reservé
     */
    @Override
    public Quai reserverQuai(long idStation, long navette) {
        return reserverQuai(quaiDisponible(idStation), navette);
    }
    
    /**
     * Reserve un quai pour acceuilir une naavette
     * et garantir l'arrivée de cette derniére
     * @param quai  identifiant du quai d'arrivé
     * @param navette identifiant de la navette qui doit prendre place
     * @return Quai qui a etait reservé
     */
    @Override
    public Quai reserverQuai(Quai quai, long navette) {
        Quai quaiDispo = quaiDisponible(navette);
        quaiDispo.setReservation(navetteFacade.find(navette));
        
        return quaiDispo;
    }

    /**
     * Occupe une place dans la station
     * @param idQuai identifiant du quai d'arrivée
     * @param navette identifiant de la navette à stationné
     */
    @Override
    public void arrimerNavette(long idQuai, long navette) {
        Quai quai = quaiFacade.find(idQuai);
        
        //La navette est attachée au quai
        quai.setStationne(navetteFacade.find(navette));
    }

    /**
     * Libére le quai pour acceuilir une nouvelle navette
     * @param idQuai identifiant du quai a liberer 
     */
    @Override
    public void liberaiQuai(long idQuai) {
        Quai quai = quaiFacade.find(idQuai);
        
        //Si il y avais une reservation de quai, cette derniére disparait
        quai.setReservation(null);
        
        //La navette n'est plus attachée au quai
        quai.setStationne(null);
    }

    /**
     * Récupére une navette disponible repondant
     * aux caractéristiques.
     * Ce choix se fait arbitrairement.
     * @param nbPlaces nombres de places nécessaire pour la navette
     * @return identifiant d'une navette disponible
     */
    @Override
    public Navette navettesDispo(long idStation, int nbPlaces) {
        return null;
    }

    /**
     * Récupére un quai disponible dans la station
     * @return Quai disponible
     */
    @Override
    public Quai quaiDisponible(long idStation) {
        return null;
    }

    /**
     * Recupére la liste des navettes de la station
     * a revisé
     * @param idStation identifiant de la station de recherche
     * @return liste des navettes a revisé
     */
    @Override
    public List<Long> navettesAReviser(long idStation) {
        //TODO: faire une requéte SQL trié de la plus 
        //      ancienn en attente a la derniére
        return null;
    }

}
