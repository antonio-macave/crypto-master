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
public class VigenereCipherUtils {
    
    public static String encrypt(String text, String key) {
        key = generateFullKey(text, key);
        return processText(text, key, true);
    }

    public static String decrypt(String text, String key) {
        key = generateFullKey(text, key);
        return processText(text, key, false);
    }

    private static String generateFullKey(String text, String key) {
        StringBuilder fullKey = new StringBuilder();
        int keyIndex = 0;

        for (int i = 0; i < text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                fullKey.append(Character.toUpperCase(key.charAt(keyIndex % key.length())));
                keyIndex++;
            } else {
                fullKey.append(text.charAt(i));
            }
        }
        return fullKey.toString();
    }

    private static String processText(String text, String key, boolean encrypting) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            char keyChar = key.charAt(i);

            if (Character.isLetter(currentChar)) {
                boolean isUpperCase = Character.isUpperCase(currentChar);
                char base = isUpperCase ? 'A' : 'a';

                int textCharIndex = currentChar - base;
                int keyCharIndex = keyChar - 'A';

                int shiftedIndex = encrypting
                        ? (textCharIndex + keyCharIndex) % 26
                        : (textCharIndex - keyCharIndex + 26) % 26;

                result.append((char) (base + shiftedIndex));
            } else {
                result.append(currentChar);
            }
        }

        return result.toString();
    }
}
