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
package org.brutusin.algorithms.combinations;

import org.brutusin.algorithms.utils.Utils;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 * http://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
 */
public class RecursionTreeKCombinations {

    public static void generate(final char[] elements, final int k) {
        generate(elements, new char[k], 0, 0);
    }

    /**
     * @param elements input array
     * @param output flyweight temporary array containing the output
     * @param length current output length
     * @param i current elements position
     */
    private static void generate(final char[] elements, final char[] output, final int i, final int length) {
        if (length == output.length) {
            Utils.printArray(output);
            return;
        }
        for (int j = i; j < elements.length; j++) {
            output[length] = elements[j];
            generate(elements, output, j + 1, length + 1);
        }
    }

    public static void main(String[] args) {
        generate(new char[]{'1', '2', '3', '4', '5'}, 3);
    }
}
