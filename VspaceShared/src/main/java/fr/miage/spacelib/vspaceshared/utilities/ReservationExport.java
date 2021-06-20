/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.vspaceshared.utilities;

import java.io.Serializable;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
public class ReservationExport implements Serializable {
    
    private Long id;
    
    private QuaiExport depart;
    
    private QuaiExport arrivee;
    
    private UsagerExport usager;
    
    private int nbPassagers;
    
    private long idNavette;
    
    public ReservationExport() {
    }

    public ReservationExport(Long id, QuaiExport depart, QuaiExport arrivee, UsagerExport usager, int nbPassagers) {
        this.id = id;
        this.depart = depart;
        this.arrivee = arrivee;
        this.usager = usager;
        this.nbPassagers = nbPassagers;
    }

    public ReservationExport(Long id, QuaiExport depart, QuaiExport arrivee, UsagerExport usager, int nbPassagers, long idNavette ) {
        this.id = id;
        this.depart = depart;
        this.arrivee = arrivee;
        this.usager = usager;
        this.idNavette = idNavette;
        this.nbPassagers = nbPassagers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuaiExport getDepart() {
        return depart;
    }

    public void setDepart(QuaiExport depart) {
        this.depart = depart;
    }

    public QuaiExport getArrivee() {
        return arrivee;
    }

    public void setArrivee(QuaiExport arrivee) {
        this.arrivee = arrivee;
    }

    public UsagerExport getUsager() {
        return usager;
    }

    public void setUsager(UsagerExport usager) {
        this.usager = usager;
    }

    public int getNbPassagers() {
        return nbPassagers;
    }

    public void setNbPassagers(int nbPassagers) {
        this.nbPassagers = nbPassagers;
    }

    public long getIdNavette() {
        return idNavette;
    }

    public void setIdNavette(long idNavette) {
        this.idNavette = idNavette;
    }

}
