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

import org.brutusin.algorithms.utils.Utils;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 * http://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 */
public class PermutationTree {
    
    public static void permute(char[] c) {
        permute(c, 0);
    }

    /**
     * Generates the permutation tree of the elements following 'start' element
     *
     * @param c
     * @param n
     */
    private static void permute(char[] c, int start) {
        if (start == c.length - 1) {
            Utils.printArray(c);
        } else {
            for (int i = start; i < c.length; i++) {
                Utils.swap(c, start, i);
                permute(c, start + 1);
                Utils.swap(c, i, start);
            }
        }
    }
    
    public static void main(String[] args) {
        permute(new char[]{'a', 'b', 'c'});
    }
}
