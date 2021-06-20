/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.vspaceshared.utilities;

/**
 * @author Audric Pouzelgues, Kevin Sannac, Alexis Vivier, 
 */
public class CoordonneesInvalideException extends Exception {

    
    private static final String MESSAGE_ERREUR_EXCEPTION = "Les coordonnées sont incorrectes !";
    
    /**
     * Crée une instance de CoordonneesInvalideException
     * avec un message par défaut
     */
    public CoordonneesInvalideException() {
        super(MESSAGE_ERREUR_EXCEPTION);
    }

    /**
     * Crée une instance de CoordonneesInvalideException
     * avec un message en argument
     * @param coordonnees qui provoquent l'exception
     */
    public CoordonneesInvalideException(String coordonnees) {
        super(MESSAGE_ERREUR_EXCEPTION + "\n" + coordonnees);
    }
    
}
