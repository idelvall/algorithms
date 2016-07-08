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

import java.util.ArrayList;
import java.util.Comparator;
import org.brutusin.algorithms.Utils;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 * @param <E>
 */
public class Heap<E> {

    private final ArrayList<E> array = new ArrayList<E>();

    private final Comparator<E> comparator;

    public Heap() {
        this(null);
    }

    public Heap(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void insert(E item) {
        array.add(item);
        heapifyUp();
    }

    public E peek() {
        if (array.isEmpty()) {
            return null;
        }
        return array.get(0);
    }

    public E pop() {
        if (array.size() == 0) {
            return null;
        } else if (array.size() == 1) {
            return array.remove(0);
        } else {
            E ret = array.get(0);
            array.set(0, array.remove(array.size() - 1));
            heapifyDown();
            return ret;
        }
    }

    private void heapifyDown() {
        int i = 0;
        while (true) {
            E node = array.get(i);
            int leftChildIndex = getLeftChild(i);
            if (leftChildIndex < array.size() - 1) {
                E lChild = array.get(leftChildIndex);
                E rChild = array.get(leftChildIndex + 1);
                if (compare(lChild, rChild) < 0) {
                    if (compare(lChild, node) < 0) {
                        Utils.swap(array, i, leftChildIndex);
                        i = leftChildIndex;
                        continue;
                    }
                } else {
                    if (compare(rChild, node) < 0) {
                        Utils.swap(array, i, leftChildIndex + 1);
                        i = leftChildIndex + 1;
                        continue;
                    }
                }
            } else if (leftChildIndex == array.size() - 1) {
                E lChild = array.get(leftChildIndex);
                if (compare(lChild, node) < 0) {
                    Utils.swap(array, i, leftChildIndex);
                }
            }
            break;
        }
    }

    private void heapifyUp() {
        int i = array.size() - 1;
        while (i > 0) {
            int parentIndex = getParent(i);
            E node = array.get(i);
            E parent = array.get(parentIndex);
            if (compare(node, parent) < 0) {
                Utils.swap(array, i, parentIndex);
                i = parentIndex;
            } else {
                break;
            }
        }
    }

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        if (!(e1 instanceof Comparable) || !(e2 instanceof Comparable)) {
            throw new IllegalArgumentException("Items are not instance of java.lang.Comparable. Specify a Comparator in constructor instead");
        }
        Comparable c1 = (Comparable) e1;
        return c1.compareTo(e2);
    }

    public static int getParent(int n) {
        return (n - 1) / 2;
    }

    public static int getLeftChild(int n) {
        return 2 * n + 1;
    }

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<Integer>();
        heap.insert(5);
        heap.insert(53);
        heap.pop();
        heap.insert(1);
        heap.insert(3);

        while (true) {
            Integer element = heap.pop();
            if (element == null) {
                break;
            }
            System.out.println(element);
        }
    }
}
