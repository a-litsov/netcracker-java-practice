package com.netcracker.adlitsov.homework3;

import java.util.Arrays;
import java.util.Random;

public class Task4 {

    public static double DELTA = 1e-5;

    public static void partA() {
        final int oddNumbersCount = 50;
        int[] oddNumbers = new int[oddNumbersCount];
        for (int i = 0, val = 1; i < oddNumbers.length && val < 100; i++, val += 2) {
            oddNumbers[i] = val;
        }

        System.out.print("Array of odd numbers in ascending order: ");
        for (int i = 0; i < oddNumbers.length; i++) {
            System.out.print(oddNumbers[i] + " ");
        }

        System.out.print("\nArray of odd numbers in descending order: ");
        for (int i = oddNumbers.length - 1; i >= 0; i--) {
            System.out.print(oddNumbers[i] + " ");
        }
        System.out.println();
    }

    public static void partB() {
        final int count = 20, min = 0, max = 10;
        int[] numbers = generateRandIntArrayAndShow(count, min, max);

        int evenCount = 0, oddCount = 0;
        for (int num : numbers) {
            if (num % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        System.out.println("Even numbers count: " + evenCount);
        System.out.println("Odd numbers count: " + oddCount);
    }

    public static void partC() {
        final int count = 10, min = 1, max = 100;
        int[] numbers = generateRandIntArrayAndShow(count, min, max);

        for (int i = 0; i < numbers.length; i++) {
            if (i % 2 == 1) {
                numbers[i] = 0;
            }
        }

        System.out.println("Odd indexes replaced by zero: " + Arrays.toString(numbers));
    }

    public static void partD() {
        final int count = 15, min = -50, max = 50;
        int[] numbers = generateRandIntArrayAndShow(count, min, max);

        int maxIndex = 0, minIndex = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] >= numbers[maxIndex]) {
                maxIndex = i;
            }
            if (numbers[i] <= numbers[minIndex]) {
                minIndex = i;
            }
        }

        System.out.println("Max element value: " + numbers[maxIndex] + ", it's last index: " + maxIndex);
        System.out.println("Min element value: " + numbers[minIndex] + ", it's last index: " + minIndex);
    }

    public static void partE() {
        final int count = 10, min = 0, max = 10;
        int[] numbers1 = generateRandIntArrayAndShow(count, min, max);
        int[] numbers2 = generateRandIntArrayAndShow(count, min, max);

        long sum1 = 0, sum2 = 0;
        for (int i = 0; i < count; i++) {
            sum1 += numbers1[i];
            sum2 += numbers2[i];
        }

        double arithmMean1 = sum1 / (double) count;
        double arithmMean2 = sum2 / (double) count;

        System.out.println("First array arithmetic mean: " + arithmMean1);
        System.out.println("Second array arithmetic mean: " + arithmMean2);

        if (Math.abs(arithmMean1 - arithmMean2) <= DELTA) {
            System.out.println("Arrays have the same arithmetic means");
        } else {
            System.out.println("Arithmetic mean is bigger for " +
                                       ((arithmMean1 < arithmMean2) ? "second" : "first") +
                                       " array");
        }
    }

    public static void partF() {
        final int count = 20, min = -1, max = 1;
        int[] numbers = generateRandIntArrayAndShow(count, min, max);

        // [-1;1] -> [0;2]
        int[] histogram = new int[3];

        for (int num : numbers) {
            histogram[num + 1]++;
        }

        int maxIndex = 0;
        for (int i = 0; i < histogram.length; i++) {
            if (histogram[i] > histogram[maxIndex]) {
                maxIndex = i;
            }
        }

        System.out.print("The most frequent element(s) has(-ve) " + histogram[maxIndex] + " occurrences: ");
        for (int i = 0; i < histogram.length; i++) {
            if (histogram[i] == histogram[maxIndex]) {
                System.out.print(i - 1 + " ");
            }
        }
        System.out.println();

        // Shows other elements with the same number of occurences
        for (int i = 0; i < histogram.length; i++) {
            if (histogram[i] != histogram[maxIndex]) {
                for (int j = i + 1; j < histogram.length; j++) {
                    if (histogram[i] == histogram[j]) {
                        System.out.println("Elements " + (i - 1) + " and " + (j - 1) + " occurred " + histogram[i] + " times");
                    }
                }
            }
        }
    }

    public static int[] generateRandIntArrayAndShow(int size, int min, int max) {
        int[] array = new Random().ints(size, min, max + 1).toArray();

        System.out.println("Array of " + size + " random numbers in [" + min + ";" + max + "]: "
                                   + Arrays.toString(array));

        return array;
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

        System.out.println("\nE)");
        partE();

        System.out.println("\nF)");
        partF();
    }
}
