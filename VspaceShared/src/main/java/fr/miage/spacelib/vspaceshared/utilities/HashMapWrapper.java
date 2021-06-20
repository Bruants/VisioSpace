/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.spacelib.vspaceshared.utilities;

import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author Kevin
 */
public class HashMapWrapper {
    private HashMap<String,TreeMap<Long,Long>> basketMap;
    
    public HashMapWrapper(HashMap<String,TreeMap<Long,Long>> basketMap) {
        System.out.println(basketMap);
        this.basketMap = basketMap;
    }
}
