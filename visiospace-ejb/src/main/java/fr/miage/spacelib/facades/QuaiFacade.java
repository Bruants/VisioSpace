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
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
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

    /**
     * Retourne le quai d'une navette
     * @param navette
     * @return Le quai de la navette
     */
    @Override
    public Quai findNavette(long navette) {
        Query recupererNavetteQuiStationne = this.em.createQuery("SELECT Q.id FROM Quai Q JOIN Q.stationne N WHERE N.id = :idNavette");
        recupererNavetteQuiStationne.setParameter("idStation", navette);
        return find((Long) recupererNavetteQuiStationne.getSingleResult());
    }

    /**
     * @param idStation
     * @param dateReservation
     * @return Un quai disponible dans la station à la date donnée
     * @throws AucunQuaiException 
     */
    @Override
    public Quai quaiDisponible(long idStation, Date dateReservation) throws AucunQuaiException {
        List<Quai> quais = quaisDisponible(idStation, dateReservation);
        if(quais.size() <= 0) {
            throw new AucunQuaiException("Aucun quai disponible à la station " + idStation + " a la date du " + dateReservation);

        }
        return quais.get(0);
    }

    /**
     * @param idStation
     * @param dateReservation
     * @return Un ensemble de quais disponibles à la date spécifiée pour la station donnée
     */
    @Override
    public List<Quai> quaisDisponible(long idStation, Date dateReservation) {
        Query quaisAvecReservation = this.em.createQuery("SELECT QA FROM Reservation R JOIN R.voyage V JOIN R.arrivee QA JOIN QA.station S WHERE V.dateArrivee >= :dateJour AND V.terminee = false AND S.id = :idStation");
        quaisAvecReservation.setParameter("dateJour", new Date(dateReservation.getYear(), dateReservation.getMonth(), dateReservation.getDate(), 0, 0, 0));
        quaisAvecReservation.setParameter("idStation", idStation);
        List<Quai> resultatsReservation = (List<Quai>)quaisAvecReservation.getResultList();

        Query quaisSansReservation = this.em.createQuery("SELECT Q FROM Quai Q JOIN Q.station S WHERE S.id = :idStation AND Q.stationne IS NULL ");
        quaisSansReservation.setParameter("idStation", idStation);
        List<Quai> resultatsSansReservation = (List<Quai>)quaisSansReservation.getResultList();

        for(int i = 0 ; i < resultatsReservation.size() ; i++) {
            for(int j = 0 ; j < resultatsSansReservation.size() ; j++) {
                if(Objects.equals(resultatsReservation.get(i).getId(), resultatsSansReservation.get(j).getId())) {
                    resultatsReservation.remove(i);
                    resultatsSansReservation.remove(j);
                    j = resultatsSansReservation.size()+1;
                }
            }
        }
        return resultatsSansReservation;
    }

    /**
     * @param idStation
     * @param nbPlaces
     * @return Une navette disponible avec le nombre de places nécessaires
     * @throws AucuneNavetteException 
     */
    @Override
    public Navette navetteDisponible(long idStation, int nbPlaces) throws AucuneNavetteException {
        Query recupererNavetteQuiStationne = this.em.createQuery("SELECT N FROM Navette N JOIN N.stationeSur Q JOIN Q.station S WHERE S.id = :idStation AND N.nbVoyagesDepuisDernierEntretien < :nbVoyagesEntretiens AND N.nbPlace >= :nbPlaces ORDER BY N.nbPlace ASC");

        recupererNavetteQuiStationne.setParameter("idStation", idStation);
        recupererNavetteQuiStationne.setParameter("nbVoyagesEntretiens", StationFacade.NB_VOYAGES_ENTRETIENS);
        recupererNavetteQuiStationne.setParameter("nbPlaces", nbPlaces);
        List<Navette> navettes = recupererNavetteQuiStationne.getResultList();
        if (navettes.isEmpty()) {
            throw new AucuneNavetteException("Aucune navette n'est disponible");
        }
        
        boolean valide = false;
        int i;
        
        for (i = 0; i < navettes.size() && !valide; i++) {
            Operation operation = navettes.get(i).getDerniereOperation();
            
            valide = operation == null ? true : operation.isTerminee();
        }
        
        if (!valide) {
            throw new AucuneNavetteException("Aucune navette n'est disponible");
        }
        
        return navettes.get(i-1);
    }

}
