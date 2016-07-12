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
package org.brutusin.algorithms.divcon;

import static org.brutusin.algorithms.sorting.MergeSort.merge;

/**
 * Counts the number of permutations in an array in O(N*logN). Based in merge
 * sort
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class Permutations {

    public static int countPermutations(int[] array) {
        int[] a = array;
        int[] aux = new int[array.length];
        int ret = 0;
        for (int width = 2; width < 2 * array.length; width = width * 2) {
            for (int i = 0; i < array.length; i = i + width) {
                int start = i;
                int med = Math.min(start + width / 2, array.length);
                int end = Math.min(start + width, array.length);
                ret += merge(a, start, med, end, aux);
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
        return ret;
    }

    /**
     * Merges the already sorted array[start,med-1) and array[med,end) and saves
     * the sorted result in aux[start...end). Also returns the number of
     * permutations found
     *
     * @param array
     * @param start
     * @param med
     * @param end
     * @param aux
     * @return
     */
    public static int merge(int[] array, int start, int med, int end, int[] aux) {
        int i = start;
        int j = med;
        int ret = 0;
        for (int k = start; k < end; k++) {
            if (j == end || array[i] < array[j] && i < med) {
                aux[k] = array[i];
                i++;
            } else {
                aux[k] = array[j];
                j++;
                ret += med - i;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(countPermutations(new int[]{3, 2, 1, 0}));
    }
}
