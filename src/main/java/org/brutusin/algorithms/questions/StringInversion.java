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
package org.brutusin.algorithms.questions;

import java.io.FileOutputStream;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class StringInversion {

    private static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static String reverseString(String s) {
        char[] chars = new char[s.length()];
        boolean twoCharCodepoint = false;
        for (int i = 0; i < s.length(); i++) {
            chars[s.length() - 1 - i] = s.charAt(i);
            if (twoCharCodepoint) {
                swap(chars, s.length() - 1 - i, s.length() - i);
            }
            twoCharCodepoint = !Character.isBmpCodePoint(s.codePointAt(i));
        }
        return new String(chars);
    }

    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream("C:/temp/reverse-string.txt");
        StringBuilder sb = new StringBuilder("Linear B Syllable B008 A: ");
        sb.appendCodePoint(65536); //http://unicode-table.com/es/#10000
        sb.append(".");
        fos.write(sb.toString().getBytes("UTF-16"));
        fos.write("\n".getBytes("UTF-16"));
        fos.write(reverseString(sb.toString()).getBytes("UTF-16"));
    }
}
