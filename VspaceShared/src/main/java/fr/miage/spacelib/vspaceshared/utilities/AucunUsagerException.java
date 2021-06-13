/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.vspaceshared.utilities;

/**
 *
 * @author apouz
 */
public class AucunUsagerException extends Exception {

        private static final String MESSAGE_ERREUR_EXCEPTION
            = "L'identifiant du quai est introuvable";
    
    /**
     * Crée une instance de QuaiInvalideException
     * avec un message par défaut
     */
    public AucunUsagerException() {
        super(MESSAGE_ERREUR_EXCEPTION);
    }

    /**
     * Crée une instance de QuaiInvalideException
     * avec l'identifiant du quai
     * @param identifiant identidiant du quai 
     */
    public AucunUsagerException(String identifiant) {
        super(MESSAGE_ERREUR_EXCEPTION + "\n" + identifiant);
    }
}
