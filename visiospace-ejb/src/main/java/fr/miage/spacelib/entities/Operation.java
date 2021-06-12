/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 * Une opération à vocation d'historiser
 * les événements liés à l'activité d'une navette
 * 
 * Elle peux étre de 2 types :
 *     - Voyage
 *     - Révision
 * 
 * Seules les valeurs nécessaire du type sont renseignées
 * @author AlexisVivier
 */
@Entity
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public enum TYPES {
        VOYAGE, REVISION
    }
    
    @NotNull
    private TYPES typeOperation;
    
    /** Date de création de l'opération */
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    /* ------------------ VOYAGE ----------------- */
    /** Reservation rattachée au voyage */
    @OneToOne
    private Reservation reservation;
    
    /** Date de départ du voyage */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDepart;
    
    /** Date d'arrivée du voyage */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateArrivee;
    
    /* ----------------- REVISION ---------------- */ 
    /** Mecanicien chargé de la révision */
    @ManyToOne
    private Mecanicien mecanicien;
        
    /** 
     * Etat de la révision ou voyage courante
     *  true : Révision ou voyage terminée
     *  false : Révision ou voyage non terminée
     */
    private boolean terminee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mecanicien getMecanicien() {
        return mecanicien;
    }

    public void setMecanicien(Mecanicien mecanicien) {
        this.mecanicien = mecanicien;
    }

    public TYPES getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TYPES typeOperation) {
        this.typeOperation = typeOperation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public boolean isTerminee() {
        return terminee;
    }

    public void setTerminee(boolean terminee) {
        this.terminee = terminee;
    }
    
    public Station getStationDepart() {
        if(typeOperation == TYPES.VOYAGE)
            return reservation.getDepart().getStation();
        return null;
    }
    
    public Station getStationArrivee() {
        if(typeOperation == TYPES.VOYAGE)
            return reservation.getArrivee().getStation();
        return null;
    }
    
    /**
     * Renvois le nombre de passagers
     * @return nombre de passager
     *          ou -1 si operation n'est pas un voyage
     */
    public int getNbPassagers(){
        if(typeOperation == TYPES.VOYAGE)
            return reservation.getNbPassagers();
        return -1;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.miage.spacelib.entities.Utilisateur[ id=" + id + " ]";
    }
    
}
