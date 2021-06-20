/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@Entity
public class Quai implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /** Navette stationnée sur le quai */
    @OneToOne
    private Navette stationne;
    
    @ManyToOne
    private Station station;
    
    @OneToMany(mappedBy = "arrivee")
    private List<Reservation> reservee;

    public Quai() {
    }
    
    public Quai(Station station){
        this.station = station;
    }
    
    public Quai(Station station, Navette navette){
        this.station = station;
        this.stationne = navette;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Navette getStationne() {
        return stationne;
    }

    public void setStationne(Navette stationne) {
        this.stationne = stationne;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public List<Reservation> getReservation() {
        return reservee;
    }

    public void setReservation(List<Reservation> reservee) {
        this.reservee = reservee;
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
        if (!(object instanceof Quai)) {
            return false;
        }
        Quai other = (Quai) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.miage.spacelib.entities.Quai[ id=" + id + " ]";
    }
    
}
