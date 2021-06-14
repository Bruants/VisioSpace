/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.facades.MecanicienFacadeLocal;
import fr.miage.spacelib.facades.OperationFacadeLocal;
import fr.miage.spacelib.vspaceshared.utilities.AucuneNavetteException;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author AlexisVivier
 */
@Stateless
public class GestionMecanicien implements GestionMecanicienLocal {

    @EJB(beanName = "MecanicienEJB")
    private MecanicienFacadeLocal mecanicien;
    
    @EJB(beanName = "OperationMecanicienEJB")
    private OperationFacadeLocal operation;

    /**
     * Get the value of operation
     *
     * @return the value of operation
     */
    public OperationFacadeLocal getOperation() {
        return operation;
    }

    /**
     * Set the value of operation
     *
     * @param operation new value of operation
     */
    public void setOperation(OperationFacadeLocal operation) {
        this.operation = operation;
    }


    /**
     * Get the value of mecanicien
     *
     * @return the value of mecanicien
     */
    public MecanicienFacadeLocal getMecanicien() {
        return mecanicien;
    }

    /**
     * Set the value of mecanicien
     *
     * @param mecanicien new value of mecanicien
     */
    public void setMecanicien(MecanicienFacadeLocal mecanicien) {
        this.mecanicien = mecanicien;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    
    /**
     * Crée une opération de révision
     * 
     * @param navette navette à réviser
     * @return l'id du quai ou se situe la navette à réviser
     * @throws AucuneNavetteException -> si l'identifiant n'existe pas
     */
    @Override
    public long debutRevision(long navette){
        return 0L;
    }

    /**
     * Termine une révision
     * 
     * @param navette navette révisée
     * @throws AucuneNavetteException -> si l'identifiant n'existe pas
     */
    @Override
    public void clotureRevision(long navette){
    }
    
    
    
}
