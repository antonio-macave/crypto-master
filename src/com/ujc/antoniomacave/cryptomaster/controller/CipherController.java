/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujc.antoniomacave.cryptomaster.controller;

import com.ujc.antoniomacave.cryptomaster.view.CifraCesar;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

/**
 *
 * @author Macave
 */
public class CipherController {
    
    private final CifraCesar cifraCesarView;
    private Integer actionMode = null; // 0 = Encrypt; 1 = Decrypt
    
    private JRadioButton encrp;
    private JRadioButton decrp;
    
    public CipherController(CifraCesar cifraCesarView) {
        this.cifraCesarView = cifraCesarView;
        setListeners();
    }
    
    private void setListeners() {
        cifraCesarView.getBtnCopy().setEnabled(true);
        cifraCesarView.getBtnCopy().addActionListener((ActionEvent e) -> {
            String text = cifraCesarView.getBtnCopy().getText();
            copyText(text);
        });
        encrp = cifraCesarView.getRadioEncypt();
        decrp = cifraCesarView.getRadioDecrypt();
        encrp.addActionListener(radioListener);
        decrp.addActionListener(radioListener);
    }
    
    private void copyText(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
    
    
    private void enterEncryptMode() {
        actionMode = 0;
        JButton btnEncrypt = cifraCesarView.getBtnEncrypt();
        JLabel plainText = cifraCesarView.getPlainTexLabel();
        JLabel cipherText = cifraCesarView.getLabelCipherText();
        
        btnEncrypt.setText("Encriptar");
        plainText.setText("Texto plano");
        cipherText.setText("Texto cifrado");
        
        System.out.println("Encrypt mode");
        
    }
    
    private void enterDecryptMode() {
        actionMode = 1;
        JButton btnEncrypt = cifraCesarView.getBtnEncrypt();
        JLabel plainText = cifraCesarView.getPlainTexLabel();
        JLabel cipherText = cifraCesarView.getLabelCipherText();
        
        btnEncrypt.setText("Desencriptar");
        plainText.setText("Texto cifrado");
        cipherText.setText("Texto plano");
    }
    
    private final ActionListener radioListener = (ActionEvent e) -> {
        if (e.getActionCommand().equals(encrp.getActionCommand())){
            enterEncryptMode();
        } else if (e.getActionCommand().equals(decrp.getActionCommand())) {
            enterDecryptMode();
        }
    };
    
}
