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

import java.util.Arrays;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class Primes {

    public static boolean isPrime(long l) {
        if (l < 2) {
            return false;
        } else if (l == 2) {
            return true;
        } else if (l % 2 == 0) {
            return false;
        }
        long r = (long) Math.sqrt(l);
        for (int i = 3; i <= r; i = i + 2) {
            if (l % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean[] sieve(int n) {
        n = n + 1;
        boolean[] prime = new boolean[n];
        Arrays.fill(prime, 2, prime.length, true);
        long r = (long) Math.sqrt(n);
        for (int i = 2; i <= r; i++) {
            if (prime[i]) {
                for (int j = 2; j * i < n; j++) {
                    prime[i * j] = false;
                }
            }
        }
        return prime;
    }

    public static void main(String[] args) {
        System.out.println(isPrime(1));
        System.out.println(isPrime(3));
        System.out.println(isPrime(4));
        System.out.println(isPrime(7));
        System.out.println(isPrime(15));

        boolean[] sieve = sieve(10000);
        for (int i = 0; i < sieve.length; i++) {
            if (sieve[i] != isPrime(i)) {
                throw new AssertionError(i);
            }
        }
    }
}
