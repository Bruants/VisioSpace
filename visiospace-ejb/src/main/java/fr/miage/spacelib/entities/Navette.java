/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author AlexisVivier
 */
@Entity
public class Navette implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /** Nombre de places disponibles */
    private int nbPlace;
    
    /** Le nombre de voyage que l'on a fait depuis le dernier entretien */
    private int nbVoyagesDepuisDernierEntretien;
    
    @OneToOne
    private Operation derniereOperation;
        
    /** Stationne sur le quai */
    @OneToOne(mappedBy = "stationne")
    private Quai stationeSur;
    
    public Navette() {
    }

    public Quai getStationeSur() {
        return stationeSur;
    }

    public void setStationeSur(Quai stationeSur) {
        this.stationeSur = stationeSur;
    }

    public int getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Operation getDerniereOperation() {
        return derniereOperation;
    }

    public void setDerniereOperation(Operation derniereOperation) {
        derniereOperation.setPrecedenteOperation(this.derniereOperation);
        this.derniereOperation = derniereOperation;
    }

    /**
     * Donne l'etat de la navette courante
     * @return true  : navette utilisable
     *         false : navette inutilisable
     */
    public boolean isActive(){
        //TODO: Recupérer etat navette
        return false;
    }
    
    public void addCompteurVoyage() {
        this.nbVoyagesDepuisDernierEntretien++;
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
        if (!(object instanceof Navette)) {
            return false;
        }
        Navette other = (Navette) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.miage.spacelib.entities.Navette[ id=" + id + " ]";
    }
    
}
