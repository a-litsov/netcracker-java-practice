package com.netcracker.adlitsov.homework4;

import java.util.Iterator;

public class MyLinkedList<E> implements ILinkedList<E> {
    private Node<E> head, tail;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E element) {
        if (tail == null) {
            head = tail = new Node<>(element, null, null);
        } else {
            tail = new Node<>(element, tail, null);
            tail.prev.next = tail;
        }
        size++;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and <= size! Current index: " + index + ", size: " + size);
        }

        if (index == size) {
            add(element);
        } else {
            Node<E> nodeAtIndex = getNode(index);
            Node<E> newNode = new Node<>(element, nodeAtIndex.prev, nodeAtIndex);
            nodeAtIndex.prev = newNode;
            if (index == 0) {
                head = newNode;
            } else {
                newNode.prev.next = newNode;
            }
            size++;
        }
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < size! Current index: " + index + ", size: " + size);
        }

        return getNode(index).value;
    }

    private Node<E> getNode(int index) {
        int middle = size / 2;
        Node<E> current;
        if (index < middle) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
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

        Node(E value, Node<E> prev, Node<E> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
