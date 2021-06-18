/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.vspaceshared.utilities;

/**
 *
 * @author Kevin
 */
public class NavetteIndisponibleException extends Exception {

    /**
     * Creates a new instance of <code>NavetteIndisponibleException</code>
     * without detail message.
     */
    public NavetteIndisponibleException() {
    }

    /**
     * Constructs an instance of <code>NavetteIndisponibleException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public NavetteIndisponibleException(String msg) {
        super(msg);
    }
}
