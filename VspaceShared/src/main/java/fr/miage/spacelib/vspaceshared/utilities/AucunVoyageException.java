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
public class AucunVoyageException extends Exception {
    
        private static final String MESSAGE_ERREUR_EXCEPTION
            = "L'identifiant du voyage est introuvable";

    /**
     * Crée une instance de IdentifiantVoyageException
     * avec un message par défaut
     */
    public AucunVoyageException() {
        super(MESSAGE_ERREUR_EXCEPTION);
    }

    /**
     * Crée une instance de IdentifiantVoyageException
     * @param msg message de l'exception
     */
    public AucunVoyageException(String msg) {
        super(msg);
    }
}
