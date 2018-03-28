package com.netcracker.adlitsov.homework4;

import org.junit.jupiter.api.RepeatedTest;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
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
        final int itemsCount = rnd.nextInt(MAX_ITEMS + 1);

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
        final int itemsCount = rnd.nextInt(MAX_ITEMS + 1);
        int[] items = rnd.ints(itemsCount).toArray();

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
        final int itemsCount = rnd.nextInt(MAX_ITEMS + 1);
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
        final int itemsCount = rnd.nextInt(MAX_ITEMS + 1);
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
        final int itemsCount = rnd.nextInt(MAX_ITEMS + 1);
        int[] items = rnd.ints(itemsCount).toArray();

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

    @RepeatedTest(3)
    void indexOfShouldWorkProperlyWithStrings() {
        Random rnd = new Random();
        final int itemsCount = rnd.nextInt(MAX_ITEMS + 1);
        int[] items = rnd.ints(itemsCount).toArray();

        ILinkedList<Integer> list = new MyLinkedList<>();
        for (int item : items) {
            list.add(item);
        }

        List<Integer> indexes = IntStream.range(0, itemsCount).boxed().collect(Collectors.toList());
        Collections.shuffle(indexes);

        for (int index : indexes) {
            assertEquals(index, list.indexOf(items[index]));
        }
    }

    @RepeatedTest(3)
    void indexOfShouldWorkProperlyWithNull() {
        Random rnd = new Random();
        final int itemsCount = rnd.nextInt(MAX_ITEMS + 1);
        final int nullPosition = rnd.nextInt(itemsCount);

        ILinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < itemsCount; i++) {
            if (i != nullPosition) {
                list.add(i);
            } else {
                list.add(null);
            }
        }

        assertEquals(nullPosition, list.indexOf(null));
    }

    @RepeatedTest(3)
    void indexOfShouldWorkProperlyWithUnknownElement() {
        Random rnd = new Random();
        final int itemsCount = rnd.nextInt(MAX_ITEMS + 1);
        final int maxValue = rnd.nextInt(Integer.MAX_VALUE);

        ILinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < itemsCount; i++) {
            list.add(i);
        }

        assertEquals(-1, list.indexOf(maxValue + 1));
        assertEquals(-1, list.indexOf(-maxValue));
    }

    @RepeatedTest(3)
    void removeShouldWorkProperly() {
        Random rnd = new Random();
        final int itemsCount = rnd.nextInt(MAX_ITEMS + 1);
        final List<Integer> items = rnd.ints(itemsCount).boxed().collect(Collectors.toList());
        final int removeItemsCount = rnd.nextInt(itemsCount + 1);

        ILinkedList<Integer> myList = new MyLinkedList<>();
        for (int item : items) {
            myList.add(item);
        }

        int removePos;
        for (int i = 0; i < removeItemsCount; i++) {
            removePos = rnd.nextInt(itemsCount - i);
            assertEquals(items.remove(removePos), myList.remove(removePos));
        }

        assertEquals(myList.size(), items.size(), "sizes not equal!");
        for (int i = 0; i < items.size(); i++) {
            assertEquals(items.get(i), myList.get(i));
        }
    }

    @RepeatedTest(3)
    void toObjectArrayShouldWorkProperly() {
        Random rnd = new Random();
        final int itemsCount = rnd.nextInt(MAX_ITEMS + 1);
        final Object[] items = rnd.ints(itemsCount).boxed().toArray();

        ILinkedList<Object> list = new MyLinkedList<>();
        for (Object item : items) {
            list.add(item);
        }

        assertArrayEquals(items, list.toArray());
    }

    @RepeatedTest(3)
    void toTypedArrayShouldWorkProperly() {
        Random rnd = new Random();
        final int itemsCount = rnd.nextInt(MAX_ITEMS + 1);
        final int itemsCopyCount = itemsCount - 2;
        Integer[] items = new Integer[itemsCount];
        items = rnd.ints(itemsCount).boxed().collect(Collectors.toList()).toArray(items);

        ILinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < itemsCopyCount; i++) {
            list.add(items[i]);
        }
        Integer[] arrayFromList = new Integer[itemsCount];
        System.arraycopy(items, itemsCopyCount, arrayFromList, itemsCopyCount, itemsCount - itemsCopyCount);
        arrayFromList = list.toArray(arrayFromList);

        assertArrayEquals(Arrays.copyOf(items, itemsCopyCount), Arrays.copyOf(list.toArray(items), itemsCopyCount));
        assertEquals(null, arrayFromList[itemsCopyCount]);
        for (int i = itemsCopyCount + 1; i < itemsCount; i++) {
            assertEquals(items[i], arrayFromList[i]);
        }
    }

    @RepeatedTest(3)
    void toTypedArrayWithLambdasShouldWorkProperly() {
        Random rnd = new Random();
        final int itemsCount = rnd.nextInt(MAX_ITEMS + 1);
        Integer[] items = rnd.ints(itemsCount).boxed().toArray(Integer[]::new);

        ILinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 0; i < items.length; i++) {
            list.add(items[i]);
        }

        assertArrayEquals(items, list.toArray(Integer[]::new));
    }

}
