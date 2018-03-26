package com.netcracker.adlitsov.homework4;

public interface ILinkedList<E> extends Iterable<E> {
    int size();

    void add(E element);

    void add(int index, E element);

    E set(int index, E element);

    E get(int index);

    int indexOf(E element);

    E remove(int index);

    void clear();

    E[] toArray();

    String toString();
}
