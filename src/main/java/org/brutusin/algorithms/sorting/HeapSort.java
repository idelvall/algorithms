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
        Heap<Integer> heap = new Heap<Integer>();
        for (int i = 0; i < array.length; i++) {
            heap.insert(array[i]);
        }
        for (int i = 0; i < array.length; i++) {
            array[i] = heap.pop();
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{-3, -4, -2, -3, 0, 0, 1,-4};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
