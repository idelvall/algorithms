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
package org.brutusin.algorithms.selection;

import java.util.Comparator;
import org.brutusin.datastructures.Heap;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class HeapSelect {

    /**
     * Returns the kth order statistic
     *
     * @param k 1-based
     * @param array
     * @return
     */
    public static int select(int k, int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("A non empty array is required");
        }
        if (k < 1 || k > array.length) {
            throw new IllegalArgumentException("Invalid k value");
        }
        Heap<Integer> maxHeap = new Heap<Integer>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });
        for (int i = 0; i < k; i++) {
            maxHeap.insert(array[i]);
        }
        for (int i = k; i < array.length; i++) {
            Integer max = maxHeap.peek();
            if (max > array[i]) {
                maxHeap.pop();
                maxHeap.insert(array[i]);
            }
        }
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 4, 3, 2, 1, 0, 2};
        for (int i = 0; i < 100; i++) {
            System.out.println(select(4, array));
        }

    }
}
