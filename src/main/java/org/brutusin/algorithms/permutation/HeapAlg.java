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
package org.brutusin.algorithms.permutation;

import java.util.Arrays;
import org.brutusin.algorithms.utils.Utils;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class HeapAlg {

    public static void permute(char[] c) {
        permute(c, c.length);
    }

    /**
     * Generates the permutation of the first n-1 elements
     *
     * @param c
     * @param n
     */
    private static void permute(char[] c, int n) {
        if (n == 1) {
            Utils.printArray(c);
            return;
        }
        // System.out.println(Arrays.toString(c));
        for (int i = 0; i < n - 1; i++) {
            permute(c, n - 1);
            int k = n % 2 == 0 ? i : 0;
            int j = n - 1;
            // System.out.println("\t" + k + "<->" + j);
            Utils.swap(c, k, j);
        }
        permute(c, n - 1);
    }

    public static void main(String[] args) {
        permute(new char[]{'a', 'b', 'c'}, 3);
    }
}
