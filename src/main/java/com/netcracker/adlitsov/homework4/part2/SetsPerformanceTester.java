package com.netcracker.adlitsov.homework4.part2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.netcracker.adlitsov.homework4.part2.ListsPerformanceTester.randAlphabeticStr;


public class SetsPerformanceTester {
    private static final int MAX_STR_LENGTH = 100;
    private static final String FORMAT_LINE = "%-30s %,15d ns\n";
    private static final int WARM_RUNS = 20;
    private static final int INNER_WARM_RUNS = 5;
    private static final int WARM_DATA_SIZE = 100_000;

    private Set<String> hashSet;
    private Set<String> linkedHashSet;
    private Set<String> treeSet;
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

    private void fillSet(Set<String> set, String[] elements) {
        for (int i = 0; i < elements.length; i++) {
            set.add(elements[i]);
        }
    }

    private void testAddTime(int size) {
        String[] elements = getRandomElements(size);
        if (!warmUp) {
            System.out.println("--- Add performance comparison ---");
            // Этот прогревочный цикл здесь необходим, поскольку тот hashSet, который будет заполняться
            // вторым, выполнит это значительно быстрее (видимо, JIT что-то успевает оптимизировать,в частности
            // все хеш-функции уже будут посчитаны и значения закешированы). В остальных методах результаты
            // выглядят достоверно без дополнительного прогрева.
            for (int i = 0; i < INNER_WARM_RUNS; i++) {
                fillSet(hashSet, elements);
                fillSet(linkedHashSet, elements);
                clearSets();
            }
        }

        showRunNanoTime(hashSet.getClass(), () -> fillSet(hashSet, elements));
        showRunNanoTime(linkedHashSet.getClass(), () -> fillSet(linkedHashSet, elements));
        showRunNanoTime(treeSet.getClass(), () -> fillSet(treeSet, elements));
    }

    private void containsSetElements(Set<String> set, int[] indexes) {
        for (int i = 0; i < indexes.length; i++) {
            set.contains(source[indexes[i]]);
        }
    }

    private void testContainsTime(int size) {
        int[] containsIndexes = getRandomIndexes(size);
        if (!warmUp) {
            System.out.println("--- Contains items (shuffled) performance comparison ---");
        }

        showRunNanoTime(hashSet.getClass(), () -> containsSetElements(hashSet, containsIndexes));
        showRunNanoTime(linkedHashSet.getClass(), () -> containsSetElements(linkedHashSet, containsIndexes));
        showRunNanoTime(treeSet.getClass(), () -> containsSetElements(treeSet, containsIndexes));
    }

    private void removeSetElements(Set<String> set, int[] indexes) {
        for (int i = 0; i < indexes.length; i++) {
            set.remove(source[indexes[i]]);
        }
    }

    private void testRemoveTime(int size) {
        int[] indexesToRemove = getRandomIndexes(size);
        if (!warmUp) {
            System.out.println("--- Remove items (shuffled) performance comparison ---");
        }

        showRunNanoTime(hashSet.getClass(), () -> removeSetElements(hashSet, indexesToRemove));
        showRunNanoTime(linkedHashSet.getClass(), () -> removeSetElements(linkedHashSet, indexesToRemove));
        showRunNanoTime(treeSet.getClass(), () -> removeSetElements(treeSet, indexesToRemove));
    }


    private int[] getRandomIndexes(int size) {
        List<Integer> allIndexes = IntStream.range(0, size).boxed().collect(Collectors.toList());
        Collections.shuffle(allIndexes);
        return allIndexes.stream().mapToInt(Integer::intValue).toArray();
    }

    private String[] getRandomElements(int size) {
        List<String> elements = Stream.of(source).collect(Collectors.toList());
        Collections.shuffle(elements);
        return elements.toArray(new String[elements.size()]);
    }

    private void generateSource(int size) {
        source = new String[size];
        for (int i = 0; i < size; i++) {
            source[i] = randAlphabeticStr(MAX_STR_LENGTH, rnd);
        }
    }

    private void createSets() {
        hashSet = new HashSet<>();
        linkedHashSet = new LinkedHashSet<>();
        treeSet = new TreeSet<>();
    }

    private void clearSets() {
        hashSet.clear();
        linkedHashSet.clear();
        treeSet.clear();
    }

    public void test(int size) {
        rnd = new Random();
        createSets();
        generateSource(size);

        testAddTime(size);
        testContainsTime(size);
        testRemoveTime(size);

        clearSets();
        source = null;
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

        SetsPerformanceTester lpt = new SetsPerformanceTester();
        lpt.warmUp(WARM_RUNS, WARM_DATA_SIZE);
        System.out.println("\n\n------ Benchmark begins ------");
        lpt.test(size);
    }
}