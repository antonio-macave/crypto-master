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

/**
 *
 * @author Macave
 */
public class CipherController {
    
    private final CifraCesar cifraCesarView;
    
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
    }
    
    private void copyText(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
    
}
