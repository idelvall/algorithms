/*
 * Copyright 2016 Ignacio del Valle Alles idelvall@brutusin.org.
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
package org.brutusin.algorithms.divcon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import org.brutusin.algorithms.utils.Point;
import org.brutusin.commons.Pair;

/**
 * Finds the closest pairs among a 2D point set. Uses a divide an conquer
 * strategy that runs in O(NlogN)
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class NearestPoints {

    private static final double EPS = 1e-6;

    public static Set<Pair<Point, Point>> getClosestPairs(Point[] points) {
        Integer[] xOrder = sortByX(points);
        Integer[] yOrder = sortByY(points);
        Set<Pair<Integer, Integer>> pairIds = getClosestPairs(points, 0, points.length - 1, xOrder, yOrder);
        if (pairIds == null) {
            return null;
        }
        Set<Pair<Point, Point>> ret = new HashSet<Pair<Point, Point>>(pairIds.size());
        for (Pair<Integer, Integer> pairId : pairIds) {
            ret.add(new Pair<Point, Point>(points[pairId.getElement1()], points[pairId.getElement2()]));
        }
        return ret;
    }

    private static Set<Pair<Integer, Integer>> getClosestPairs(Point[] points, int start, int end, Integer[] xOrder, Integer[] yOrder) {
        if (end <= start) {
            return null;
        }
        if (end == start + 1) {
            Set<Pair<Integer, Integer>> ret = new HashSet<Pair<Integer, Integer>>();
            ret.add(new Pair<Integer, Integer>(xOrder[start], xOrder[end]));
            return ret;
        } else {
            int med = (end + start) / 2;
            Set<Pair<Integer, Integer>> leftClosestPairs = getClosestPairs(points, start, med, xOrder, yOrder);
            Set<Pair<Integer, Integer>> rightClosestPairs = getClosestPairs(points, med + 1, end, xOrder, yOrder);
            Set<Pair<Integer, Integer>> closest = getClosest(points, leftClosestPairs, rightClosestPairs);
            Set<Pair<Integer, Integer>> splitClosest = getSplitClosestPairs(points, med, xOrder, yOrder, getDistance(points, closest));
            return getClosest(points, closest, splitClosest);
        }
    }

    private static Set<Pair<Integer, Integer>> getSplitClosestPairs(Point[] points, int med, Integer[] xOrder, Integer[] yOrder, double delta) {
        boolean[] candidates = new boolean[points.length];
        Point mPoint = points[xOrder[med]];
        int counter = 0;
        for (int i = med; i >= 0; i--) {
            int pointId = xOrder[i];
            Point p = points[pointId];
            if (Math.abs(p.getX() - mPoint.getX()) > delta + EPS) {
                break;
            }
            candidates[pointId] = true;
            counter++;
        }
        for (int i = med + 1; i < points.length; i++) {
            int pointId = xOrder[i];
            Point p = points[pointId];
            if (Math.abs(p.getX() - mPoint.getX()) > delta + EPS) {
                break;
            }
            candidates[pointId] = true;
            counter++;
        }
        int[] sortedByYCandidateIds = new int[counter];
        int j = 0;
        for (int i = 0; i < yOrder.length; i++) {
            int pointId = yOrder[i];
            if (candidates[pointId]) {
                sortedByYCandidateIds[j++] = pointId;
            }
        }
        Set<Pair<Integer, Integer>> ret = new HashSet<Pair<Integer, Integer>>();
        double minDistance = delta;
        for (int i = 0; i < sortedByYCandidateIds.length; i++) {
            Point pi = points[sortedByYCandidateIds[i]];
            for (int k = i + 1; k < sortedByYCandidateIds.length; k++) {
                Point pk = points[sortedByYCandidateIds[k]];
                double distance = pi.distanceTo(pk);
                int compare = compare(distance, minDistance);
                if (compare < 0) {
                    minDistance = distance;
                    ret.clear();
                    ret.add(new Pair<Integer, Integer>(sortedByYCandidateIds[i], sortedByYCandidateIds[k]));
                } else if (compare == 0) {
                    ret.add(new Pair<Integer, Integer>(sortedByYCandidateIds[i], sortedByYCandidateIds[k]));
                } else if (pk.getY() - pi.getY() > delta + EPS) {
                    break; // Warranted to happen in less than 8 iterations this ensures O(n) work for this method
                }
            }
        }
        return ret;
    }

    private static Set<Pair<Integer, Integer>> getClosest(Point[] points, Set<Pair<Integer, Integer>> set1, Set<Pair<Integer, Integer>> set2) {
        if (set1 == null || set1.isEmpty()) {
            return set2;
        }
        if (set2 == null || set2.isEmpty()) {
            return set1;
        }
        int compare = compare(getDistance(points, set1), getDistance(points, set2));
        if (compare < 0) {
            return set1;
        } else if (compare > 0) {
            return set2;
        } else {
            set1.addAll(set2);
            return set1;
        }
    }

    private static double getDistance(Point[] points, Set<Pair<Integer, Integer>> set) {
        Pair<Integer, Integer> firstPair = set.iterator().next();
        Point p1 = points[firstPair.getElement1()];
        Point p2 = points[firstPair.getElement2()];
        return p1.distanceTo(p2);
    }

    private static int compare(double d1, double d2) {
        if (d1 + EPS < d2) {
            return -1;
        } else if (d2 + EPS < d1) {
            return 1;
        } else {
            return 0;
        }
    }

    private static Integer[] sortByX(final Point[] points) {
        Integer[] ret = new Integer[points.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = i;
        }
        Arrays.sort(ret, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return Double.compare(points[i1].getX(), points[i2].getX());
            }

        });
        return ret;
    }

    private static Integer[] sortByY(final Point[] points) {
        Integer[] ret = new Integer[points.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = i;
        }
        Arrays.sort(ret, new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return Double.compare(points[i1].getY(), points[i2].getY());
            }
        });
        return ret;
    }

    public static void main(String[] args) {
        Point[] points = new Point[]{
            new Point(0, 0),
            new Point(1, 1),
            new Point(0, 1),
            new Point(0.4, 0.5),
            new Point(0.4, 2.5),
            new Point(0.4, 3.5)
        };
        System.out.println(getClosestPairs(points));
    }

}
