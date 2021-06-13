/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.vspaceshared.utilities;

/**
 *
 * @author audric.pouzelgues
 */
public class NombrePlacesInvalideException extends Exception {

    
    private static final String MESSAGE_ERREUR_EXCEPTION
        = "Le nombre de places pour la navette est incorrect, "
            + "veuillez saisir un nombre de navettes valide :"
            + " 2, 5, 10 ou 15 places";
        
    /**
     * Crée une instance de NombrePlacesInvalideException
     * avec un message par défaut
     */
    public NombrePlacesInvalideException() {
        super(MESSAGE_ERREUR_EXCEPTION);
    }

    /**
     * Crée une instance de NombrePlacesInvalideException
     * avec un message par défaut et en affichant le nombre de places saisit
     *
     * @param nbPlaces nombre de places saisit
     */
    public NombrePlacesInvalideException(String nbPlaces) {
        super(MESSAGE_ERREUR_EXCEPTION + "\n" + nbPlaces);
    }
}
