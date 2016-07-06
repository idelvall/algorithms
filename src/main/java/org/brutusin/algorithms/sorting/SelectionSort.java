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

/**
 * In each outer iteration selection sort finds the smallest of the remaining
 * elements and swaps it to the current position
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class SelectionSort {

    public static void sort(int[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length - 1; i++) {
            int jMin = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[jMin]) {
                    jMin = j;
                }
            }
            Utils.swap(array, i, jMin);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 4, 3, 2, 2, 1, 0, 2, 5};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
