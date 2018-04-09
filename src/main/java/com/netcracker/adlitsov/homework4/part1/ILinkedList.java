package com.netcracker.adlitsov.homework4.part1;

import java.util.function.IntFunction;

/*
 * Tests are located in test/java/com/netcracker/adlitsov/homework4//part1/MyLinkedListTest.java instead of MainClass.java
 */

public interface ILinkedList<E> extends Iterable<E> {
    int size();

    void add(E element);

    void add(int index, E element);

    E set(int index, E element);

    E get(int index);

    int indexOf(E element);

    E remove(int index);

    void clear();

    Object[] toArray();

    <T> T[] toArray(T[] array);

    <T> T[] toArray(IntFunction<T[]> constructor);

    String toString();
}
