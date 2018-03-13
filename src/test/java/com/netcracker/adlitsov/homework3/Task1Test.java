package com.netcracker.adlitsov.homework3;

import org.junit.jupiter.api.RepeatedTest;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Task1Test {

    static final int MAX_ARRAY_SIZE = 10_000;

    @RepeatedTest(10)
    void bubbleSortShouldWorkProperly() {
        Random rnd = new Random();
        final int[] inArray = rnd.ints(rnd.nextInt(MAX_ARRAY_SIZE + 1)).toArray();

        final int[] bubbleSorted = Arrays.copyOf(inArray, inArray.length);
        Task1.bubbleSort(bubbleSorted);

        final int[] defaultSorted = Arrays.copyOf(inArray, inArray.length);
        Arrays.sort(defaultSorted);

        assertArrayEquals(bubbleSorted, defaultSorted);
    }

    @RepeatedTest(10)
    void selectionSortShouldWorkProperly() {
        Random rnd = new Random();
        final int[] inArray = rnd.ints(rnd.nextInt(MAX_ARRAY_SIZE + 1)).toArray();

        final int[] selectionSorted = Arrays.copyOf(inArray, inArray.length);
        Task1.selectionSort(selectionSorted);

        final int[] defaultSorted = Arrays.copyOf(inArray, inArray.length);
        Arrays.sort(defaultSorted);

        assertArrayEquals(selectionSorted, defaultSorted);
    }

}
