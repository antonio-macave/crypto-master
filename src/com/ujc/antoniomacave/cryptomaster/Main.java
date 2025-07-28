/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujc.antoniomacave.cryptomaster;

import com.formdev.flatlaf.FlatLightLaf;
import com.ujc.antoniomacave.cryptomaster.controller.CiphersController;
import com.ujc.antoniomacave.cryptomaster.util.Identifier;
import com.ujc.antoniomacave.cryptomaster.view.CiphersForm;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Macave
 */
public class Main {

   
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc", 20);
        } catch (UnsupportedLookAndFeelException exception) {
            throw new RuntimeException("Error while setting up the look and feel.", exception);
        }
        new CiphersController(new CiphersForm(Identifier.APP_TITLE));
    }
    
}
