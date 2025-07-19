/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujc.antoniomacave.cryptomaster.controller;

import com.ujc.antoniomacave.cryptomaster.util.CeaserCipherUtils;
import com.ujc.antoniomacave.cryptomaster.util.Identifier;
import com.ujc.antoniomacave.cryptomaster.util.StringUtils;
import com.ujc.antoniomacave.cryptomaster.util.UiUtils;
import com.ujc.antoniomacave.cryptomaster.view.CifraCesarForm;
import com.ujc.antoniomacave.cryptomaster.view.CiphersForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Macave
 */
public class CeaserCipherController {
    
    private CifraCesarForm cifraCesarView;
    private Integer actionMode = null; // 0 = Encrypt; 1 = Decrypt
    
    private int key;
    
    private JRadioButton encryptRadio;
    private JRadioButton decryptRadio;
    private JTextField keyTextArea;
    private JTextArea inputField;
    private JTextArea outputField;
    private JButton copyButton;
    private JButton encryptButton;
    
    public CeaserCipherController(CifraCesarForm cifraCesarView) {
        this.cifraCesarView = cifraCesarView;
        setListeners();
    }
    
    private void setListeners() {
        cifraCesarView.addWindowListener(windowListener);
        copyButton = cifraCesarView.getBtnCopy();
        copyButton.addActionListener((ActionEvent e) -> {
            String text = outputField.getText();
            copyText(text);
        });
        encryptButton = cifraCesarView.getBtnEncrypt();
        encryptButton.addActionListener(encryptListener);
        keyTextArea = cifraCesarView.getKey();
        encryptRadio = cifraCesarView.getRadioEncypt();
        decryptRadio = cifraCesarView.getRadioDecrypt();
        inputField = cifraCesarView.getPlainTexField();
        outputField = cifraCesarView.getCipherTextArea();
        outputField.getDocument().addDocumentListener(docListener);
        encryptRadio.addActionListener(radioListener);
        decryptRadio.addActionListener(radioListener);
    }
    
    private void copyText(String text) {
        StringUtils.copyText(text);
        JOptionPane.showMessageDialog(cifraCesarView, "Texto copiado para a área de transferência!", "", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private boolean isKeyValid(String text) throws NumberFormatException {
        try {
            if (!text.isEmpty()) {
                int shift = Integer.parseInt(text);
                if ((shift >= 0) && (shift <= 26)) {
                    key = Integer.parseInt(text.trim());
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this.cifraCesarView, "A chave deve ser entre 0-26.","Chave", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this.cifraCesarView, "Insira a chave.", "Chave", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this.cifraCesarView, "A chave deve ser composta apenas por números.","Chave", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    private void enterEncryptMode() {
        actionMode = 0;
    
        JLabel inputLabel = cifraCesarView.getPlainTexLabel();
        JLabel outputLabel = cifraCesarView.getLabelCipherText();
        UiUtils.setActionMode(true, encryptButton, inputLabel, outputLabel, outputField);
    }
    
    private void enterDecryptMode() {
        actionMode = 1;
        JLabel inputLabel = cifraCesarView.getPlainTexLabel();
        JLabel outputLabel = cifraCesarView.getLabelCipherText();
        UiUtils.setActionMode(false, encryptButton, inputLabel, outputLabel, outputField);
    }
    
    private final ActionListener encryptListener = (ActionEvent e) -> {
        String keyText = keyTextArea.getText();
        String input = inputField.getText();
        if (UiUtils.isAnyActionModeSelected(cifraCesarView, actionMode)) {
            if (isKeyValid(keyText)) {
                int shift = Integer.parseInt(keyText.trim());
                if (actionMode == 0) { //ENCRYPT MODE
                    String cipherText = CeaserCipherUtils.encrypt(input, shift);
                    outputField.setText(cipherText);   
                } else if (actionMode == 1) { //DECRYPT MODE
                    String plainText = CeaserCipherUtils.decrypt(input, shift);
                    outputField.setText(plainText);
                }
            }
        }
    };
    
    private final ActionListener radioListener = (ActionEvent e) -> {
        if (e.getActionCommand().equals(encryptRadio.getActionCommand())){
            enterEncryptMode();
        } else if (e.getActionCommand().equals(decryptRadio.getActionCommand())) {
            enterDecryptMode();
        }
    };
    
    private final DocumentListener docListener = new DocumentListener() {
        
        private void updateCopyButtonState() {
            copyButton.setEnabled(!outputField.getText().trim().isEmpty());
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            updateCopyButtonState();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateCopyButtonState();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateCopyButtonState();
        }
    };
 
    private final WindowListener windowListener = new WindowListener() {

        @Override
        public void windowOpened(WindowEvent e) {
            
        }

        @Override
        public void windowClosing(WindowEvent e) {
            
        }

        @Override
        public void windowClosed(WindowEvent e) {
            new CiphersController(new CiphersForm(Identifier.APP_TITLE));
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
