/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujc.antoniomacave.cryptomaster.controller;

import com.ujc.antoniomacave.cryptomaster.view.CifraCesarForm;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Macave
 */
public class CeaserCipherController {
    
    private final CifraCesarForm cifraCesarView;
    private Integer actionMode = null; // 0 = Encrypt; 1 = Decrypt
    
    private int key;
    
    private JRadioButton encryptRadio;
    private JRadioButton decryptRadio;
    private JTextField keyTextArea;
    
    public CeaserCipherController(CifraCesarForm cifraCesarView) {
        this.cifraCesarView = cifraCesarView;
        setListeners();
    }
    
    private void setListeners() {
        cifraCesarView.getBtnCopy().setEnabled(true);
        cifraCesarView.getBtnCopy().addActionListener((ActionEvent e) -> {
            String text = cifraCesarView.getBtnCopy().getText();
            copyText(text);
        });
        keyTextArea = cifraCesarView.getKey();
        encryptRadio = cifraCesarView.getRadioEncypt();
        decryptRadio = cifraCesarView.getRadioDecrypt();
        encryptRadio.addActionListener(radioListener);
        decryptRadio.addActionListener(radioListener);
        cifraCesarView.getBtnEncrypt().addActionListener(encryptListener);
    }
    
    private void copyText(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
    
    private boolean isKeyValid(String text) throws NumberFormatException {
        try {
            if (!text.isEmpty()) {
                int shift = Integer.parseInt(text);
                if ((shift >= 0) && (shift <= 26)) {
                    key = Integer.parseInt(text.trim());
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this.cifraCesarView, "A chave deve ser entre 0-26.");
                }
            } else {
                JOptionPane.showMessageDialog(this.cifraCesarView, "Insira a chave.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this.cifraCesarView, "A chave deve ser composta apenas por números.");
        }
        return false;
    }
    
    private void enterEncryptMode() {
        actionMode = 0;
        JButton btnEncrypt = cifraCesarView.getBtnEncrypt();
        JLabel plainText = cifraCesarView.getPlainTexLabel();
        JLabel cipherText = cifraCesarView.getLabelCipherText();
        
        btnEncrypt.setText("Encriptar");
        plainText.setText("Texto plano");
        cipherText.setText("Texto cifrado");
   
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
    
    private final ActionListener encryptListener = (ActionEvent e) -> {
        isKeyValid(keyTextArea.getText());
    };
    
    private final ActionListener radioListener = (ActionEvent e) -> {
        if (e.getActionCommand().equals(encryptRadio.getActionCommand())){
            enterEncryptMode();
        } else if (e.getActionCommand().equals(decryptRadio.getActionCommand())) {
            enterDecryptMode();
        }
    };
 
}
