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
public class AucunQuaiException extends Exception {

    private static final String MESSAGE_ERREUR_EXCEPTION
            = "L'identifiant du quai est introuvable";

    /**
     * Crée une instance de QuaiInvalideException
     * avec un message par défaut
     */
    public AucunQuaiException() {
        super(MESSAGE_ERREUR_EXCEPTION);
    }

    /**
     * Crée une instance de QuaiInvalideException
     * @param msg message de l'exception
     */
    public AucunQuaiException(String msg) {
        super(msg);
    }
}
