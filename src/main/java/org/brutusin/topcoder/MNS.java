/*
 * Copyright 2017 Ignacio del Valle Alles idelvall@brutusin.org.
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
package org.brutusin.topcoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.brutusin.algorithms.utils.Utils;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 * https://community.topcoder.com/stat?c=problem_statement&pm=1744&rd=4545
 */
public class MNS {

    public static void run(int[] s) {
        int l = (int) Math.sqrt(s.length);
        if (l * l != s.length) {
            throw new IllegalArgumentException("Array length must be a perfect square");
        }
        HashSet solutions = new HashSet<String>();
        permute(s, 0, solutions);
        System.out.println(solutions.size());
    }

    private static void permute(int[] s, int start, final Set<String> solutions) {
        if (!verify(s, start - 1)) {
            // Skip recursion tree as soon as restrictions are violated
            return;
        }
        if (start == s.length - 1) {
            solutions.add(Arrays.toString(s));
        } else {
            for (int i = start; i < s.length; i++) {
                Utils.swap(s, start, i);
                permute(s, start + 1, solutions);
                Utils.swap(s, i, start);
            }
        }
    }

    private static void viewSquare(int[] s) {
        int l = (int) Math.sqrt(s.length);
        for (int i = 0; i < s.length; i++) {
            if (i > 0 && i % l == 0) {
                System.out.println("");
            }
            System.out.print(s[i]);
        }
        System.out.println("");
    }

    /**
     * Validates the MNS properties affecting the i-th element. If the row or
     * column of this element contain elements greater than it the validation
     * succeeds.
     *
     * This allows a complete O(n^3/2) bottom-up validation, calling this method
     * repeatedly with index from 0 to length-1, that is an incremental
     * validation
     *
     * @param s
     * @param n
     * @return
     */
    private static boolean verify(int[] s, int index) {
        int l = (int) Math.sqrt(s.length);
        if (index < l - 1) {
            return true;
        }
        int prevSum = 0;
        int i = getRow(l, index);
        int j = getColumn(l, index);

        for (int k = 0; k < l; k++) {
            prevSum += s[k];
        }

        // if row is complete
        if (j == l - 1) {
            int sum = 0;
            for (int k = 0; k < l; k++) {
                sum += s[k + i * l];
            }
            if (sum != prevSum) {
                return false;
            }
        }

        // if column is complete
        if (i == l - 1) {
            int sum = 0;
            for (int k = 0; k < l; k++) {
                sum += s[j + k * l];
            }
            if (sum != prevSum) {
                return false;
            }
        }
        return true;
    }

    private static int getRow(int l, int index) {
        return index / l;
    }

    private static int getColumn(int l, int index) {
        return index % l;
    }

    public static void main(String[] args) {
        int[] s = new int[]{1, 2, 3, 3, 2, 1, 2, 2, 2};
        run(s);
    }

}
