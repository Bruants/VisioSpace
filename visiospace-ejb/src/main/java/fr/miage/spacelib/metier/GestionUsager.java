/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.metier;

import fr.miage.spacelib.facades.UsagerFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author AlexisVivier
 */
@Stateless
public class GestionUsager implements GestionUsagerLocal {

    @EJB(beanName = "UsagerEJB")
    private UsagerFacadeLocal usager;

    /**
     * Get the value of usager
     *
     * @return the value of usager
     */
    public UsagerFacadeLocal getUsager() {
        return usager;
    }

    /**
     * Set the value of usager
     *
     * @param usager new value of usager
     */
    public void setUsager(UsagerFacadeLocal usager) {
        this.usager = usager;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
