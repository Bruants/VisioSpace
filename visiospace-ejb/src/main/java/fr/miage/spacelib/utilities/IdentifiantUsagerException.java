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
public class IdentifiantUsagerException extends Exception {

    
    private static final String MESSAGE_ERREUR_EXCEPTION
            = "L'identifiant de l'usager est introuvable";

    /**
     * Crée une instance de IdentifiantNavetteException
     * avec un message par défaut
     */
    public IdentifiantUsagerException() {
        super(MESSAGE_ERREUR_EXCEPTION);
    }

    /**
     * Crée une instance de NombreNavettesException
     * avec l'identifiant de la navette
     * @param identifiant identidiant de la navette 
     */
    public IdentifiantUsagerException(String identifiant) {
        super(MESSAGE_ERREUR_EXCEPTION + "\n" + identifiant);
    }
}
