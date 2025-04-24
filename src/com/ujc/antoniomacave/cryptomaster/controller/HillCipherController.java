/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujc.antoniomacave.cryptomaster.controller;

import com.ujc.antoniomacave.cryptomaster.view.HillForm;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Macave
 */
public class HillCipherController {
    
    private HillForm hillFormView;
    
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
    }
    
}
