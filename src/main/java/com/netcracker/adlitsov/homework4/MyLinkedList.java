package com.netcracker.adlitsov.homework4;

import java.util.Iterator;
import java.util.Objects;

public class MyLinkedList<E> implements ILinkedList<E> {
    private Node<E> head;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E element) {
        if (head == null) {
            head = new Node<>(element, null);
        } else {
            Node<E> newNode = new Node<>(element, null);
            Node<E> lastNode = getNode(size - 1);
            lastNode.nextNode = newNode;
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
            if (index == 0) {
                head = new Node<>(element, head);
            } else {
                Node<E> prevNode = getNode(index - 1);
                prevNode.nextNode = new Node<>(element, prevNode.nextNode);
            }
            size++;
        }
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < size! Current index: " + index + ", size: " + size);
        }

        Node<E> nodeAtIndex = getNode(index);
        E prevValue = nodeAtIndex.element;
        nodeAtIndex.element = element;
        return prevValue;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < size! Current index: " + index + ", size: " + size);
        }
        return getNode(index).element;
    }

    private Node<E> getNode(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.nextNode;
        }
        return current;
    }

    @Override
    public int indexOf(E element) {
        Node<E> current = head;
        int currentIndex = 0;
        while (current != null) {
            if (Objects.equals(current.element, element)) {
                return currentIndex;
            }
            current = current.nextNode;
            currentIndex++;
        }
        return -1;
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
        E element;
        Node<E> nextNode;

        Node(E element, Node<E> nextNode) {
            this.element = element;
            this.nextNode = nextNode;
        }
    }
}
