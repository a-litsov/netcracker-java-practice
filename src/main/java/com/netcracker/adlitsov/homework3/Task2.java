package com.netcracker.adlitsov.homework3;

import java.util.Scanner;

public class Task2 {

    public static long getFactorialRecursive(int n) {
        checkForNonNegative(n);

        if (n <= 0) {
            return 1;
        }

        return n * getFactorialRecursive(n - 1);
    }

    public static long getFactorialIterative(int n) {
        checkForNonNegative(n);

        long factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }

        return factorial;
    }

    private static void checkForNonNegative(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is defined only for non-negative numbers!");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number: ");
        final int number = sc.nextInt();

        long startTime = System.nanoTime();
        long result = getFactorialRecursive(number);
        long estimatedTime = System.nanoTime() - startTime;

        String formatLine = "%-10s version result: %,20d, time: %,15d ns\n";
        System.out.printf(formatLine, "Recursive", result, estimatedTime);


        startTime = System.nanoTime();
        result = getFactorialIterative(number);
        estimatedTime = System.nanoTime() - startTime;

        System.out.printf(formatLine, "Iterative", result, estimatedTime);

        sc.close();
    }
}
