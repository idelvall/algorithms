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

import org.brutusin.algorithms.Utils;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class QuickSort {

    public static void sort(int[] array) {
        if (array == null) {
            return;
        }

        sort(array, 0, array.length - 1);
    }

    /**
     * Recursive range selection
     *
     * @param k
     * @param array
     * @param start inclusive
     * @param end inclusive
     * @return
     */
    private static void sort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int p = Utils.randomPartition(array, start, end);
        sort(array, p + 1, end);
        sort(array, start, p - 1);
    }
}
