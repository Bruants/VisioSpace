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
public class AucuneOperationException extends Exception {

    /**
     * Creates a new instance of <code>AucuneOperationException</code> without
     * detail message.
     */
    public AucuneOperationException() {
    }

    /**
     * Constructs an instance of <code>AucuneOperationException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public AucuneOperationException(String msg) {
        super(msg);
    }
}
