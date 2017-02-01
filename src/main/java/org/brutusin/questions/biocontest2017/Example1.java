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
package org.brutusin.questions.biocontest2017;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
class Example1 {

    public static void getOcurrences(String str, String subStr) {

        Matcher m = Pattern.compile(subStr).matcher(str);
        int start = 0;
        while (m.find(start)) {
            start = m.start();
            System.out.print(++start);
            System.out.print(" ");

        }
        if (start > 0) {
            System.out.println();
        }
    }

    public static void main(String[] args) {
       // getOcurrences("GATATATGCATATACTT", "ATAT");
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String subStr = in.nextLine();
        getOcurrences(str, subStr);
    }
}
