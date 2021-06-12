/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.facades.QuaiFacadeLocal;
import fr.miage.spacelib.facades.StationFacadeLocal;
import java.util.List;
import javax.ejb.Stateless;

/**
 * Gerer les stations traités par le systéme
 * @author AlexisVivier
 */
@Stateless
public class GestionStation implements GestionStationLocal {

    private QuaiFacadeLocal quai;
    
    private StationFacadeLocal station;

    /**
     * Get the value of station
     *
     * @return the value of station
     */
    public StationFacadeLocal getStation() {
        return station;
    }

    /**
     * Set the value of station
     *
     * @param station new value of station
     */
    public void setStation(StationFacadeLocal station) {
        this.station = station;
    }

    /**
     * Get the value of quai
     *
     * @return the value of quai
     */
    public QuaiFacadeLocal getQuai() {
        return quai;
    }

    /**
     * Set the value of quai
     *
     * @param quai new value of quai
     */
    public void setQuai(QuaiFacadeLocal quai) {
        this.quai = quai;
    }

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
    }

    /**
     * Reserve un quai pour acceuilir une navette
     * et garantir l'arrivée de cette derniére
     * Ce quai est choisi arbitrairement
     * @param navette identifiant de la navette qui doit prendre place
     * @return quai qui a etait reservé
     */
    @Override
    public long reserverQuai(long navette) {
        return reserverQuai(quaiDisponible(), navette);
    }
    
    /**
     * Reserve un quai pour acceuilir une naavette
     * et garantir l'arrivée de cette derniére
     * @param quai  identifiant du quai d'arrivé
     * @param navette identifiant de la navette qui doit prendre place
     * @return quai qui a etait reservé
     */
    @Override
    public long reserverQuai(long quai, long navette) {
        return 0L;
    }

    /**
     * Occupe une place dans la station
     * @param quai identifiant du quai d'arrivée
     * @param navette identifiant de la navette à stationné
     */
    @Override
    public void arrimerNavette(long quai, long navette) {
    }

    /**
     * Libére le quai pour acceuilir une nouvelle navette
     * @param quai identifiant du quai a liberer 
     */
    @Override
    public void liberaiQuai(long quai) {
    }

    /**
     * Récupére une navette disponible repondant
     * aux caractéristiques.
     * Ce choix se fait arbitrairement.
     * @param nbPlaces nombres de places nécessaire pour la navette
     * @return identifiant d'une navette disponible
     */
    @Override
    public long navettesDispo(int nbPlaces) {
        return 0L;
    }

    /**
     * Récupére un quai disponible dans la station
     * @return 
     */
    @Override
    public long quaiDisponible() {
        return 0L;
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
