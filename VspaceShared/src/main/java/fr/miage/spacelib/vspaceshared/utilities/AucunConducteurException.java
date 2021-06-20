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
public class AucunConducteurException extends Exception {

    /**
     * Creates a new instance of <code>AucunConducteurException</code> without
     * detail message.
     */
    public AucunConducteurException() {
    }

    /**
     * Constructs an instance of <code>AucunConducteurException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AucunConducteurException(String msg) {
        super(msg);
    }
}
