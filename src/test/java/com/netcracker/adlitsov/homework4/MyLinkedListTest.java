package com.netcracker.adlitsov.homework4;

import org.junit.jupiter.api.RepeatedTest;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyLinkedListTest {

    public static final int MAX_ITEMS = 10_000;

    public static String randAlphabeticStr(int length, Random rnd) {
        final String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789";
        final int alphabetSize = alphabet.length();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(rnd.nextInt(alphabetSize)));
        }

        return sb.toString();
    }

    @RepeatedTest(3)
    void sizeCalculatedProperly() {
        Random rnd = new Random();
        final int itemsCount = rnd.nextInt(Integer.MAX_VALUE) % MAX_ITEMS;

        ILinkedList<Object> list = new MyLinkedList<>();
        for (int i = 0; i < itemsCount; i++) {
            list.add(new Object());
        }

        for (int i = 0; i < itemsCount; i++) {
            list.add(rnd.nextInt(list.size() + 1), new Object());
        }

        assertEquals(2 * itemsCount, list.size());
    }

    @RepeatedTest(3)
    void addShouldWorkProperly() {
        Random rnd = new Random();
        final int itemsCount = rnd.nextInt(Integer.MAX_VALUE) % MAX_ITEMS;
        int[] items = new Random().ints(itemsCount).toArray();

        ILinkedList<Integer> addList = new MyLinkedList<>();
        for (int item : items) {
            addList.add(item);
        }

        assertEquals(addList.size(), items.length, "sizes not equal!");
        for (int i = items.length - 1; i >= 0; i--) {
            assertEquals(items[i], addList.get(i).intValue());
        }
    }

    @RepeatedTest(3)
    void getShouldWorkProperly() {
        Random rnd = new Random();
        final int itemsCount = rnd.nextInt(Integer.MAX_VALUE) % MAX_ITEMS;
        int[] items = new Random().ints(itemsCount).toArray();

        ILinkedList<Integer> addList = new MyLinkedList<>();
        for (int item : items) {
            addList.add(item);
        }

        assertEquals(addList.size(), items.length, "sizes not equal!");
        int index;
        for (int i = items.length - 1; i >= 0; i--) {
            index = rnd.nextInt(items.length);
            assertEquals(items[index], addList.get(index).intValue());
        }
    }

    @RepeatedTest(3)
    void addAtIndexShouldWorkProperlyWithStrings() {
        Random rnd = new Random();
        final int itemsCount = rnd.nextInt(Integer.MAX_VALUE) % MAX_ITEMS;
        final int maxLength = 50;
        String[] items = new String[itemsCount];
        for (int i = 0; i < items.length; i++) {
            items[i] = randAlphabeticStr(rnd.nextInt(maxLength + 1), rnd);
        }

        ILinkedList<String> addIndList = new MyLinkedList<>();
        List<String> defaultList = new LinkedList<>();
        int insertPos;
        for (String item : items) {
            insertPos = rnd.nextInt(defaultList.size() + 1);
            addIndList.add(insertPos, item);
            defaultList.add(insertPos, item);
        }

        assertEquals(addIndList.size(), defaultList.size(), "sizes not equal!");
        for (int i = 0; i < defaultList.size(); i++) {
            assertEquals(defaultList.get(i), addIndList.get(i));
        }
    }

    @RepeatedTest(3)
    void setShouldWorkProperly() {
        Random rnd = new Random();
        final int itemsCount = rnd.nextInt(Integer.MAX_VALUE) % MAX_ITEMS;
        final int maxLength = 50;
        int[] items = new Random().ints(itemsCount).toArray();

        ILinkedList<Integer> myList = new MyLinkedList<>();
        List<Integer> defaultList = new LinkedList<>();
        for (int item : items) {
            myList.add(item);
            defaultList.add(item);
        }

        int insertPos;
        for (int item : items) {
            insertPos = rnd.nextInt(defaultList.size());
            myList.set(insertPos, item);
            defaultList.set(insertPos, item);
        }

        assertEquals(myList.size(), defaultList.size(), "sizes not equal!");
        for (int i = 0; i < defaultList.size(); i++) {
            assertEquals(defaultList.get(i), myList.get(i));
        }
    }
}
