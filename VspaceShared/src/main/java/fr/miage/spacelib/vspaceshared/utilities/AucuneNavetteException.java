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
public class AucuneNavetteException extends Exception {
    
    
    private static final String MESSAGE_ERREUR_EXCEPTION
            = "L'identifiant de la navette est introuvable";

    /**
     * Crée une instance de AucuneNavetteException
     * avec un message par défaut
     */
    public AucuneNavetteException() {
        super(MESSAGE_ERREUR_EXCEPTION);
    }

    /**
     * Crée une instance de AucuneNavetteException
     * avec l'identifiant de la navette
     * @param identifiant identidiant de la navette 
     */
    public AucuneNavetteException(String identifiant) {
        super(MESSAGE_ERREUR_EXCEPTION + "\n" + identifiant);
    }
}
