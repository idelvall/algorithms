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
package org.brutusin.algorithms.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class Utils {

    private static final Random RANDOM = new Random();

    public static void swap(Object array, int i, int j) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Object is not an array");
        }
        if (i == j) {
            return;
        }
        Object temp = Array.get(array, i);
        Array.set(array, i, Array.get(array, j));
        Array.set(array, j, temp);
    }

    public static void swap(ArrayList array, int i, int j) {
        Object temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    /**
     * Quicksort partitioning
     *
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int randomPartition(int[] array, int start, int end) {
        int p = start + RANDOM.nextInt(end - start + 1);
        Utils.swap(array, start, p);
        int j = start;
        for (int i = start + 1; i <= end; i++) {
            if (array[i] < array[start]) {
                Utils.swap(array, i, j + 1);
                j++;
            }
        }
        Utils.swap(array, start, j);
        return j;
    }

    public static void printArray(Object array) {
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException("Object is not an array");
        }
        for (int i = 0; i < Array.getLength(array); i++) {
            System.out.print(Array.get(array, i));
        }
        System.out.println();
    }
}
