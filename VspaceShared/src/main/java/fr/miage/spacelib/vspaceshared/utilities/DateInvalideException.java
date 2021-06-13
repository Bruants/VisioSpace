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
public class DateInvalideException extends Exception {

    
    private static final String MESSAGE_ERREUR_EXCEPTION = "La date saisie est invalide !";
    
    /**
     * Crée une instance de DateInvalideException
     * avec un message par défaut
     */
    public DateInvalideException() {
    }

    /**
     * Crée une instance de DateInvalideException
     * avec un message par défaut plus un message détaillé
     *
     * @param message le détail du message
     */
    public DateInvalideException(String message) {
        super(MESSAGE_ERREUR_EXCEPTION + "\n" + message);
    }
}
