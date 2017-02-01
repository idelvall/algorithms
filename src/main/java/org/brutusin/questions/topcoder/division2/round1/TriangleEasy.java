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
package org.brutusin.questions.topcoder.division2.round1;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
//https://community.topcoder.com/stat?c=problem_statement&pm=14324
public class TriangleEasy {

    public static int find(int n, int[] x, int[] y) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < x.length; i++) {
            List<Integer> listX = graph[x[i]];
            if (listX == null) {
                listX = new LinkedList<Integer>();
                graph[x[i]] = listX;
            }
            List<Integer> listY = graph[y[i]];
            if (listY == null) {
                listY = new LinkedList<Integer>();
                graph[y[i]] = listY;
            }
            listX.add(y[i]);
            listY.add(x[i]);
        }
        int min = 3;
        for (int i = 0; i < graph.length; i++) {
            Set<Integer> visited = new HashSet<Integer>();
            if (traverse(graph, 3, i, i, visited)) {
                return 0;
            } else {
                int needed;
                if (visited.size() == 0) {
                    needed = 3;
                } else if (visited.size() == 1) {
                    needed = 2;
                } else {
                    needed = 1;
                }
                if (needed < min) {
                    min = needed;
                }
            }
        }
        return min;
    }

    private static boolean traverse(List<Integer>[] graph, int hops, int start, int v, Set<Integer> visited) {
        if (hops == 0) {
            return false;
        }
        List<Integer> neighbors = graph[v];
        if (neighbors != null) {
            for (Integer w : neighbors) {
                if (w == start && hops == 1) {
                    return true;
                }
                if (!visited.contains(w)) {
                    visited.add(v);
                    if (traverse(graph, hops - 1, start, w, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {

        System.out.println(
                find(20,
                        new int[]{16, 4, 15, 6, 1, 0, 10, 12, 7, 15, 2, 4, 8, 1, 10, 15, 13, 10, 1, 16, 3, 19, 8, 7, 13, 1, 15, 15, 15, 5, 16, 7, 5, 6, 4, 18, 3, 8, 6, 2, 16, 8, 19, 14, 17, 16, 4, 6, 9, 17, 4, 10, 8, 12, 2, 3, 18, 9, 13, 17, 4, 7, 10, 0, 13, 11, 15, 17, 11, 15, 11, 19, 19, 4, 10, 14, 16, 6, 3, 17, 1, 4, 14, 9, 7, 18, 10, 11, 5, 0, 5, 9, 9, 7, 16, 12, 4, 10, 17, 3},
                        new int[]{17, 18, 6, 16, 18, 6, 11, 2, 15, 10, 1, 15, 17, 8, 5, 9, 7, 0, 0, 4, 16, 1, 9, 0, 9, 5, 17, 14, 1, 12, 14, 11, 9, 18, 0, 12, 11, 3, 19, 14, 7, 6, 3, 19, 0, 1, 19, 5, 11, 19, 2, 13, 12, 0, 6, 2, 14, 16, 14, 18, 5, 5, 19, 3, 6, 14, 12, 5, 17, 3, 1, 12, 7, 11, 8, 8, 10, 11, 13, 2, 13, 13, 0, 18, 2, 7, 2, 12, 14, 9, 3, 19, 2, 8, 12, 13, 8, 18, 13, 18}
                ));
    }
}
