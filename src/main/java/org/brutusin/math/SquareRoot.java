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
package org.brutusin.math;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class SquareRoot {

    /**
     * Computes the square root using the bisection method
     * (https://en.wikipedia.org/wiki/Bisection_method)
     *
     * @param d
     * @param maxError
     * @return
     */
    public static double sqrtBisection(final double d, double maxError) {
        double a = 0;
        double b = d;
        if (b < 1) {
            b = 1;
        }
        int step = 1;
        while (true) {
            double sqrt = (a + b) / 2;
            double da = sqrt * sqrt;
            if (da < d - maxError) {
                a = sqrt;
            } else if (da > d + maxError) {
                b = sqrt;
            } else {
                return sqrt;
            }
            System.out.println(step++);
        }
    }

    /**
     * Usues a linear taylor aproximation to compute the next probe position
     *
     * @param d
     * @param maxError
     * @return
     */
    public static double sqrtVariableStep(final double d, double maxError) {
        double x = 1;
        int step = 1;
        while (true) {
            double f = x * x;
            if (f < d - maxError || f > d + maxError) {
                x = (d - f) / (2 * x) + x;
            } else {
                return x;
            }
            System.out.println(step++);
        }
    }

    public static void main(String[] args) {
        System.out.println(sqrtBisection(0.5, 0.001));
        System.out.println(sqrtVariableStep(0.5, 0.001));
    }
}
