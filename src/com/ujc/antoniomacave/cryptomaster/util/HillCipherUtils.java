/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ujc.antoniomacave.cryptomaster.util;

import javax.swing.JOptionPane;

/**
 *
 * @author Macave
 */
public class HillCipherUtils {

    public static String encrypt(String text, int[][] keyMatrix) {
        text = prepareText(text);
        validateKeyMatrix(keyMatrix);
        return processText(text, keyMatrix, true);
    }

    public static String decrypt(String text, int[][] keyMatrix) {
        int[][] inverseKey = getInverseMatrix(keyMatrix);
        return processText(text, inverseKey, false);
    }

    private static String prepareText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        if (text.length() % 2 != 0) {
            text += "X";
        }
        return text;
    }

    private static void validateKeyMatrix(int[][] matrix) {
        int det = (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]) % 26;
        if (det < 0) det += 26;

        if (gcd(det, 26) != 1) {
            JOptionPane.showMessageDialog(null, "Matriz-chave inválida: Impossível deterinar o módulo de 26.", "Erro na matriz", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Invalid key matrix: determinant not invertible modulo 26.");
        }
    }

    private static String processText(String text, int[][] matrix, boolean encrypting) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            int[] vector = {
                text.charAt(i) - 'A',
                text.charAt(i + 1) - 'A'
            };

            int[] transformed = {
                (matrix[0][0] * vector[0] + matrix[0][1] * vector[1]) % 26,
                (matrix[1][0] * vector[0] + matrix[1][1] * vector[1]) % 26
            };

            result.append((char) ('A' + transformed[0]));
            result.append((char) ('A' + transformed[1]));
        }

        return result.toString();
    }

    private static int[][] getInverseMatrix(int[][] matrix) {
        int det = (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]) % 26;
        if (det < 0) det += 26;

        int detInv = modInverse(det, 26);
        int[][] inverse = new int[2][2];

        inverse[0][0] = matrix[1][1];
        inverse[0][1] = -matrix[0][1];
        inverse[1][0] = -matrix[1][0];
        inverse[1][1] = matrix[0][0];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                inverse[i][j] = (inverse[i][j] * detInv) % 26;
                if (inverse[i][j] < 0) inverse[i][j] += 26;
            }
        }

        return inverse;
    }

    private static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) return x;
        }
        
        JOptionPane.showMessageDialog(null, "Nenhum módulo inverso encontrado.", "Erro na matriz", JOptionPane.ERROR_MESSAGE);
        throw new ArithmeticException("No modular inverse found.");
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int[][] parseMatrixFromInput(String input) {
        String[] rows = input.trim().split(";");
        if (rows.length != 2) {
            JOptionPane.showMessageDialog(null, "Cada linha na deve conter 2 valores.", "Erro na matriz", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Matrix must have 2 rows.");
        }

        int[][] matrix = new int[2][2];
        for (int i = 0; i < 2; i++) {
            String[] values = rows[i].trim().split(",");
            if (values.length != 2) {
                JOptionPane.showMessageDialog(null, "Cada linha deve conter 2 valores.", "Erro na matriz", JOptionPane.ERROR_MESSAGE);
                throw new IllegalArgumentException("Each row must have 2 values.");
            }

            for (int j = 0; j < 2; j++) {
                matrix[i][j] = Integer.parseInt(values[j].trim());
            }
        }

        return matrix;
    }

}
