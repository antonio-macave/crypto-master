/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujc.antoniomacave.cryptomaster.controller;

import com.ujc.antoniomacave.cryptomaster.util.Identifier;
import com.ujc.antoniomacave.cryptomaster.util.StringUtils;
import com.ujc.antoniomacave.cryptomaster.util.VigenereCipherUtils;
import com.ujc.antoniomacave.cryptomaster.view.CiphersForm;
import com.ujc.antoniomacave.cryptomaster.view.VigenereForm;
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
public class VigenereCipherController {
    
    private Integer actionMode = null; // 0 = Encrypt; 1 = Decrypt 
    private JTextField keyField;
    private JTextArea inputField;
    private JLabel inputLabel;
    private JTextArea outputField;
    private JLabel outputLabel;
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
        inputLabel = form.getInputLabel();
        outputLabel = form.getOutputLabel();
        keyField = form.getKeyField();
        inputField = form.getInputTextField();
        outputField = form.getOutputTxtField();
        outputField.getDocument().addDocumentListener(documentListener);
        encryptRadio = form.getRadioEncrypt();
        decryptRadio = form.getRadioDecrypt();
        btnCopy = form.getBtnCopy();
        btnCopy.addActionListener((ActionEvent e) -> {
            if (!outputField.getText().trim().isEmpty()) {
                copyText(outputField.getText());
            }
        });
        btnEncrypt = form.getBtnEncrypt();
        btnEncrypt.addActionListener(encyptListener);
        encryptRadio.addActionListener(actionListener);
        decryptRadio.addActionListener(actionListener);
    }
    
    
    private void setEncryptMode() {
        actionMode = 0;
        inputLabel.setText("Texto plano");
        outputLabel.setText("Texto cifrado");
        btnEncrypt.setText("Encriptar");
    }
    
    private void setDecryptMode() {
        actionMode = 1;
        inputLabel.setText("Texto cifrado");
        outputLabel.setText("Texto plano");
        btnEncrypt.setText("Decriptar");
    }
    
    private void copyText(String text) {
        StringUtils.copyText(text);
    }
    
    private final ActionListener encyptListener = (ActionEvent e) -> {
        String key = keyField.getText();
        if (isAnyModeSelected()) {
            if (isKeyValid(key)) {
                String content = inputField.getText();
                if (hasInputContent(content)) {
                    if (actionMode == 0) { //ENCYPT MODE
                        String cipherText = VigenereCipherUtils.encrypt(content, key);
                        outputField.setText(cipherText);
                    } else if (actionMode == 1) { //DECRYPT MODE
                        String plainText = VigenereCipherUtils.decrypt(content, key);
                        outputField.setText(plainText);
                    }
                }
            }
        }
    };
    
    private boolean isKeyValid(String text) {
        if (text.trim().isEmpty()) {
            JOptionPane.showMessageDialog(vigenereFormView, "Por favor, insira a chave.");
            return false;
        }
        return true;
    }
    
    private boolean hasInputContent(String content) {
        if (content.trim().isEmpty()) {
            JOptionPane.showMessageDialog(vigenereFormView, "Por favor, insira o texto que deseja cifrar/decifrar.");
            return false;
        }
        return true;
    }
    
    private boolean isAnyModeSelected() {
        if(actionMode != null) {
            return true;
        } else {
            JOptionPane.showMessageDialog(vigenereFormView, "Por favor, selecione o que deseja fazer.");
            return false;
        }
    }
    
    private final ActionListener actionListener = (ActionEvent e) -> {
        if (e.getActionCommand().equals(encryptRadio.getActionCommand())) {
            setEncryptMode();
        } else if (e.getActionCommand().equals(decryptRadio.getActionCommand())){
            setDecryptMode();
        }
    };
    
    private final DocumentListener documentListener = new DocumentListener() {

        private void updateCopyButtonState() {
            btnCopy.setEnabled(!inputField.getText().trim().isEmpty());
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
            CiphersForm ciphersForm = new CiphersForm(Identifier.APP_TITLE);
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
