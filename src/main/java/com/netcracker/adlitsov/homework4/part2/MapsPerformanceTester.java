package com.netcracker.adlitsov.homework4.part2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.netcracker.adlitsov.homework4.part2.ListsPerformanceTester.randAlphabeticStr;


public class MapsPerformanceTester {
    private static final int MAX_STR_LENGTH = 100;
    private static final String FORMAT_LINE = "%-30s %,15d ns\n";
    private static final int WARM_RUNS = 50;
    private static final int WARM_DATA_SIZE = 50_000;
    private static final int INNER_WARM_RUNS = 5;

    private Map<String, Integer> hashMap;
    private Map<String, Integer> linkedHashMap;
    private Map<String, Integer> treeMap;
    private String[] keySource;
    private Integer[] valueSource;

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

    private void fillMap(Map<String, Integer> map, String[] keys, Integer[] values) {
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], values[i]);
        }
    }

    private void testAddTime(int size) {
        String[] keys = getRandomKeys(size);
        Integer[] values = getRandomValues(size);
        if (!warmUp) {
            System.out.println("--- Add performance comparison ---");
            // Этот прогревочный цикл здесь необходим, поскольку тот hashMap, который будет заполняться
            // вторым, выполнит это значительно быстрее (видимо, JIT что-то успевает оптимизировать,в частности
            // все хеш-функции уже будут посчитаны и значения закешированы). TreeMap прогреваем для чистоты
            // эксперимента. В остальных методах аналогично, иначе результаты получатся недостоверными (
            // например, будут cильно зависеть от порядка заполнения Map'ов и т.п.).
            for (int i = 0; i < INNER_WARM_RUNS; i++) {
                fillMap(hashMap, keys, values);
                fillMap(linkedHashMap, keys, values);
                fillMap(treeMap, keys, values);
                clearMaps();
                createMaps();
            }
            System.gc();
        }

        showRunNanoTime(hashMap.getClass(), () -> fillMap(hashMap, keys, values));
        showRunNanoTime(linkedHashMap.getClass(), () -> fillMap(linkedHashMap, keys, values));
        showRunNanoTime(treeMap.getClass(), () -> fillMap(treeMap, keys, values));
    }

    private void containsMapElements(Map<String, Integer> Map, String[] keys) {
        for (int i = 0; i < keys.length; i++) {
            Map.containsKey(keys[i]);
        }
    }

    private void testContainsTime(int size) {
        String[] keys = getRandomKeys(size);
        if (!warmUp) {
            System.out.println("--- Contains items (shuffled) performance comparison ---");
            for (int i = 0; i < INNER_WARM_RUNS; i++) {
                containsMapElements(hashMap, keys);
                containsMapElements(linkedHashMap, keys);
                containsMapElements(treeMap, keys);
            }
        }

        showRunNanoTime(hashMap.getClass(), () -> containsMapElements(hashMap, keys));
        showRunNanoTime(linkedHashMap.getClass(), () -> containsMapElements(linkedHashMap, keys));
        showRunNanoTime(treeMap.getClass(), () -> containsMapElements(treeMap, keys));
    }

    private void removeMapElements(Map<String, Integer> Map, String[] keys) {
        for (int i = 0; i < keys.length; i++) {
            Map.remove(keys[i]);
        }
    }

    private void testRemoveTime(int size) {
        String[] keys = getRandomKeys(size);
        if (!warmUp) {
            System.out.println("--- Remove items (shuffled) performance comparison ---");
            for (int i = 0; i < INNER_WARM_RUNS; i++) {
                removeMapElements(new HashMap<>(hashMap), keys);
                removeMapElements(new LinkedHashMap<>(hashMap), keys);
                removeMapElements(new TreeMap<>(hashMap), keys);
            }
        }

        showRunNanoTime(hashMap.getClass(), () -> removeMapElements(hashMap, keys));
        showRunNanoTime(linkedHashMap.getClass(), () -> removeMapElements(linkedHashMap, keys));
        showRunNanoTime(treeMap.getClass(), () -> removeMapElements(treeMap, keys));
    }

    private String[] getRandomKeys(int size) {
        List<String> elements = Stream.of(keySource).collect(Collectors.toList());
        Collections.shuffle(elements);
        return elements.toArray(new String[elements.size()]);
    }

    private Integer[] getRandomValues(int size) {
        List<Integer> elements = Stream.of(valueSource).collect(Collectors.toList());
        Collections.shuffle(elements);
        return elements.toArray(new Integer[elements.size()]);
    }

    private void generateSources(int size) {
        keySource = new String[size];
        for (int i = 0; i < size; i++) {
            keySource[i] = randAlphabeticStr(MAX_STR_LENGTH, rnd);
        }
        valueSource = rnd.ints(size).boxed().toArray(Integer[]::new);
    }

    private void createMaps() {
        hashMap = new HashMap<>();
        linkedHashMap = new LinkedHashMap<>();
        treeMap = new TreeMap<>();
    }

    private void clearMaps() {
        hashMap.clear();
        linkedHashMap.clear();
        treeMap.clear();
    }

    private void clearSources() {
        keySource = null;
        valueSource = null;
    }

    public void test(int size) {
        rnd = new Random();
        createMaps();
        generateSources(size);

        testAddTime(size);
        testContainsTime(size);
        testRemoveTime(size);

        clearMaps();
        clearSources();
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

        MapsPerformanceTester spt = new MapsPerformanceTester();
        spt.warmUp(WARM_RUNS, WARM_DATA_SIZE);
        System.out.println("\n\n------ Benchmark begins ------");
        spt.test(size);
    }
}