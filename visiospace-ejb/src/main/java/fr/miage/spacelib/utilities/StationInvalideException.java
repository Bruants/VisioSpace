/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.utilities;

/**
 *
 * @author apouz
 */
public class IdentifiantStationException extends Exception {

    
    private static final String MESSAGE_ERREUR_EXCEPTION
            = "L'identifiant de la station est introuvable";

    /**
     * Crée une instance de IdentifiantStationException
     * avec un message par défaut
     */
    public IdentifiantStationException() {
        super(MESSAGE_ERREUR_EXCEPTION);
    }

    /**
     * Crée une instance de IdentifiantStationException
     * avec l'identifiant de la station
     * @param identifiant identidiant de la station
     */
    public IdentifiantStationException(String identifiant) {
        super(MESSAGE_ERREUR_EXCEPTION + "\n" + identifiant);
    }
}
