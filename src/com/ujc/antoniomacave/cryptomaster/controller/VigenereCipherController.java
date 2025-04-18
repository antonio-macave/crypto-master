/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujc.antoniomacave.cryptomaster.controller;

import com.ujc.antoniomacave.cryptomaster.util.StringUtils;
import com.ujc.antoniomacave.cryptomaster.view.CiphersForm;
import com.ujc.antoniomacave.cryptomaster.view.VigenereForm;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Macave
 */
public class VigenereCipherController {
    
    private Integer actionMode = null; // 0 = Encrypt; 1 = Decrypt 
    private JTextField keyField;
    private JTextArea inputField;
    private JTextArea outputField;
    private JButton btnEncrypt;
    private JButton btnCopy;
    private JRadioButton encryptRadio;
    private JRadioButton decryptRadio;
    
    private final VigenereForm vigenereFormView;
    
    public VigenereCipherController(VigenereForm vigenereFormView) {
        this.vigenereFormView = vigenereFormView;
        initComps(vigenereFormView);
    }
    
    private void initComps(VigenereForm form) {
        form.addWindowListener(windowListener);
        keyField = form.getKeyField();
        inputField = form.getInputTextField();
        outputField = form.getOutputTxtField();
        encryptRadio = form.getRadioEncrypt();
        decryptRadio = form.getRadioDecrypt();
        btnCopy = form.getBtnCopy();
        btnEncrypt = form.getBtnEncrypt();
    }
    
    
    private void setEncryptMode() {
        actionMode = 0;
    }
    
    private void setDecryptMode() {
        actionMode = 1;
    }
    
    private void copyText(String text) {
        StringUtils.copyText(text);
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
