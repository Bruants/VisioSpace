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
public class NombreNavettesException extends Exception {

    
    private static final String MESSAGE_ERREUR_EXCEPTION = "Le nombre de navettes est incorrect !";
    
    /**
     * Crée une instance de NombreNavettesException
     * avec un message par défaut
     */
    public NombreNavettesException() {
    }

    /**
     * Crée une instance de NombreNavettesException
     * avec un message en argument
     * @param message 
     */
    public NombreNavettesException(String message) {
        super(message);
    }
}
