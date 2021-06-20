/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.entities.Conducteur;
import fr.miage.spacelib.entities.Station;
import fr.miage.spacelib.facades.ConducteurFacadeLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucunConducteurException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@Stateless
public class GestionConducteur implements GestionConducteurLocal {
    
    public final long LIMITE_INFERIEUR_TRANSFERT = 10;
    
    public final long LIMITE_SUPERIEUR_TRANSFERT = 20;

    @EJB
    private GestionStationLocal gestionStation;

    @EJB
    private ConducteurFacadeLocal conducteurFacade;

    /**
     * Créé un conducteur
     * @param nom
     * @param prenom
     * @return L'id du conducteur
     */
    @Override
    public Long creerConducteur(String nom, String prenom) {
                conducteurFacade.create(new Conducteur(prenom, nom));
        return conducteurFacade.findWithNames(nom, prenom).getId();
    }

    /**
     * Teste la connexion d'un conducteur
     * TODO : Token ou Variable Session
     * @param idConducteur
     * @return l'id du conducteur
     * @throws AucunConducteurException 
     */
    @Override
    public Long connexion(long idConducteur) throws AucunConducteurException {
        Conducteur conducteur = conducteurFacade.find(idConducteur);
        if(conducteur == null) {
            throw new AucunConducteurException(Long.toString(idConducteur));
        }
        return conducteur.getId();
    }

    /**
     * @return Le pourcentage de remplissage des quais de chaque station
     */
    @Override
    public HashMap<String,TreeMap<Long,Long>> ratioQuaisNavettesDisponibles() {
        List<Station> stations = gestionStation.toutesStations();
        TreeMap<Long,Long> ratios = new TreeMap<>(new Comparator<Long>() {
            @Override
            public int compare(Long s1, Long s2) {
                return s2.compareTo(s1);
            }
        });
        TreeMap<Long,Long> ratiosInferieur = new TreeMap<>(new Comparator<Long>() {
            @Override
            public int compare(Long s1, Long s2) {
                return s2.compareTo(s1);
            }
        });
        TreeMap<Long,Long> ratiosSuperieur = new TreeMap<>(new Comparator<Long>() {
            @Override
            public int compare(Long s1, Long s2) {
                return s2.compareTo(s1);
            }
        });
        
        for (Station s : stations) {
            ratios.put(s.getId(), ratioQuaisNavettesDisponiblesParStation(s.getId()));
        }
        
        for (Long key : ratios.keySet()) {
            Long value = ratios.get(key);
            if(value < LIMITE_INFERIEUR_TRANSFERT) {
                ratiosInferieur.put(key, value);
            } else {
                ratiosSuperieur.put(key, value);
            }
        }

        HashMap<String, TreeMap<Long,Long>> map = new HashMap<>();
        map.put("INFERIEUR",ratiosInferieur);
        map.put("SUPERIEUR",ratiosSuperieur);

        return map;
    }

    /**
     * @param idStation La station pour laquelle on veut l'info
     * @return Le pourcentage de remplissage des quais d'une station
     */
    @Override
    public Long ratioQuaisNavettesDisponiblesParStation(long idStation) {
        return gestionStation.ratioDispoDansDixJours(idStation);
    }
    
    
    
    
}
