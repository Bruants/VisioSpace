/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.utilities;

/**
 * @author audric.pouzelgues
 */
public class CoordonneesException extends Exception {

    
    private static final String MESSAGE_ERREUR_EXCEPTION = "Les coordonnées sont incorrectes !";
    
    /**
     * Crée une instance de CoordonneesException
     * avec un message par défaut
     */
    public CoordonneesException() {
        super(MESSAGE_ERREUR_EXCEPTION);
    }

    /**
     * Crée une instance de CoordonneesException
     * avec un message en argument
     * @param coordonnees qui provoquent l'exception
     */
    public CoordonneesException(String coordonnees) {
        super(MESSAGE_ERREUR_EXCEPTION + "\n" + coordonnees);
    }
    
}
