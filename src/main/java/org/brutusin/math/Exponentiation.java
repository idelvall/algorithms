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
package org.brutusin.math;

/**
 * Exponentiation by squaring.
 * (https://en.wikipedia.org/wiki/Exponentiation_by_squaring)
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class Exponentiation {

    public static double pow(double x, int n) {
        if (n < 0) {
            return pow(1 / x, -n);
        } else if (n == 0) {
            return 1;
        } else {
            if (n % 2 == 0) {
                return pow(x * x, n / 2);
            } else {
                return x * pow(x * x, (n - 1) / 2);
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println(pow(3, 1));
        System.out.println(pow(3, 2));
        System.out.println(pow(3, 3));
        System.out.println(pow(3, 0));
        System.out.println(pow(3, -1));
    }
}
