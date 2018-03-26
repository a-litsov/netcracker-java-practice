package com.netcracker.adlitsov.homework4;

import java.util.Iterator;

public class MyLinkedList<E> implements ILinkedList<E> {
    Node<E> head;
    int size = 0;

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(E element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E get(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int indexOf(E element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E[] toArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static class Node<E> {
        E value;
        Node<E> prev, next;

        public Node(E value, Node<E> prev, Node<E> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
