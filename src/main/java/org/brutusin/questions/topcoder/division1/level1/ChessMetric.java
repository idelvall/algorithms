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
public class ChessMetric {

    private static int[][] MOVES = new int[][]{
        {-2, -1}, {-2, 1},
        {-1, -2}, {-1, -1}, {-1, 0}, {-1, 1}, {-1, 2},
        {0, -1}, {0, 1},
        {1, -2}, {1, -1}, {1, 0}, {1, 1}, {1, 2},
        {2, -1}, {2, 1},};

    public static long howMany(int size, int[] start, int[] end, int numMoves) {
        long[][] dp = new long[size][size];
        dp[start[0]][end[0]] = 1;
        for (int n = 1; n <= numMoves; n++) {
            long[][] next = new long[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (dp[i][j] > 0) {
                        for (int[] move : MOVES) {
                            int row = i + move[0];
                            int col = j + move[1];
                            if (0 <= row && row < size && 0 <= col && col < size) {
                                next[row][col] = next[row][col] + dp[i][j];
                            }
                        }
                    }
                }
            }
            dp = next;
        }
        return dp[end[0]][end[1]];
    }

    public static void main(String[] args) {
        System.out.println(howMany(100, new int[]{0, 0}, new int[]{0, 99}, 50));
    }
}
