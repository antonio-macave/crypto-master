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

    public static String encrypt(String plaintext, int[][] keyMatrix) {
        int size = keyMatrix.length;
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");

        // Pad the plaintext
        while (plaintext.length() % size != 0) {
            plaintext += "X";
        }

        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += size) {
            int[] block = new int[size];
            for (int j = 0; j < size; j++) {
                block[j] = plaintext.charAt(i + j) - 'A';
            }

            int[] result = multiplyMatrixVector(keyMatrix, block);

            for (int val : result) {
                ciphertext.append((char) ((val % 26) + 'A'));
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int[][] keyMatrix) {
        int[][] inverseMatrix = inverseKeyMatrix(keyMatrix);
        if (inverseMatrix == null) {
            JOptionPane.showMessageDialog(null, "A matriz não é invertível", "Erro na matriz", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Key matrix is not invertible modulo 26.");
        }

        int size = keyMatrix.length;
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += size) {
            int[] block = new int[size];
            for (int j = 0; j < size; j++) {
                block[j] = ciphertext.charAt(i + j) - 'A';
            }

            int[] result = multiplyMatrixVector(inverseMatrix, block);

            for (int val : result) {
                plaintext.append((char) (((val % 26 + 26) % 26) + 'A'));
            }
        }

        return plaintext.toString();
    }

    private static int[] multiplyMatrixVector(int[][] matrix, int[] vector) {
        int size = vector.length;
        int[] result = new int[size];

        for (int i = 0; i < size; i++) {
            result[i] = 0;
            for (int j = 0; j < size; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }

        return result;
    }

    private static int[][] inverseKeyMatrix(int[][] matrix) {
        int det = determinant(matrix);
        det = ((det % 26) + 26) % 26;
        int invDet = modularInverse(det, 26);
        if (invDet == -1) return null;

        int[][] adj = adjugate(matrix);
        int size = matrix.length;
        int[][] inverse = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                inverse[i][j] = (adj[i][j] * invDet) % 26;
                if (inverse[i][j] < 0) inverse[i][j] += 26;
            }
        }

        return inverse;
    }

    private static int modularInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) return x;
        }
        return -1;
    }

    // Determinant calculation for 2x2 and 3x3 only
    private static int determinant(int[][] matrix) {
        int size = matrix.length;

        if (size == 2) {
            return matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0];
        }

        if (size == 3) {
            return matrix[0][0]*(matrix[1][1]*matrix[2][2] - matrix[1][2]*matrix[2][1])
                 - matrix[0][1]*(matrix[1][0]*matrix[2][2] - matrix[1][2]*matrix[2][0])
                 + matrix[0][2]*(matrix[1][0]*matrix[2][1] - matrix[1][1]*matrix[2][0]);
        }
        JOptionPane.showMessageDialog(null, "Apenas matrizes 2x2 e 3x3 são suportadas.", "Erro na matriz", JOptionPane.ERROR_MESSAGE);
        throw new IllegalArgumentException("Only 2x2 and 3x3 matrices are supported.");
    }

    // Adjugate matrix for 2x2 and 3x3 only
    private static int[][] adjugate(int[][] matrix) {
        int size = matrix.length;
        int[][] adj = new int[size][size];

        if (size == 2) {
            adj[0][0] =  matrix[1][1];
            adj[0][1] = -matrix[0][1];
            adj[1][0] = -matrix[1][0];
            adj[1][1] =  matrix[0][0];
        } else if (size == 3) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    int[][] minor = new int[2][2];
                    for (int mi = 0, r = 0; mi < 3; mi++) {
                        if (mi == i) continue;
                        for (int mj = 0, c = 0; mj < 3; mj++) {
                            if (mj == j) continue;
                            minor[r][c++] = matrix[mi][mj];
                        }
                        r++;
                    }
                    adj[j][i] = ((minor[0][0]*minor[1][1] - minor[0][1]*minor[1][0]) * ((i + j) % 2 == 0 ? 1 : -1));
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Apenas matrizes 2x2 e 3x3 são suportadas.", "Erro na matriz", JOptionPane.ERROR_MESSAGE);
            throw new IllegalArgumentException("Only 2x2 and 3x3 matrices are supported.");
        }

        return adj;
    }
}
