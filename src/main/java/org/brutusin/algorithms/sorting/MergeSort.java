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

public class MergeSort {

    public static void sort(final int[] array) {
        int[] a = array;
        int[] aux = new int[array.length];

        for (int width = 2; width <= array.length; width = width * 2) {
            for (int i = 0; i < array.length; i = i + width) {
                int start = i;
                int med = Math.min(start + width / 2, array.length);
                int end = Math.min(start + width, array.length);
                merge(a, start, med, end, aux);
            }
            int[] temp = a;
            a = aux;
            aux = temp;
        }
        if (a != array) {
            for (int i = 0; i < array.length; i++) {
                array[i] = a[i];
            }
        }
    }

    /**
     * Merges array[start,med-1) and array[med,end) and saves the result in
     * aux[start...end)
     *
     * @param array
     * @param start
     * @param med
     * @param end
     * @param aux
     */
    public static void merge(int[] array, int start, int med, int end, int[] aux) {
        int i = start;
        int j = med;
        for (int k = start; k < end; k++) {
            if (j == end || array[i] < array[j] && i < med) {
                aux[k] = array[i];
                i++;
            } else {
                aux[k] = array[j];
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{-3, -3, -2, 1, -4, 0, 2, 2};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
