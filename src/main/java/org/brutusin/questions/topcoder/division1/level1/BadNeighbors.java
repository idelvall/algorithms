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
 */
public class BadNeighbors {

    public static int maxDonations(int[] donations) {
        int[] a = new int[donations.length]; // if 1st element is selected in final solution
        int[] b = new int[donations.length]; // if 1st element is not selected

        a[0] = donations[0];
        for (int i = 2; i < a.length; i++) {
            if (i == a.length - 1) {
                a[i] = Math.max(a[i - 1], donations[i]);
            } else {
                a[i] = a[i - 1];
                for (int j = i - 3; j < i - 1; j++) {
                    if (j < 0) {
                        continue;
                    }
                    int d = a[j] + donations[i];
                    if (d > a[i]) {
                        a[i] = d;
                    }
                }
            }
        }
        for (int i = 1; i < b.length; i++) {
            b[i] = Math.max(b[i - 1], donations[i]);
            for (int j = i - 3; j < i - 1; j++) {
                if (j < 0) {
                    continue;
                }
                int d = b[j] + donations[i];
                if (d > b[i]) {
                    b[i] = d;
                }
            }
        }

        return Math.max(a[a.length - 1], b[b.length - 1]);
    }

    public static void main(String[] args) {
        System.out.println(maxDonations(new int[]{94, 40, 49, 65, 21, 21, 106, 80, 92, 81, 679, 4, 61,
            6, 237, 12, 72, 74, 29, 95, 265, 35, 47, 1, 61, 397,
            52, 72, 37, 51, 1, 81, 45, 435, 7, 36, 57, 86, 81, 72}
        ));
    }
}
