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
public class NombrePassagersInvalideException extends Exception {

    private static final String MESSAGE_ERREUR_EXCEPTION = "Le nombre de passagers est incorrect !";
    
    /**
     * Crée une instance de NombrePassagersInvalideException
     * avec un message par défaut
     */
    public NombrePassagersInvalideException() {
        super(MESSAGE_ERREUR_EXCEPTION);
    }

    /**
     * Crée une instance de NombrePassagersInvalideException
     * avec un message en argument
     * @param message message plus détaillé
     */
    public NombrePassagersInvalideException(String message) {
        super(MESSAGE_ERREUR_EXCEPTION + "\n" + message);
    }
}
