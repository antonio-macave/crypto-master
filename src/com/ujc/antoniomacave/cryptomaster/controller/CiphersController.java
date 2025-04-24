/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujc.antoniomacave.cryptomaster.controller;

import com.ujc.antoniomacave.cryptomaster.util.Identifier;
import com.ujc.antoniomacave.cryptomaster.view.CifraCesarForm;
import com.ujc.antoniomacave.cryptomaster.view.CiphersForm;
import com.ujc.antoniomacave.cryptomaster.view.HillForm;
import com.ujc.antoniomacave.cryptomaster.view.VigenereForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Macave
 */
public class CiphersController {
    private CiphersForm ciphersFormView;
    private JButton btnCeaser;
    private JButton btnHill;
    private JButton btnVigenere;
    
    
    public CiphersController(CiphersForm ciphersFormView) {
        this.ciphersFormView = ciphersFormView;
        initComps(ciphersFormView);
    }
    
    private void initComps(CiphersForm form) {
        btnCeaser = form.getBtnCeaserCipher();
        btnHill = form.getBtnHillCipher();
        btnVigenere = form.getBtnVigenereCipher();
        
        btnCeaser.addActionListener(actionListener);
        btnHill.addActionListener(actionListener);
        btnVigenere.addActionListener(actionListener);
    }
    
    private void openVigenereCipherForm() {
        VigenereForm vigenereForm = new VigenereForm();
        VigenereCipherController vigenereCipherController = new VigenereCipherController(vigenereForm);
        ciphersFormView.dispose();
    }
    
    private void openCeaserCipherForm() {
        CifraCesarForm cifraCesar = new CifraCesarForm(Identifier.CEASER_CIPHER);
        CeaserCipherController cipherController = new CeaserCipherController(cifraCesar);
        ciphersFormView.dispose();
    }
    
    private void openHillCipherForm() {
        HillForm hillForm = new HillForm(Identifier.HILL_CIPHER);
        HillCipherController hillController = new HillCipherController(hillForm);
        ciphersFormView.dispose();
    }
    
    private final ActionListener actionListener = (ActionEvent e) -> {
        if (ciphersFormView != null) {
            if (btnCeaser.getActionCommand().equals(e.getActionCommand())) {
                openCeaserCipherForm();
            } else if (e.getActionCommand().equals(btnHill.getActionCommand())) {
                openHillCipherForm();
            } else if (e.getActionCommand().equals(btnVigenere.getActionCommand())) {
                openVigenereCipherForm();   
            }
        }
    };
}
