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
public class NombreNavetteInvalideException extends Exception {

    
    private static final String MESSAGE_ERREUR_EXCEPTION = "Le nombre de navettes est incorrect !";
    
    /**
     * Crée une instance de NombreNavetteInvalideException
     * avec un message par défaut
     */
    public NombreNavetteInvalideException() {
        super(MESSAGE_ERREUR_EXCEPTION);
    }

    /**
     * Crée une instance de NombreNavetteInvalideException
     * avec un message en argument
     * @param message message plus détaillé
     */
    public NombreNavetteInvalideException(String message) {
        super(MESSAGE_ERREUR_EXCEPTION + "\n" + message);
    }
}
