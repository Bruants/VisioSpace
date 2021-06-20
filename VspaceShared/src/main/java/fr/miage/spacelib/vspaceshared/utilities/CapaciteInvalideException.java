/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.vspaceshared.utilities;

/**
 *
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
public class CapaciteInvalideException extends Exception {

        private static final String MESSAGE_ERREUR_EXCEPTION
        = "La capacité de la station est incorrecte";
        
    /**
     * Crée une instance de NombrePlacesInvalideException
     * avec un message par défaut
     */
    public CapaciteInvalideException() {
        super(MESSAGE_ERREUR_EXCEPTION);
    }

    /**
     * Crée une instance de NombrePlacesInvalideException
     * avec un message par défaut et en affichant le nombre de places saisit
     *
     * @param capacite capacité d'accueil de navettes de la station
     */
    public CapaciteInvalideException(String capacite) {
        super(MESSAGE_ERREUR_EXCEPTION + "\n" + capacite);
    }
}
