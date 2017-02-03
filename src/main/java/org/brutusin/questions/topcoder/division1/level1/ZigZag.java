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
package org.brutusin.questions.topcoder.division1.level1;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 * https://community.topcoder.com/stat?c=problem_statement&pm=1259&rd=4493
 */
public class ZigZag {

    public static int longestZigZag(int[] a) {
        int[] asc = new int[a.length];
        int[] desc = new int[a.length];

        asc[0] = 1;
        desc[0] = 1;

        for (int i = 1; i < a.length; i++) {
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i]) {
                    int s = asc[j] + 1;
                    if (desc[i] < s) {
                        desc[i] = s;
                    }
                }
                if (a[j] > a[i]) {
                    int s = desc[j] + 1;
                    if (asc[i] < s) {
                        asc[i] = s;
                    }
                }
            }
        }
        return Math.max(asc[asc.length - 1], desc[desc.length - 1]);
    }

    public static void main(String[] args) {
        System.out.println(longestZigZag(new int[]{374, 40, 854, 203, 203, 156, 362, 279, 812, 955,
            600, 947, 978, 46, 100, 953, 670, 862, 568, 188,
            67, 669, 810, 704, 52, 861, 49, 640, 370, 908,
            477, 245, 413, 109, 659, 401, 483, 308, 609, 120,
            249, 22, 176, 279, 23, 22, 617, 462, 459, 244}));
    }
}
