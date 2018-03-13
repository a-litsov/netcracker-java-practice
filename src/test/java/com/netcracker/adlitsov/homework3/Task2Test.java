package com.netcracker.adlitsov.homework3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task2Test {

    @Test
    void getFactorialRecursiveShouldWorkProperlyForFirstTenNumbers() {
        int[] firstFactorialValues = {1, 1, 2, 6, 24, 120, 720, 5_040, 40_320, 362_880};
        for (int i = 0; i < firstFactorialValues.length; i++) {
            assertEquals(Task2.getFactorialRecursive(i), firstFactorialValues[i]);
        }
    }

    @Test
    void getFactorialIterativeShouldWorkProperlyForFirstTenNumbers() {
        int[] firstFactorialValues = {1, 1, 2, 6, 24, 120, 720, 5_040, 40_320, 362_880};
        for (int i = 0; i < firstFactorialValues.length; i++) {
            assertEquals(Task2.getFactorialIterative(i), firstFactorialValues[i]);
        }

    }

    @Test
    void recursiveMethodShouldThrowIAExceptionForNegativeNumbers() {
        assertThrows(IllegalArgumentException.class, () -> Task2.getFactorialRecursive(-1));
    }

    @Test
    void iterativeMethodShouldThrowIAExceptionForNegativeNumbers() {
        assertThrows(IllegalArgumentException.class, () -> Task2.getFactorialIterative(-1));
    }
}
