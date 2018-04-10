package com.netcracker.adlitsov.homework4.part1;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static com.netcracker.adlitsov.homework4.part2.ListsPerformanceTester.randAlphabeticStr;

class PerformanceTester {
    private static final int MAX_STR_LENGTH = 100;
    private static final String FORMAT_LINE = "%-30s %15d ns\n";
    private static final int WARM_RUNS = 50;
    private static final int WARM_DATA_SIZE = 10_000;

    private ILinkedList<String> myList = new MyLinkedList<>();
    private List<String> utilList = new LinkedList<>();
    private Random rnd;
    private String[] source;

    private boolean warmUp = false;

    private void showRunNanoTime(Class listClass, Runnable r) {
        long startTime = System.nanoTime();
        r.run();
        long duration = System.nanoTime() - startTime;
        if (!warmUp) {
            System.out.printf(FORMAT_LINE, listClass.getSimpleName() + " time:", duration);
        }
    }


    private void testAppendTime(int size) {
        if (!warmUp) {
            System.out.println("--- Append performance comparison ---");
        }
        showRunNanoTime(myList.getClass(), () -> {
            for (int i = 0; i < size; i++) {
                myList.add(source[i]);
            }
        });
        showRunNanoTime(utilList.getClass(), () -> {
            for (int i = 0; i < size; i++) {
                utilList.add(source[i]);
            }
        });
    }

    private void testAddTime(int size) {
        if (!warmUp) {
            System.out.println("--- Add at random index performance comparison ---");
        }
        int[] indexes = generateRandomIndexesForAdd(size);
        showRunNanoTime(myList.getClass(), () -> {
            for (int i = 0; i < size; i++) {
                myList.add(indexes[i], source[i]);
            }
        });
        showRunNanoTime(utilList.getClass(), () -> {
            for (int i = 0; i < size; i++) {
                utilList.add(indexes[i], source[i]);
            }
        });
    }

    private void testGetTime(int size) {
        if (!warmUp) {
            System.out.println("--- Get at random index (" + size + " times) performance comparison ---");
        }
        int[] indexes = generateRandomIndexes(size);
        showRunNanoTime(myList.getClass(), () -> {
            for (int i = 0; i < size; i++) {
                myList.get(indexes[i]);
            }
        });
        showRunNanoTime(utilList.getClass(), () -> {
            for (int i = 0; i < size; i++) {
                utilList.get(indexes[i]);
            }
        });
    }

    private void testSearchTime(int size) {
        int searchSize = size / 10;
        if (!warmUp) {
            System.out.println("--- Search " + searchSize + "(10%) random items performance comparison ---");
        }
        String[] searchElements = getRandomElements(searchSize);
        showRunNanoTime(myList.getClass(), () -> {
            for (int i = 0; i < searchSize; i++) {
                myList.indexOf(searchElements[i]);
            }
        });
        showRunNanoTime(utilList.getClass(), () -> {
            for (int i = 0; i < searchSize; i++) {
                utilList.indexOf(searchElements[i]);
            }
        });
    }

    private void testRemoveTime(int size) {
        if (!warmUp) {
            System.out.println("--- Remove (almost all) elements at random index performance comparison ---");
        }
        int[] indexes = generateRandomIndexesForRemove(size);
        showRunNanoTime(myList.getClass(), () -> {
            for (int i = 0; i < size; i++) {
                myList.remove(indexes[i]);
            }
        });
        showRunNanoTime(utilList.getClass(), () -> {
            for (int i = 0; i < size; i++) {
                utilList.remove(indexes[i]);
            }
        });
    }

    private int[] generateRandomIndexesForAdd(int size) {
        int[] indexes = new int[size];
        for (int i = 0; i < size; i++) {
            indexes[i] = rnd.nextInt(i + 1);
        }
        return indexes;
    }

    private int[] generateRandomIndexes(int size) {
        int[] indexes = new int[size];
        for (int i = 0; i < size; i++) {
            indexes[i] = rnd.nextInt(source.length);
        }
        return indexes;
    }


    private int[] generateRandomIndexesForRemove(int size) {
        int[] indexes = new int[size];
        for (int i = 0; i < size; i++) {
            indexes[i] = rnd.nextInt(source.length - i);
        }
        return indexes;
    }

    private String[] getRandomElements(int size) {
        String[] elements = new String[size];
        for (int i = 0; i < size; i++) {
            elements[i] = source[rnd.nextInt(source.length)];
        }
        return elements;
    }

    private void generateSource(int size) {
        source = new String[size];
        for (int i = 0; i < size; i++) {
            source[i] = randAlphabeticStr(MAX_STR_LENGTH, rnd);
        }
    }

    private void clearLists() {
        myList.clear();
        utilList.clear();
    }

    private void createLists() {
        myList = new MyLinkedList<>();
        utilList = new LinkedList<>();
    }

    private void recreateLists() {
        clearLists();
        createLists();
    }

    public void test(int size) {
        rnd = new Random();
        createLists();
        generateSource(size);

        testAddTime(size);
        recreateLists();
        testAppendTime(size);

        testGetTime(size);
        testSearchTime(size);
        testRemoveTime(size);

        clearLists();
        System.gc();
    }

    public void warmUp(int runsCount, int dataSize) {
        System.out.println("*** Warming up with " + runsCount + " runs where data size is " + dataSize + " ***");
        warmUp = true;
        for (int i = 0; i < runsCount; i++) {
            System.out.println("* Warming up, run #" + (i + 1) + "*");
            test(dataSize);
        }
        warmUp = false;
    }

    public static void main(String[] args) {
        System.out.print("Enter elements count:");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();

        PerformanceTester pf = new PerformanceTester();
        pf.warmUp(WARM_RUNS, WARM_DATA_SIZE);
        System.out.println("\n\n------ Benchmark begins ------");
        pf.test(size);
    }
}
