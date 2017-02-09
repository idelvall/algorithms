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
package org.brutusin.questions.topcoder.division2.level3;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class ABC {

    private String createString(int k, int K, int N, StringBuilder sb) {
        if (sb.length() < N) {
            sb.insert(K - k, "C");
            while (sb.length() < N) {
                sb.insert(0, "C");
            }
        }
        return sb.toString();
    }

    public String createString(int N, int K) {
        int k = 0;
        StringBuilder sb = new StringBuilder("A");
        int l;
        while ((l = sb.length()) < N) {
            int dk = l - l / 3;
            if (k + dk > K) {
                return createString(k, K, N, sb);
            }
            if (l % 3 == 0) {
                sb.insert(0, "A");
            } else if (l % 3 == 1) {
                sb.append("C");
            } else {
                sb.insert(l / 2, "B");
            }
            k += dk;
            if (k == K) {
                return createString(k, K, N, sb);
            }
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(new ABC().createString(3, 2));
    }
}
