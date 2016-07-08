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
package org.brutusin.algorithms.sorting;

import java.util.Arrays;
import org.brutusin.algorithms.Utils;
import org.brutusin.datastructures.Heap;
import static org.brutusin.datastructures.Heap.getParent;

/**
 * Swaps elements to the right until there are no more swaps
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class HeapSort {

    public static void sort(int[] array) {
        if (array == null) {
            return;
        }
        // Heapify array in place
        buildMaxHeap(array);
        int iMax = array.length - 1;
        // One by one extract the max, shrink the heap, and swap the current max to the last position of the heap
        while (iMax > 0) {
            heapifyDown(array, iMax);
            Utils.swap(array, 0, iMax);
            iMax--;
        }
    }

    private static void buildMaxHeap(int[] array) {
        for (int i = 0; i < array.length; i++) {
            heapifyUp(array, i);
        }
    }

    private static void heapifyDown(int[] array, int iMax) {
        int i = 0;
        while (true) {
            int node = array[i];
            int leftChildIndex = Heap.getLeftChild(i);
            if (leftChildIndex < iMax) {
                int lChild = array[leftChildIndex];
                int rChild = array[leftChildIndex + 1];
                if (lChild > rChild) {
                    if (lChild > node) {
                        Utils.swap(array, i, leftChildIndex);
                        i = leftChildIndex;
                        continue;
                    }
                } else {
                    if (rChild > node) {
                        Utils.swap(array, i, leftChildIndex + 1);
                        i = leftChildIndex + 1;
                        continue;
                    }
                }
            } else if (leftChildIndex == iMax) {
                int lChild = array[leftChildIndex];
                if (lChild > node) {
                    Utils.swap(array, i, leftChildIndex);
                }
            }
            break;
        }
    }

    private static void heapifyUp(int[] array, int i) {
        while (i > 0) {
            int parentIndex = Heap.getParent(i);
            int node = array[i];
            int parent = array[parentIndex];
            if (node > parent) {
                Utils.swap(array, i, parentIndex);
                i = parentIndex;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{-3, -4, -2, -3, 0, 0, 1, -4};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
