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
 * Swaps elements to the right until there are no more swaps
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class BubbleSort {

    public static void sort(int[] array) {
        if (array == null) {
            return;
        }
        int n = array.length;
        while (true) {
            boolean swapped = false;
            int newn = n - 1;
            for (int i = 1; i < n; i++) {
                if (array[i] < array[i - 1]) {
                    Utils.swap(array, i, i - 1);
                    swapped = true;
                    newn = i;
                }
            }
            if (!swapped) {
                break;
            }
            n = newn;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 4, 3, 2, 2, 1, 0, 2, 5};
        sort(array);
        System.out.println(Arrays.toString(array));
    }
}
