/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujc.antoniomacave.cryptomaster.controller;

import com.ujc.antoniomacave.cryptomaster.util.Identifier;
import com.ujc.antoniomacave.cryptomaster.util.StringUtils;
import com.ujc.antoniomacave.cryptomaster.view.CiphersForm;
import com.ujc.antoniomacave.cryptomaster.view.HillForm;
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
public class HillCipherController {
    
    private final HillForm hillFormView;
    private Integer actionMode = null; //0 = Encryption Mode; 1 = Decryption Mode
    
    private JLabel inputLabel;
    private JLabel outputLabel;
    private JTextField keyField;
    private JTextArea inputField;
    private JTextArea outputField;
    private JRadioButton radioEncrypt;
    private JRadioButton radioDecrypt;
    private JButton btnEncypt;
    private JButton btnCopy;
    
    public HillCipherController(HillForm hillFormView) {
        this.hillFormView = hillFormView;
        initComps(hillFormView);
    }
    
    private void initComps(HillForm form) {
        inputLabel = form.getInputLabel();
        outputLabel = form.getOutputLabel();
        keyField = form.getKeyField();
        inputField = form.getInputField();
        outputField = form.getOutputField();
        radioEncrypt = form.getRadioEncrypt();
        radioDecrypt = form.getRadioDecrypt();
        btnEncypt = form.getBtnEncrypt();
        btnCopy = form.getBtnCopy();
        
        btnEncypt.addActionListener(encyptButtonListener);
        btnCopy.addActionListener(copyButtonListener);
        
        outputField.getDocument().addDocumentListener(documentListener);
        
        form.addWindowListener(windowListener);
        radioEncrypt.addActionListener(radioButtonsListener);
        radioDecrypt.addActionListener(radioButtonsListener);
    }
    
    private void setEncyptionMode() {
        actionMode = 0;
        inputLabel.setText("Texto plano");
        outputLabel.setText("Texto cifrado");
        btnEncypt.setText("Encriptar");
    }
    
    private void setDecyptionMode() {
        actionMode = 1;
        inputLabel.setText("Texto cifrado");
        outputLabel.setText("Texto plano");
        btnEncypt.setText("Decriptar");
    }
    
    private boolean isKeyValid(String key) {
        if (!key.trim().isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(hillFormView, "Por favor, insira uma chave.");
            return false;
        }
    }
    
    private boolean hasInputContent(String content) {
        if (!content.trim().isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(hillFormView, "Por favor, insira o conteúdo que deseja cifrar/decifrar.");
            return false;
        }
    }
    
    private final ActionListener radioButtonsListener = (ActionEvent e) -> {
           if (e.getActionCommand().equals(radioEncrypt.getActionCommand())) {
               setEncyptionMode();
           } else if (e.getActionCommand().equals(radioDecrypt.getActionCommand())) {
               setDecyptionMode();
           }
    };
    
    
    private final ActionListener encyptButtonListener = (ActionEvent) -> {
        String key = keyField.getText();
        String inputContent = inputField.getText();
        if(hasInputContent(inputContent)) {
            if (isKeyValid(key)) {
                
            }
        } 
    };
    
    private final ActionListener copyButtonListener = (ActionEvent e)-> {
        String text = outputField.getText();
        StringUtils.copyText(text);
    };
    
    private final DocumentListener documentListener = new DocumentListener() {
        
        private void updateCopyButtonState() {
            btnCopy.setEnabled(!outputField.getText().trim().isEmpty());
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
