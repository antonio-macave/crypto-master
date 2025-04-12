/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujc.antoniomacave.cryptomaster;

import com.formdev.flatlaf.FlatLightLaf;
import com.ujc.antoniomacave.cryptomaster.view.Ciphers;
import javax.swing.UIManager;

/**
 *
 * @author Macave
 */
public class Main {

   
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 20);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Ciphers ciphers = new Ciphers("Cifras");
    }
    
}
