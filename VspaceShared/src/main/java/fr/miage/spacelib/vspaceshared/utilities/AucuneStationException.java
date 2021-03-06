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
public class AucuneStationException extends Exception {

    
    private static final String MESSAGE_ERREUR_EXCEPTION
            = "L'identifiant de la station est introuvable";

    /**
     * Crée une instance de StationInvalideException
     * avec un message par défaut
     */
    public AucuneStationException() {
        super(MESSAGE_ERREUR_EXCEPTION);
    }

    /**
     * Crée une instance de StationInvalideException
     * avec l'identifiant de la station
     * @param identifiant identidiant de la station
     */
    public AucuneStationException(String identifiant) {
        super(MESSAGE_ERREUR_EXCEPTION + "\n" + identifiant);
    }
}
