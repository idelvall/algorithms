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
 * In each outer iteration insertion sort suppose the left sub-array already
 * sorted and swaps the current element the necessary times to place it in its
 * position
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class InsertionSort {

    public static void sort(int[] array) {
        if (array == null) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            int j = i;
            while (j > 0 && array[j] < array[j - 1]) {
                Utils.swap(array, j, j - 1);
                j--;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 4, 3, 2, 2, 1, 0, 2, 5};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
