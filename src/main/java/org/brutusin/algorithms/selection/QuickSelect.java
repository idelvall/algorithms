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

import java.util.Random;
import org.brutusin.algorithms.Utils;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class QuickSelect {

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
        return selectRange(k, array, 0, array.length - 1);
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
    
    private static int selectRange(int k, int[] array, int start, int end) {
        int p = Utils.randomPartition(array, start, end);
        if (p < k - 1) {
            return selectRange(k, array, p + 1, end);
        } else if (p > k - 1) {
            return selectRange(k, array, start, p - 1);
        } else {
            return array[p];
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 4, 3, 2, 1, 0, 2};
        for (int i = 0; i < 100; i++) {
            System.out.println(select(4, array));
        }
        
    }
}
