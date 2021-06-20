/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.facades;

import fr.miage.spacelib.entities.Reservation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
@Stateless
public class ReservationFacade extends AbstractFacade<Reservation> implements ReservationFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.spacelib_visiospace-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * @param idUsager
     * @return Trouve toutes les réservations d'un utilisateur
     */
    @Override
    public List<Reservation> findUsager(long idUsager) {
        List<Reservation> reservations = new ArrayList<>();
        List<Object> resultat;

        Query recupererNavettePourEntretien = this.em.createQuery("SELECT R.id FROM Reservation R JOIN R.usager U WHERE U.id = :idUsager ORDER BY R.id DESC");
        recupererNavettePourEntretien.setParameter("idUsager", idUsager);

        resultat = recupererNavettePourEntretien.getResultList();

        for (Object id : resultat) {
            reservations.add(find((long) id));
        }
        return reservations;
    }

    public ReservationFacade() {
        super(Reservation.class);
    }

}
