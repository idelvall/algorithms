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
package org.brutusin.questions.topcoder.division1.level3;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class RoboCourier {

    private static final int[][] DELTAS = new int[][]{{0, 1}, {-1, 1}, {-1, 0}, {0, -1}, {1, -1}, {1, 0}};
    private static final int[] SPIN_TIME = new int[]{0, 3, 6, 9, 6, 3};

    private static int timeToDeliver(int xf, int yf, boolean[][][] validEdges, int x, int y, int time, int direction, boolean[][] visited, boolean inertia) {

       // System.out.println(x + "," + y + " (" + time + ")");

        if (xf == x && yf == y) {
            return time + 4;
        }
        int minDtToDeliver = Integer.MAX_VALUE;
        for (int d = 0; d < 6; d++) {
            int nextX = x + DELTAS[d][0];
            int nextY = y + DELTAS[d][1];
            if (validEdges[x + 500][y + 500][d] && !visited[nextX + 500][nextY + 500]) {
                visited[nextX + 500][nextY + 500] = true;
                int dt;
                boolean nextInertia;
                if (x == 0 && y == 0) {
                    if (d == direction) {
                        dt = 0;
                        nextInertia = false;
                    } else {
                        dt = SPIN_TIME[(6 + d - direction) % 6];
                        nextInertia = false;
                    }
                } else {
                    if (d == direction) {
                        dt = inertia ? 2 : 4;
                        nextInertia = true;
                    } else {
                        dt = SPIN_TIME[(6 + d - direction) % 6] + 4;
                        nextInertia = false;
                    }
                }
                int dtToDeliver = timeToDeliver(xf, yf, validEdges, nextX, nextY, time + dt, d, visited, nextInertia);
                if (dtToDeliver < minDtToDeliver) {
                    minDtToDeliver = dtToDeliver;
                }
                visited[nextX + 500][nextY + 500] = false;
            }
        }
        return minDtToDeliver;
    }

    public static int timeToDeliver(String[] path) {
        boolean[][][] validEdges = new boolean[1001][1001][6];
        boolean[][] visited = new boolean[1001][1001];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.length; i++) {
            sb.append(path[i]);
        }
        int x = 0;
        int y = 0;
        int d = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == 'F') {
                validEdges[x + 500][y + 500][d] = true;
                x += DELTAS[d][0];
                y += DELTAS[d][1];
                validEdges[x + 500][y + 500][(d + 3) % 6] = true;
            } else if (sb.charAt(i) == 'L') {
                d = (6 + d - 1) % 6;
            } else if (sb.charAt(i) == 'R') {
                d = (6 + d + 1) % 6;
            } else {
                throw new IllegalArgumentException();
            }
        }
       // System.out.println(x + " " + y);
        if (x == 0 && y == 0) {
            return 0;
        }
        return timeToDeliver(x, y, validEdges, 0, 0, 0, 0, visited, false);
    }

    public static void main(String[] args) {
        System.out.println(timeToDeliver(new String[]{"FRRFLLFLLFRRFLF"}));
        System.out.println(timeToDeliver(new String[]{"RFLLF"}));
        System.out.println(timeToDeliver(new String[]{"FLFRRFRFRRFLLFRRF"}));
        System.out.println(timeToDeliver(new String[]{"FFFFFFFFFRRFFFFFFRRFFFFF", "FLLFFFFFFLLFFFFFFRRFFFF"}));
        System.out.println(timeToDeliver(new String[]{"RFLLFLFLFRFRRFFFRFFRFFRRFLFFRLRRFFLFFLFLLFRFLFLRFF", "RFFLFLFFRFFLLFLLFRFRFLRLFLRRFLRFLFFLFFFLFLFFRLFRLF", "LLFLFLRLRRFLFLFRLFRF"}));
        System.out.println(timeToDeliver(new String[]{"LLFLFRLRRLRFFLRRRRFFFLRFFRRRLLFLFLLRLRFFLFRRFFFLFL", "RLFFRRLRLRRFFFLLLRFRLLRFFLFRLFRRFRRRFRLRLRLFFLLFLF", "FRFLRFRRLLLRFFRRRLRFLFRRFLFFRLFLFLFRLLLLFRLLRFLLLF", "FFLFRFRRFLLFFLLLFFRLLFLRRFRLFFFRRFFFLLRFFLRFRRRLLR", "FFFRRLLFLLRLFRRLRLLFFFLFLRFFRLRLLFLRLFFLLFFLLFFFRR", "LRFRRFLRRLRRLRFFFLLLLRRLRFFLFRFFRLLRFLFRRFLFLFFLFR", "RFRRLRRFLFFFLLRFLFRRFRFLRLRLLLLFLFFFLFRLLRFRLFRLFR", "LLFLFRLFFFFFFFRRLRLRLLRFLRLRRRRRRRRLFLFLFLRFLFRLFF", "RLFRRLLRRRRFFFRRRLLLLRRLFFLLLLLRFFFFRFRRLRRRFFFLLF", "FFFFLRRLRFLLRRLRLRFRRRRLFLLRFLRRFFFRFRLFFRLLFFRRLL"}));
    }
}
