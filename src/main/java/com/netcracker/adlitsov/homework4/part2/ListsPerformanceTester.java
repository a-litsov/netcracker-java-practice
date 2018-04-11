package com.netcracker.adlitsov.homework4.part2;

import java.util.*;

public class ListsPerformanceTester {
    private static final int MAX_STR_LENGTH = 100;
    private static final String FORMAT_LINE = "%-30s %,15d ns\n";
    private static final int WARM_RUNS = 100;
    private static final int WARM_DATA_SIZE = 10_000;

    private List<String> linkedList;
    private List<String> arrayList;
    private String[] source;

    private Random rnd;
    private boolean warmUp = false;

    private void showRunNanoTime(Class listClass, Runnable r) {
        long startTime = System.nanoTime();
        r.run();
        long duration = System.nanoTime() - startTime;
        if (!warmUp) {
            System.out.printf(FORMAT_LINE, listClass.getSimpleName() + " time:", duration);
        }
    }

    private void fillListUsingAppend(List<String> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(source[i]);
        }
    }

    private void testAppendTime(int size) {
        if (!warmUp) {
            System.out.println("--- Append performance comparison ---");
        }
        showRunNanoTime(arrayList.getClass(), () -> fillListUsingAppend(arrayList, size));
        showRunNanoTime(linkedList.getClass(), () -> fillListUsingAppend(linkedList, size));
    }

    private void fillListUsingAddFirst(List<String> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(0, source[i]);
        }
    }

    private void testAddFirstTime(int size) {
        if (!warmUp) {
            System.out.println("--- Add at the start performance comparison ---");
        }
        showRunNanoTime(arrayList.getClass(), () -> fillListUsingAddFirst(arrayList, size));
        showRunNanoTime(linkedList.getClass(), () -> fillListUsingAddFirst(linkedList, size));
    }


    private void fillListUsingAdd(List<String> list, int[] indexes) {
        for (int i = 0; i < indexes.length; i++) {
            list.add(indexes[i], source[i]);
        }
    }

    private void testAddTime(int size) {
        if (!warmUp) {
            System.out.println("--- Add at random index performance comparison ---");
        }
        int[] indexes = generateRandomIndexesForAdd(size);
        showRunNanoTime(arrayList.getClass(), () -> fillListUsingAdd(arrayList, indexes));
        showRunNanoTime(linkedList.getClass(), () -> fillListUsingAdd(linkedList, indexes));
    }


    private void removeListElements(String[] elements, List<String> list) {
        for (int i = 0; i < elements.length; i++) {
            list.remove(elements[i]);
        }
    }

    private void testRemoveTime(int size) {
        int removeSize = size / 10;
        String[] itemsToRemove = getRandomElementsToRemove(removeSize);
        if (!warmUp) {
            System.out.println("--- Remove " + removeSize + "(10%) random items performance comparison ---");
        }

        showRunNanoTime(arrayList.getClass(), () -> removeListElements(itemsToRemove, arrayList));
        showRunNanoTime(linkedList.getClass(), () -> removeListElements(itemsToRemove, linkedList));
    }

    private void addUsingIterator(List<String> list, int index) {
        ListIterator<String> it = list.listIterator(index);
        String str = randAlphabeticStr(rnd.nextInt(MAX_STR_LENGTH), rnd);
        showRunNanoTime(list.getClass(), () -> it.add(str));
    }

    private void removeUsingIterator(List<String> list, int index) {
        ListIterator<String> it = list.listIterator();
        for (int i = 0; i < index + 1; i++) {
            it.next();
        }
        String str = randAlphabeticStr(rnd.nextInt(MAX_STR_LENGTH), rnd);
        showRunNanoTime(list.getClass(), it::remove);
    }

    private void testAddMiddleWithIter() {
        int addPos = source.length / 2;
        if (!warmUp) {
            System.out.println("--- Add element at the middle using iterator performance comparison ---");
        }
        addUsingIterator(arrayList, addPos);
        addUsingIterator(linkedList, addPos);
    }

    private void testAddFirstWithIter() {
        int addPos = source.length / 2;
        if (!warmUp) {
            System.out.println("--- Add element at the beginning using iterator performance comparison ---");
        }
        addUsingIterator(arrayList, 0);
        addUsingIterator(linkedList, 0);
    }

    private void testRemoveMiddleWithIter() {
        int removePos = source.length / 2;
        if (!warmUp) {
            System.out.println("--- Remove element at the middle using iterator performance comparison ---");
        }
        removeUsingIterator(arrayList, removePos);
        removeUsingIterator(linkedList, removePos);

    }

    private void testRemoveBeginningWithIter() {
        int removePos = 0;
        if (!warmUp) {
            System.out.println("--- Remove element at the beginning using iterator performance comparison ---");
        }
        removeUsingIterator(arrayList, removePos);
        removeUsingIterator(linkedList, removePos);
    }

    private int[] generateRandomIndexesForAdd(int size) {
        int[] indexes = new int[size];
        for (int i = 0; i < size; i++) {
            indexes[i] = rnd.nextInt(i + 1);
        }
        return indexes;
    }

    private String[] getRandomElementsToRemove(int size) {
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

    public static String randAlphabeticStr(int length, Random rnd) {
        final String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
        final int alphabetSize = alphabet.length();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(rnd.nextInt(alphabetSize)));
        }

        return sb.toString();
    }

    private void clearLists() {
        linkedList.clear();
        arrayList.clear();
    }

    private void createLists() {
        linkedList = new LinkedList<>();
        arrayList = new ArrayList<>();
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
        testAddFirstTime(size);
        recreateLists();
        testAppendTime(size);

        testAddMiddleWithIter();
        testAddFirstWithIter();
        testRemoveMiddleWithIter();
        testRemoveBeginningWithIter();

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

        ListsPerformanceTester lpt = new ListsPerformanceTester();
        lpt.warmUp(WARM_RUNS, WARM_DATA_SIZE);
        System.out.println("\n\n------ Benchmark begins ------");
        lpt.test(size);
    }
}
