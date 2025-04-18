/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujc.antoniomacave.cryptomaster.controller;

import com.ujc.antoniomacave.cryptomaster.view.CiphersForm;
import com.ujc.antoniomacave.cryptomaster.view.VigenereForm;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author Macave
 */
public class VigenereCipherController {
    
    private VigenereForm vigenereFormView;
    
    public VigenereCipherController(VigenereForm vigenereFormView) {
        this.vigenereFormView = vigenereFormView;
        initComps();
    }
    
    private void initComps() {
        vigenereFormView.addWindowListener(windowListener);
    }
    
    
    private final WindowListener windowListener = new WindowListener() {

        @Override
        public void windowOpened(WindowEvent e) {
            
        }

        @Override
        public void windowClosing(WindowEvent e) {
            
        }

        @Override
        public void windowClosed(WindowEvent e) {
            CiphersForm ciphersForm = new CiphersForm("Cifras");
            CiphersController ciphersController = new CiphersController(ciphersForm);
        }

        @Override
        public void windowIconified(WindowEvent e) {
            
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            
        }

        @Override
        public void windowActivated(WindowEvent e) {
            
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            
        }
    };
}
