package com.netcracker.adlitsov.homework4.part2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.netcracker.adlitsov.homework4.part2.ListsPerformanceTester.randAlphabeticStr;


public class SetsPerformanceTester {
    private static final int MAX_STR_LENGTH = 100;
    private static final String FORMAT_LINE = "%-30s %,15d ns\n";
    private static final int WARM_RUNS = 50;
    private static final int WARM_DATA_SIZE = 50_000;
    private static final int INNER_WARM_RUNS = 5;

    private Set<String> hashSet;
    private Set<String> linkedHashSet;
    private Set<String> treeSet;
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
            // все хеш-функции уже будут посчитаны и значения закешированы). TreeSet прогреваем для чистоты
            // эксперимента. В остальных методах аналогично, иначе результаты получатся недостоверными (
            // например, будут cильно зависеть от порядка заполнения Set'ов и т.п.).
            for (int i = 0; i < INNER_WARM_RUNS; i++) {
                fillSet(hashSet, elements);
                fillSet(linkedHashSet, elements);
                fillSet(treeSet, elements);
                clearSets();
                createSets();
            }
            System.gc();
        }

        showRunNanoTime(hashSet.getClass(), () -> fillSet(hashSet, elements));
        showRunNanoTime(linkedHashSet.getClass(), () -> fillSet(linkedHashSet, elements));
        showRunNanoTime(treeSet.getClass(), () -> fillSet(treeSet, elements));
    }

    private void containsSetElements(Set<String> set, String[] elements) {
        for (int i = 0; i < elements.length; i++) {
            set.contains(elements[i]);
        }
    }

    private void testContainsTime(int size) {
        String[] elements = getRandomElements(size);
        if (!warmUp) {
            System.out.println("--- Contains items (shuffled) performance comparison ---");
            for (int i = 0; i < INNER_WARM_RUNS; i++) {
                containsSetElements(hashSet, elements);
                containsSetElements(linkedHashSet, elements);
                containsSetElements(treeSet, elements);
            }
        }

        showRunNanoTime(hashSet.getClass(), () -> containsSetElements(hashSet, elements));
        showRunNanoTime(linkedHashSet.getClass(), () -> containsSetElements(linkedHashSet, elements));
        showRunNanoTime(treeSet.getClass(), () -> containsSetElements(treeSet, elements));
    }

    private void removeSetElements(Set<String> set, String[] elements) {
        for (int i = 0; i < elements.length; i++) {
            set.remove(elements[i]);
        }
    }

    private void testRemoveTime(int size) {
        String[] elements = getRandomElements(size);
        if (!warmUp) {
            System.out.println("--- Remove items (shuffled) performance comparison ---");
            for (int i = 0; i < INNER_WARM_RUNS; i++) {
                removeSetElements(new HashSet<>(hashSet), elements);
                removeSetElements(new LinkedHashSet<>(hashSet), elements);
                removeSetElements(new TreeSet<>(hashSet), elements);
            }
        }

        showRunNanoTime(hashSet.getClass(), () -> removeSetElements(hashSet, elements));
        showRunNanoTime(linkedHashSet.getClass(), () -> removeSetElements(linkedHashSet, elements));
        showRunNanoTime(treeSet.getClass(), () -> removeSetElements(treeSet, elements));
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

        SetsPerformanceTester spt = new SetsPerformanceTester();
        spt.warmUp(WARM_RUNS, WARM_DATA_SIZE);
        System.out.println("\n\n------ Benchmark begins ------");
        spt.test(size);
    }
}