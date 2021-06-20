/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.vspaceshared.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author Kevin
 */
public class WrapperForGetionChargeConducteur {
    
    private List<Combinaison> navettesFaibles = new ArrayList<>();
    
    private List<Combinaison> navettesSuffisantes = new ArrayList<>();
    
    public WrapperForGetionChargeConducteur(HashMap<String,TreeMap<Long,Long>> basketMap) {
        TreeMap<Long, Long> inferieur = basketMap.get("INFERIEUR");
        inferieur.entrySet().forEach((entry) -> {
            Long keyEtry = entry.getKey();
            Long valueEntry = entry.getValue();
            navettesFaibles.add(new Combinaison(keyEtry, valueEntry));
        });
        
        TreeMap<Long, Long> superieur = basketMap.get("SUPERIEUR");
        superieur.entrySet().forEach((entry) -> {
            Long keyEtry = entry.getKey();
            Long valueEntry = entry.getValue();
            navettesSuffisantes.add(new Combinaison(keyEtry, valueEntry));
        });
    }

    public List<Combinaison> getNavettesFaibles() {
        return navettesFaibles;
    }

    public void setNavettesFaibles(List<Combinaison> navettesFaibles) {
        this.navettesFaibles = navettesFaibles;
    }

    public List<Combinaison> getNavettesSuffisantes() {
        return navettesSuffisantes;
    }

    public void setNavettesSuffisantes(List<Combinaison> navettesSuffisantes) {
        this.navettesSuffisantes = navettesSuffisantes;
    }
}
