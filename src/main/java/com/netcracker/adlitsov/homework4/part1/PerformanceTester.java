package com.netcracker.adlitsov.homework4.part1;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class PerformanceTester {
    private static ILinkedList<Integer> myList = new MyLinkedList<>();
    private static List<Integer> utilList = new LinkedList<>();
    private static Random rnd = new Random();
    private static String formatLine = "%-30s %,15d ns\n";


    private static void showRunNanoTime(String listType, Runnable r) {
        long startTime = System.nanoTime();
        r.run();
        long duration = System.nanoTime() - startTime;
        System.out.printf(formatLine, listType + " time:", duration);
    }


    private static void testAppendTime(int size) {
        System.out.println("--- Append performance comparison ---");
        showRunNanoTime("MyLinkedList", () -> {
            for (int i = 0; i < size; i++) {
                myList.add(rnd.nextInt());
            }
        });
        showRunNanoTime("util.LinkedList", () -> {
            for (int i = 0; i < size; i++) {
                utilList.add(rnd.nextInt());
            }
        });
    }

    private static void testAddTime(int size) {
        System.out.println("--- Add at random index performance comparison ---");
        showRunNanoTime("MyLinkedList", () -> {
            for (int i = 0; i < size; i++) {
                myList.add(rnd.nextInt(myList.size() + 1), rnd.nextInt());
            }
        });
        showRunNanoTime("util.LinkedList", () -> {
            for (int i = 0; i < size; i++) {
                utilList.add(rnd.nextInt(utilList.size() + 1), rnd.nextInt());
            }
        });
    }

    private static void getListsRandomElements(int searchSize, Integer[] myListElements, Integer[] utilListElements) {
        for (int i = 0; i < searchSize; i++) {
            myListElements[i] = myList.get(rnd.nextInt(myList.size()));
            utilListElements[i] = utilList.get(rnd.nextInt(utilList.size()));
        }
    }

    private static void testGetTime(int size) {
        System.out.println("--- Get at random index (" + size + " times) performance comparison ---");
        showRunNanoTime("MyLinkedList", () -> {
            for (int i = 0; i < size; i++) {
                myList.get(rnd.nextInt(myList.size()));
            }
        });
        showRunNanoTime("util.LinkedList", () -> {
            for (int i = 0; i < size; i++) {
                utilList.get(rnd.nextInt(utilList.size()));
            }
        });
    }

    private static void testSearchTime(int size) {
        int searchSize = size / 10;
        System.out.println("--- Search " + searchSize + "(10%) random items performance comparison ---");
        Integer[] myListItems = new Integer[searchSize];
        Integer[] utilListItems = new Integer[searchSize];
        getListsRandomElements(searchSize, myListItems, utilListItems);

        showRunNanoTime("MyLinkedList", () -> {
            for (int i = 0; i < searchSize; i++) {
                myList.indexOf(myListItems[i]);
            }
        });
        showRunNanoTime("util.LinkedList", () -> {
            for (int i = 0; i < searchSize; i++) {
                utilList.indexOf(utilListItems[i]);
            }
        });
    }

    private static void testRemoveTime(int size) {
        System.out.println("--- Remove at random index (all elements are removed) performance comparison ---");
        showRunNanoTime("MyLinkedList", () -> {
            for (int i = 0; i < size; i++) {
                myList.remove(rnd.nextInt(myList.size()));
            }
        });
        showRunNanoTime("util.LinkedList", () -> {
            for (int i = 0; i < size; i++) {
                utilList.remove(rnd.nextInt(utilList.size()));
            }
        });
    }

    private static void clearLists() {
        myList.clear();
        utilList.clear();
    }

    public static void main(String[] args) {
        System.out.print("Enter elements count:");
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();

        testAppendTime(size);
        clearLists();
        testAddTime(size);
        testGetTime(size);
        testSearchTime(size);
        testRemoveTime(size);
    }
}
