/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.facades;

import fr.miage.spacelib.entities.Navette;
import fr.miage.spacelib.entities.Operation;
import fr.miage.spacelib.entities.Quai;
import fr.miage.spacelib.vspaceshared.utilities.AucunQuaiException;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author AlexisVivier
 */
@Stateless
public class QuaiFacade extends AbstractFacade<Quai> implements QuaiFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.spacelib_visiospace-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuaiFacade() {
        super(Quai.class);
    }

    @Override
    public Quai findNavette(long navette) {
        Query recupererNavetteQuiStationne = this.em.createQuery("SELECT Q.id FROM Quai Q JOIN Q.stationne N WHERE N.id = :idNavette");
        recupererNavetteQuiStationne.setParameter("idStation", navette);
        return find((Long) recupererNavetteQuiStationne.getSingleResult());
    }

    @Override
    public Quai quaiDisponible(long idStation, Date dateReservation) throws  AucunQuaiException {
        List<Quai> quais = quaisDisponible(idStation, dateReservation);
        if(quais.size() <= 0 ) {
            throw new AucunQuaiException("Aucun quai disponible à la station " + idStation + " a la date du " + dateReservation);
        }
        return quais.get(0);
    }

    @Override
    public List<Quai> quaisDisponible(long idStation, Date dateReservation) {
        Query recupererNavetteQuiStationne = this.em.createQuery("SELECT Q FROM Quai Q JOIN Q.station S JOIN Q.reservee R WHERE S.id = :idStation AND NOT EXISTS (SELECT R FROM Reservation R JOIN R.voyage V WHERE V.dateArrivee > :dateJour AND V.dateArrivee < :dateFinJour)");
        recupererNavetteQuiStationne.setParameter("idStation", idStation);
        recupererNavetteQuiStationne.setParameter("dateJour", new Date(dateReservation.getYear(), dateReservation.getMonth(), dateReservation.getDate(), 0, 0, 0));
        recupererNavetteQuiStationne.setParameter("dateFinJour", new Date(dateReservation.getYear(), dateReservation.getMonth(), dateReservation.getDate(), 23, 59, 59));
        return (List<Quai>) recupererNavetteQuiStationne.getResultList();
    }

    @Override
    public Navette navetteDisponible(long idStation, int nbPlaces) throws AucuneNavetteException {
        Query recupererNavetteQuiStationne = this.em.createQuery("SELECT N FROM Navette N JOIN N.stationeSur Q, Q.station S WHERE S.id = :idStation AND N.nbVoyagesDepuisDernierEntretien < :nbVoyagesEntretiens AND N.nbPlace = :nbPlaces");
        recupererNavetteQuiStationne.setParameter("idStation", idStation);
        recupererNavetteQuiStationne.setParameter("nbVoyagesEntretiens", StationFacade.NB_VOYAGES_ENTRETIENS);
        recupererNavetteQuiStationne.setParameter("nbPlaces", nbPlaces);
        List<Navette> navettes = recupererNavetteQuiStationne.getResultList();
                
        if (navettes.size() == 0) {
            throw new AucuneNavetteException("Aucune navette n'est disponible");
        }
        
        boolean valide = false;
        for (int i = 0; i < navettes.size() && !valide; i++) {
            Operation operation = navettes.get(i).getDerniereOperation();
            valide = operation.isTerminee();
        }

        return navettes.get(0); // TODO Exception pas de navette dispo
    }

}
