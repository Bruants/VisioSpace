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
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Kevin
 */
@Stateless
public class GestionConducteur implements GestionConducteurLocal {

    @EJB
    private GestionStationLocal gestionStation;

    @EJB
    private ConducteurFacadeLocal conducteurFacade;

    @Override
    public Long creerConducteur(String nom, String prenom) {
                conducteurFacade.create(new Conducteur(prenom, nom));
        return conducteurFacade.findWithNames(nom, prenom).getId();
    }

    @Override
    public Long connexion(long idConducteur) throws AucunConducteurException {
        Conducteur conducteur = conducteurFacade.find(idConducteur);
        if(conducteur == null) {
            throw new AucunConducteurException(Long.toString(idConducteur));
        }
        return conducteur.getId();
    }

    @Override
    public List<Long> ratioQuaisNavettesDisponibles() {
        List<Station> stations = gestionStation.toutesStations();
        List<Long> ratios = new ArrayList<>();

        for (Station s : stations) {
            ratios.add(ratioQuaisNavettesDisponiblesParStation(s.getId()));
        }
        Collections.sort(ratios);
        
        return ratios;
    }

    @Override
    public Long ratioQuaisNavettesDisponiblesParStation(long idStation) {
        return gestionStation.ratioDispoDansDixJours(idStation);
    }
    
    
    
    
}
