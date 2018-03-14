package com.netcracker.adlitsov.homework3;

import java.util.Arrays;
import java.util.Random;

public class Task5 {

    public static double DELTA = 1e-5;

    public static void partA() {
        final int rows = 8, columns = 8;
        final int min = 1, max = 99;
        int[][] matrix = generateTwoDimRandIntArrayAndShow(rows, columns, min, max);

        long primarySum = 0, primaryMult = 1;
        for (int i = 0; i < rows; i++) {
            primarySum += matrix[i][i];
            primaryMult *= matrix[i][i];
        }

        System.out.println("Primary diagonal elements sum: " + primarySum + ", mult: " + primaryMult);

        long secondarySum = 0, secondaryMult = 1;
        for (int i = 0; i < rows; i++) {
            primarySum += matrix[i][columns - i - 1];
            primaryMult *= matrix[i][columns - i - 1];
        }

        System.out.println("Secondary diagonal elements sum: " + primarySum + ", mult: " + primaryMult);
    }

    public static void partB() {
        final int rows = 8, columns = 5;
        final int min = -99, max = 99;
        int[][] matrix = generateTwoDimRandIntArrayAndShow(rows, columns, min, max);

        int maxRow = 0, maxColumn = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] > matrix[maxRow][maxColumn]) {
                    maxRow = i;
                    maxColumn = j;
                }
            }
        }

        System.out.println("Max element " + matrix[maxRow][maxColumn] + " located in row " + maxRow + ", column " + maxColumn);
    }

    public static void partC() {
        final int rows = 8, columns = 5;
        final int min = -10, max = 10;
        int[][] matrix = generateTwoDimRandIntArrayAndShow(rows, columns, min, max);

        int maxRowIndex = 0;
        int maxRowMult = Integer.MIN_VALUE, currentRowMult;
        for (int i = 0; i < rows; i++) {
            currentRowMult = 1;
            for (int j = 0; j < columns; j++) {
                currentRowMult *= matrix[i][j];
            }
            currentRowMult = Math.abs(currentRowMult);

            if (currentRowMult > maxRowMult) {
                maxRowMult = currentRowMult;
                maxRowIndex = i;
            }
        }

        System.out.println("Row with max absolute mult of elements has index " + maxRowIndex + ", mult value: " + maxRowMult);
    }

    public static void partD() {
        final int rows = 10, columns = 7;
        final int min = 0, max = 100;
        int[][] matrix = generateTwoDimRandIntArrayAndShow(rows, columns, min, max);

        for (int i = 0; i < rows; i++) {
            Task1.selectionSort(matrix[i], (a, b) -> b - a);
        }

        System.out.println("\nRows sorted in descending order: ");
        showTwoDimIntArray(matrix);
    }

    public static int[][] generateTwoDimRandIntArrayAndShow(int rows, int columns, int min, int max) {
        Random rnd = new Random();
        int[][] matrix = new int[rows][];
        for (int i = 0; i < rows; i++) {
            matrix[i] = rnd.ints(columns, min, max + 1).toArray();
        }

        System.out.println("Two dimensional array of " + rows + "x" + columns + " random numbers in [" + min + ";" + max + "]: ");
        showTwoDimIntArray(matrix);

        return matrix;
    }

    public static void showTwoDimIntArray(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    public static void main(String[] args) {
        System.out.println("A)");
        partA();

        System.out.println("\nB)");
        partB();

        System.out.println("\nC)");
        partC();

        System.out.println("\nD)");
        partD();
    }
}
