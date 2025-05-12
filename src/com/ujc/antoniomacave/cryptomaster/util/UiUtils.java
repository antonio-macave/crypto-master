package com.ujc.antoniomacave.cryptomaster.util;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
        } else {
            button.setText("Decriptar");
            inputLabel.setText("Texto cifrado");
            outputLabel.setText("Texto plano");
        }
        outputField.setText("");
    }
    
    public static boolean isAnyActionModeSelected(JFrame frame, Integer actionMode) {
        if (frame != null) {
            if (actionMode != null) {
                return true;
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecione o que deseja fazer.");
            return false;
            }
        } else {
            return false;
        }
    }
}