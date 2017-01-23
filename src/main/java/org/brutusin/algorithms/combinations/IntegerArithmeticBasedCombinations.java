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

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class IntegerArithmeticBasedCombinations {

    public static void generate(final char[] elements, final int k) {
        if (k < 1) {
            throw new IllegalArgumentException("k must be positive");
        }
        int b = elements.length;
        if (b > 36) {
            throw new IllegalArgumentException("at most " + b + " elements are supported");
        }
        int maxK = (int) Math.pow(Integer.MAX_VALUE, 1.0 / b);
        if (k > maxK) {
            throw new IllegalArgumentException("At most k=" + maxK + " is allowed for these elements");
        }
        int n = (int) Math.pow(b, k);
        for (int i = 0; i < n; i++) {
            System.out.println(transform(elements, b, k, Integer.toString(i, b)));
        }
    }

    private static String transform(final char[] elements, final int b, final int k, final String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k - s.length(); i++) {
            sb.append(elements[0]);
        }
        for (int i = 0; i < s.length(); i++) {
            sb.append(elements[Integer.valueOf(String.valueOf(s.charAt(i)), b)]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        generate(new char[]{'a', 'b', 'c'}, 2);
    }
}
