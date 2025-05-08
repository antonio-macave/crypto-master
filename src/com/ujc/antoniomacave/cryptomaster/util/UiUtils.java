package com.ujc.antoniomacave.cryptomaster.util;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 *
 * @author Macave
 */
public class UiUtils {
    /*
    Modifies labels according to the selected RadioButton
    */
    public static void setActionMode(boolean isEncryptMode, JButton button, JLabel inputLabel, JLabel outputLabel, JTextArea outputField) {
        if (isEncryptMode) {
            button.setText("Encriptar");
            inputLabel.setText("Texto plano");
            outputLabel.setText("Texto cifrado");
            outputField.setText("");
        } else {
            button.setText("Decriptar");
            inputLabel.setText("Texto cifrado");
            outputLabel.setText("Texto plano");
            outputField.setText("");
        }
    }
    
}
