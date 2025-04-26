/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujc.antoniomacave.cryptomaster.util;

/**
 *
 * @author Macave
 */
public class CeaserCipherUtils {
    
    public static String encrypt(String text, int shift) {
        return processText(text, shift, true);
    }

    public static String decrypt(String text, int shift) {
        return processText(text, shift, false);
    }

    private static String processText(String text, int shift, boolean encrypting) {
        StringBuilder result = new StringBuilder();
        text = text.toUpperCase();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                int original = mapCharToInt(c);
                int shifted;

                if (encrypting) {
                    shifted = (original + shift) % 26;
                } else {
                    shifted = (original - shift + 26) % 26;
                }

                result.append(mapIntToChar(shifted));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    private static int mapCharToInt(char c) {
        int value = c - 'A' + 1;
        return value % 26;
    }

    private static char mapIntToChar(int i) {
        if (i == 0) return 'Z';
        return (char) ('A' + i - 1);
    }
    
}