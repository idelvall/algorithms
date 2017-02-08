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
package org.brutusin.questions.topcoder.division1.level2;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class StripePainter {

    private static String reduce(String s) {
        StringBuilder sb = new StringBuilder();
        sb.append(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (sb.charAt(sb.length() - 1) != s.charAt(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    public static int minStrokes(String stripes) {
        stripes = reduce(stripes);
        int[][][] cache = new int[stripes.length()][stripes.length()]['Z' - 'A' + 1];
        return 1 + minStrokes(stripes, 0, stripes.length() - 1, cache, stripes.charAt(0));
    }

    private static int minStrokes(String stripes, int start, int end, int[][][] cache, char c) {
        int ret = cache[start][end][c - 'A'];
        if (ret == 0) {
            if (start == end) {
                ret = stripes.charAt(start) == c ? 0 : 1;
            } else {
                int min = Integer.MAX_VALUE;
                for (int i = start; i < end; i++) {
                    int strokes = (stripes.charAt(i + 1) == c ? 0 : 1) + minStrokes(stripes, start, i, cache, c) + minStrokes(stripes, i + 1, end, cache, stripes.charAt(i + 1));
                    if (strokes < min) {
                        min = strokes;
                    }
                }
                ret = min;
            }
            cache[start][end][c - 'A'] = ret;
        }
        return ret;

    }

    public static void main(String[] args) {
        System.out.println(minStrokes("RGBGR"));
        System.out.println(minStrokes("RGRG"));
        System.out.println(minStrokes("ABACADA"));
        System.out.println(minStrokes("AABBCCDDCCBBAABBCCDD"));
        System.out.println(minStrokes("BECBBDDEEBABDCADEAAEABCACBDBEECDEDEACACCBEDABEDADD"));
        
    }
}
