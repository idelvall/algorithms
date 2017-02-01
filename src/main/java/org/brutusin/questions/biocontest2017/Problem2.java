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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
RNA consists of ribonucleotides of 4 types: adenine (A), cytosine (C), guanine (G), uracil (U). Therefore RNA can be represented as a string that consists of the characters A, C, G, U. It is known that the ribonucleotides A-U and C-G are complementary to each other.

RNA consists of one strand (chain of ribonucleotides) most of the time, and sometimes it can fold into the secondary structure. In this problem we consider a simpler secondary form than in real life. In this form, some pairs of complementary ribonucleotides build hydrogen bonds between them and each ribonucleotide can build at most one bond. Each bond can be represented as a pair of indices (i,j)(i,j) (i<j)(i<j) of the string S that represents RNA. These indices indicate the positions of the symbols in S corresponding to these ribonucleotides.

Also, in the secondary structure one additional condition holds for each pair of hydrogen bonds (i1,j1)(i1,j1), (i2,j2)(i2,j2), such that i1<i2i1<i2:

they do not intersect (i1<j1<i2<j2i1<j1<i2<j2);
or the second bond is inside the first one (i1<i2<j2<j1i1<i2<j2<j1).
The picture below shows an example of the correct secondary structure of RNA. Grey lines connect the pairs of subsequent nucleotides and red lines represent hydrogen bonds.



The string S with even length is perfect if the corresponding RNA could have a secondary structure in which each nucleotide is contained in a bond.
The string S with odd length is almost perfect if we could get a perfect string by removing exactly one symbol.

Your task is to check if the given string S is perfect, almost perfect or imperfect.

For example, the picture shows imperfect RNA.


Input Format


The only line of the input contains string S of symbols A, G, C, U. The length of SS is greater than 11.

Constraints for the easy version: the length of the string SS does not exceed 151151.
Constraints for the hard version: the length of the string SS does not exceed 3⋅105+13⋅105+1.


Output Format


If the string S is perfect, the sole line of the output should contain the string «perfect» (without quotes). If the string S is almost perfect, the sole line of the output should contain the string «almost perfect» (without quotes). Otherwise, the sole line should contain «imperfect» (without quotes).

Examples

Sample Input 1

UGCA
Sample Output 1

perfect
Sample Input 2

AGUCU
Sample Output 2

almost perfect
Sample Input 3

CAGUU
Sample Output 3

imperfect

 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class Problem2 {

    enum RNAType {

        perfect,
        almost("almost perfect"),
        imperfect;

        private final String str;

        private RNAType() {
            this.str = name();
        }

        private RNAType(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return this.str;
        }
    }

    public static boolean areComplementary(char c1, char c2) {
        if (c1 == 'A' && c2 == 'U') {
            return true;
        }
        if (c1 == 'U' && c2 == 'A') {
            return true;
        }
        if (c1 == 'C' && c2 == 'G') {
            return true;
        }
        if (c1 == 'G' && c2 == 'C') {
            return true;
        }
        return false;
    }

    private static RNAType getRNATypeType(String rnaString) {
        LinkedList<Character> linkedList = new LinkedList<Character>();

        linkedList.add(rnaString.charAt(0));
        for (int i = 1; i < rnaString.length(); i++) {
            char c = rnaString.charAt(i);
            if (linkedList.size() > 0 && areComplementary(c, linkedList.getLast())) {
                linkedList.removeLast();
            } else {
                linkedList.add(c);
            }
        }
        if (rnaString.length() % 2 == 0) {
            if (linkedList.size() == 0) {
                return RNAType.perfect;
            } else {
                return RNAType.imperfect;
            }
        } else {
            LinkedList<Character> linkedList2 = new LinkedList<Character>();
            int i = 0;
            for (Character c : linkedList) {
                if (i < linkedList.size() / 2) {
                    linkedList2.add(c);
                } else if (i > linkedList.size() / 2) {
                    if (linkedList2.size() > 0 && areComplementary(c, linkedList2.getLast())) {
                        linkedList2.removeLast();
                    } else {
                        linkedList2.add(c);
                    }
                }
                i++;
            }
            if (linkedList2.size() == 0) {
                return RNAType.almost;
            } else {
                return RNAType.imperfect;
            }
        }
    }

    public static void generateLargeInput1() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < 1e5 - 1; i++) {
            if (i > 1) {
                sb.append(" ");
            }
            sb.append(i);
        }
        sb.append("\n");
        for (int i = 1; i < 1e5; i++) {
            if (i > 1) {
                sb.append("+");
            }
            sb.append(i);
        }
        sb.append("->");
        sb.append(1e5);
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws Exception {
        //FileInputStream fis = new FileInputStream("C:\\Users\\DGPORTATIL01\\Documents\\GitHub\\algorithms\\src\\main\\java\\org\\brutusin\\questions\\biocontest\\input_large1.txt");
        //generateLargeInput1();
        // BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        // String s = "UGCA";
        System.out.println(getRNATypeType(s));

    }
}
