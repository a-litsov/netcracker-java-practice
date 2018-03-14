package com.netcracker.adlitsov.homework3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class Task1 {

    public static void swapElems(int[] inArray, int firstPos, int secondPos) {
        int temp = inArray[firstPos];
        inArray[firstPos] = inArray[secondPos];
        inArray[secondPos] = temp;
    }

    public static void bubbleSort(int[] inArray) {
        boolean swapped;
        for (int i = 0; i < inArray.length - 1; i++) {
            swapped = false;
            for (int j = inArray.length - 1; j > i; j--) {
                if (inArray[j] < inArray[j - 1]) {
                    swapElems(inArray, j, j - 1);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void selectionSort(int[] inArray) {
        int curMinIndex;
        for (int i = 0; i < inArray.length - 1; i++) {
            curMinIndex = i;
            for (int j = i + 1; j < inArray.length; j++) {
                if (inArray[j] < inArray[curMinIndex]) {
                    curMinIndex = j;
                }
            }
            swapElems(inArray, i, curMinIndex);
        }
    }

    public static void selectionSort(int[] inArray, Comparator<Integer> comp) {
        int curMinIndex;
        for (int i = 0; i < inArray.length - 1; i++) {
            curMinIndex = i;
            for (int j = i + 1; j < inArray.length; j++) {
                if (comp.compare(inArray[j], inArray[curMinIndex]) < 0) {
                    curMinIndex = j;
                }
            }
            swapElems(inArray, i, curMinIndex);
        }
    }

    private static long getRunNanoTime(Runnable r) {
        long startTime = System.nanoTime();
        r.run();
        return System.nanoTime() - startTime;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        final int size = sc.nextInt();
        final int[] inArray = new Random().ints(size).toArray();

        String formatLine = "%-20s %,15d ns\n";
        System.out.printf(formatLine,
                          "Bubble sort time:",
                          getRunNanoTime(() -> bubbleSort(Arrays.copyOf(inArray, inArray.length))));
        System.out.printf(formatLine,
                          "Selection sort time:",
                          getRunNanoTime(() -> selectionSort(Arrays.copyOf(inArray, inArray.length))));
        System.out.printf(formatLine,
                          "Arrays.sort time:",
                          getRunNanoTime(() -> Arrays.sort(Arrays.copyOf(inArray, inArray.length))));

        sc.close();
    }

}
