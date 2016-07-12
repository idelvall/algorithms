/*
 * Copyright 2016 Ignacio del Valle Alles idelvall@brutusin.org.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.brutusin.datastructures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class LinkedList<E> implements Iterable<E> {

    private Node first;
    private Node last;
    private int size;
    private int operationCounter;

    public void add(E element) {
        Node node = new Node();
        node.element = element;
        if (last == null) {
            first = node;
        } else {
            last.next = node;
        }
        last = node;
        size++;
        operationCounter++;
    }

    public void add(LinkedList<E> list) {
        last.next = list.first;
        last = list.last;
        size += list.size;
        operationCounter++;
    }

    public E getFirst() {
        return first.element;
    }

    public E getLast() {
        return last.element;
    }

    public E removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        E ret = first.element;
        first = first.next;
        size--;
        if (size == 0) {
            last = null;
        }
        operationCounter++;
        return ret;
    }
    
    public Iterator<E> iterator() {
        final int counter = operationCounter;

        return new Iterator<E>() {

            Node node = null;

            public boolean hasNext() {
                check();
                if (node == null) {
                    return first != null;
                } else {
                    return node.next != null;
                }
            }

            public E next() {
                check();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (node == null) {
                    node = first;
                } else {
                    node = node.next;
                }
                return node.element;
            }

            void check() {
                if (operationCounter != counter) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    public int getSize() {
        return size;
    }

    private class Node {

        E element;
        Node next;
    }

}
