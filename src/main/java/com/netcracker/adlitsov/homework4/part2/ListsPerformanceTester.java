package com.netcracker.adlitsov.homework4.part2;

import java.util.*;

public class ListsPerformanceTester {
    public static final int MAX_STR_LENGTH = 100;
    private static List<String> linkedList = new LinkedList<>();
    private static List<String> arrayList = new ArrayList<>();
    private static Random rnd = new Random();
    private static String formatLine = "%-30s %,15d ns\n";
    private static String[] source;

    private static void showRunNanoTime(Class listClass, Runnable r) {
        long startTime = System.nanoTime();
        r.run();
        long duration = System.nanoTime() - startTime;
        System.out.printf(formatLine, listClass.getSimpleName() + " time:", duration);
    }

    private static void fillListUsingAppend(List<String> list, int size) {
        for (int i = 0; i < size; i++) {
            list.add(source[i]);
        }
    }

    private static void testAppendTime(int size) {
        System.out.println("--- Append performance comparison ---");
        showRunNanoTime(arrayList.getClass(), () -> fillListUsingAppend(arrayList, size));
        showRunNanoTime(linkedList.getClass(), () -> fillListUsingAppend(linkedList, size));

    }

    private static void fillListUsingAdd(List<String> list, int[] indexes) {
        for (int i = 0; i < indexes.length; i++) {
            list.add(indexes[i], source[i]);
        }
    }

    private static void testAddTime(int size) {
        System.out.println("--- Add at random index performance comparison ---");
        int[] indexes = generateRandomIndexesForAdd(size);

        showRunNanoTime(linkedList.getClass(), () -> fillListUsingAdd(linkedList, indexes));
        showRunNanoTime(arrayList.getClass(), () -> fillListUsingAdd(arrayList, indexes));
    }


    private static void removeListElements(String[] elements, List<String> list) {
        for (int i = 0; i < elements.length; i++) {
            list.remove(elements[i]);
        }
    }

    private static void testRemoveTime(int size) {
        int removeSize = size / 10;
        System.out.println("--- Remove " + removeSize + "(10%) random items performance comparison ---");
        String[] itemsToRemove = getRandomElementsToRemove(removeSize);

        showRunNanoTime(arrayList.getClass(), () -> removeListElements(itemsToRemove, arrayList));
        showRunNanoTime(linkedList.getClass(), () -> removeListElements(itemsToRemove, linkedList));
    }

    private static void clearLists() {
        linkedList.clear();
        arrayList.clear();
    }

    private static void addUsingIterator(List<String> list, int index) {
        ListIterator<String> it = list.listIterator();
        for (int i = 0; i < index; i++) {
            it.next();
        }
        String str = randAlphabeticStr(rnd.nextInt(MAX_STR_LENGTH));
        showRunNanoTime(list.getClass(), () -> it.add(str));
    }

    private static void removeUsingIterator(List<String> list, int index) {
        ListIterator<String> it = list.listIterator();
        for (int i = 0; i < index + 1; i++) {
            it.next();
        }
        String str = randAlphabeticStr(rnd.nextInt(MAX_STR_LENGTH));
        showRunNanoTime(list.getClass(), it::remove);
    }

    private static void testAddMiddleWithIter() {
        int addPos = source.length / 2;
        System.out.println("--- Add element at the middle using iterator performance comparison ---");

        addUsingIterator(arrayList, addPos);
        addUsingIterator(linkedList, addPos);
    }

    private static void testAddFirstWithIter() {
        int addPos = source.length / 2;
        System.out.println("--- Add element at the beginning using iterator performance comparison ---");

        addUsingIterator(arrayList, 0);
        addUsingIterator(linkedList, 0);
    }

    private static void testRemoveMiddleWithIter() {
        int removePos = source.length / 2;
        System.out.println("--- Remove element at the middle using iterator performance comparison ---");

        removeUsingIterator(arrayList, removePos);
        removeUsingIterator(linkedList, removePos);

    }

    private static void testRemoveBeginningWithIter() {
        int removePos = 0;
        System.out.println("--- Remove element at the beginning using iterator performance comparison ---");

        removeUsingIterator(arrayList, removePos);
        removeUsingIterator(linkedList, removePos);
    }

    public static String randAlphabeticStr(int length) {
        final String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
        final int alphabetSize = alphabet.length();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(rnd.nextInt(alphabetSize)));
        }

        return sb.toString();
    }

    private static void generateSource(int size) {
        source = new String[size];
        for (int i = 0; i < size; i++) {
            source[i] = randAlphabeticStr(MAX_STR_LENGTH);
        }
    }

    private static int[] generateRandomIndexesForAdd(int size) {
        int[] indexes = new int[size];
        for (int i = 0; i < size; i++) {
            indexes[i] = rnd.nextInt(i + 1);
        }
        return indexes;
    }

    private static String[] getRandomElementsToRemove(int size) {
        String[] elements = new String[size];
        for (int i = 0; i < size; i++) {
            elements[i] = source[rnd.nextInt(source.length)];
        }
        return elements;
    }

    public static void main(String[] args) {
        System.out.print("Enter elements count:");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        generateSource(size);

        testAddTime(size);
        clearLists();
        testAppendTime(size);
        testAddMiddleWithIter();
        testAddFirstWithIter();
        testRemoveMiddleWithIter();
        testRemoveBeginningWithIter();
        testRemoveTime(size);
    }
}
