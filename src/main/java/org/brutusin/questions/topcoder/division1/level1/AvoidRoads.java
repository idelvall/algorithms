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

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class AvoidRoads {

    private static boolean hasVerticalArrival(int i, int j, boolean[][][] bad) {
        return j > 0 && !bad[i][j][1];
    }

    private static boolean hasHorizontalArrival(int i, int j, boolean[][][] bad) {
        return i > 0 && !bad[i][j][0];
    }

    private static long numWays(int i, int j, boolean[][][] bad, long[][] cache) {
        long ret = cache[i][j];
        if (ret > 0) {
            return ret;
        }
        if (hasVerticalArrival(i, j, bad)) {
            ret += numWays(i, j - 1, bad, cache);
        }
        if (hasHorizontalArrival(i, j, bad)) {
            ret += numWays(i - 1, j, bad, cache);
        }
        cache[i][j] = ret;
        return ret;
    }

    public static long numWays(int width, int height, String[] bad) {
        boolean[][][] badIndex = new boolean[width + 1][height + 1][2];
        if (bad != null) {
            for (int i = 0; i < bad.length; i++) {
                Scanner sc = new Scanner(bad[i]);
                int[] p1 = new int[]{sc.nextInt(), sc.nextInt()};
                int[] p2 = new int[]{sc.nextInt(), sc.nextInt()};
                if (p1[0] == p2[0]) { // Vertical 
                    if (p1[1] < p2[1]) { // p2 is target 
                        badIndex[p2[0]][p2[1]][1] = true;
                    } else { // p1 is target
                        badIndex[p1[0]][p1[1]][1] = true;
                    }
                } else { // Horizontal
                    if (p1[0] < p2[0]) { // p2 is target 
                        badIndex[p2[0]][p2[1]][0] = true;
                    } else { // p1 is target
                        badIndex[p1[0]][p1[1]][0] = true;
                    }
                }
            }
        }
        long[][] cache = new long[width + 1][height + 1];
        cache[0][0] = 1;
        return numWays(width, height, badIndex, cache);
    }

    public static void main(String[] args) {
        System.out.println(numWays(
                35,
                31,
                null));
        System.out.println(numWays(
                6,
                6,
                new String[]{"0 0 0 1", "6 6 5 6"}));
    }
}
