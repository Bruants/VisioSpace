/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.utilities;

/**
 *
 * @author audric.pouzelgues
 */
public class IdentifiantVoyageException extends Exception {
    
        private static final String MESSAGE_ERREUR_EXCEPTION
            = "L'identifiant du voyage est introuvable";

    /**
     * Crée une instance de IdentifiantVoyageException
     * avec un message par défaut
     */
    public IdentifiantVoyageException() {
        super(MESSAGE_ERREUR_EXCEPTION);
    }

    /**
     * Crée une instance de IdentifiantVoyageException
     * avec l'identifiant de la navette
     * @param identifiant identidiant du voyage 
     */
    public IdentifiantVoyageException(String identifiant) {
        super(MESSAGE_ERREUR_EXCEPTION + "\n" + identifiant);
    }
}
