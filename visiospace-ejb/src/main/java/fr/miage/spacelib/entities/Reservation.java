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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author AlexisVivier
 */
@Entity
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Navette utilisee;
    
    @ManyToOne
    private Quai depart;
    
    @ManyToOne
    private Quai arrivee;
    
    @ManyToOne
    private Usager usager;
    
    @OneToOne(mappedBy = "reservation")
    private Operation voyage;
    
    private int nbPassagers;

    public Reservation() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Navette getUtilisee() {
        return utilisee;
    }

    public void setUtilisee(Navette utilisee) {
        this.utilisee = utilisee;
    }

    public Quai getDepart() {
        return depart;
    }

    public void setDepart(Quai depart) {
        this.depart = depart;
    }

    public Quai getArrivee() {
        return arrivee;
    }

    public void setArrivee(Quai arrivee) {
        this.arrivee = arrivee;
    }

    public Usager getUsager() {
        return usager;
    }

    public void setUsager(Usager usager) {
        this.usager = usager;
    }

    public Operation getVoyage() {
        return voyage;
    }

    public void setVoyage(Operation voyage) {
        this.voyage = voyage;
    }

    public int getNbPassagers() {
        return nbPassagers;
    }

    public void setNbPassagers(int nbPassagers) {
        this.nbPassagers = nbPassagers;
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
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.miage.spacelib.entities.Reservation[ id=" + id + " ]";
    }
    
}
