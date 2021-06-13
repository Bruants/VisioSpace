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
     * avec l'identifiant du quai
     * @param identifiant identidiant du quai 
     */
    public AucunQuaiException(String identifiant) {
        super(MESSAGE_ERREUR_EXCEPTION + "\n" + identifiant);
    }
}
