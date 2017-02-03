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
package org.brutusin.questions.topcoder.division1.level2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class FlowerGarden {

    public int[] getOrdering2(int[] height, int[] bloom, int[] wilt) {
        int[] optimal = new int[height.length];
        int[] optimalBloom = new int[bloom.length];
        int[] optimalWilt = new int[wilt.length];

        // init state
        optimal[0] = height[0];
        optimalBloom[0] = bloom[0];
        optimalWilt[0] = wilt[0];

        // run dynamic programming
        for (int i = 1; i < height.length; i++) {
            int currHeight = height[i];
            int currBloom = bloom[i];
            int currWilt = wilt[i];

            int offset = 0; // by default, type i is to be put to 1st row
            for (int j = 0; j < i; j++) {
                if (currWilt >= optimalBloom[j] && currWilt <= optimalWilt[j]
                        || currBloom >= optimalBloom[j] && currBloom <= optimalWilt[j]
                        || currWilt >= optimalWilt[j] && currBloom <= optimalBloom[j]) {  // life period overlap
                    if (currHeight < optimal[j]) {  // life overlap, and type i is shorter than type j
                        offset = j;
                        break;
                    } else {
                        offset = j + 1; // type i overlap with type j, and i is taller than j. Put i after j
                    }
                } else {  // not overlap with current
                    if (currHeight < optimal[j]) {
                        offset = j + 1; // type i not overlap with j, i is shorter than j, put i after j
                    }
                    // else keep offset as is considering offset is smaller than j
                }
            }

            // shift the types after offset
            for (int k = i - 1; k >= offset; k--) {
                optimal[k + 1] = optimal[k];
                optimalBloom[k + 1] = optimalBloom[k];
                optimalWilt[k + 1] = optimalWilt[k];
            }
            // update optimal
            optimal[offset] = currHeight;
            optimalBloom[offset] = currBloom;
            optimalWilt[offset] = currWilt;
        }
        return optimal;
    }

    private static boolean intersects(final int[] starts, final int[] ends, int i1, int i2) {
        return !(ends[i1] < starts[i2] || ends[i2] < starts[i1]);
    }
    
    public int[] getOrdering(final int[] height, final int[] starts, final int[] ends) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer i, Integer j) {
                return Integer.compare(height[i], height[j]);
            }
        }
        );
        for (int i = 0; i < height.length; i++) {
            minHeap.add(i);
        }
        LinkedList<Integer> list = new LinkedList<Integer>();
        while (minHeap.size() > 0) {
            Integer index = minHeap.poll();
            int p = 1;
            int pos = 0;
            for (Integer i : list) {
                if (intersects(starts, ends, i, index)) {
                    pos = p;
                }
                p++;
            }
            list.add(pos, index);
        }
        int[] ret = new int[height.length];
        int j = 0;
        for (Integer i : list) {
            ret[j++] = height[i];
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] height = new int[]{5, 3, 4};
        int[] start = new int[]{1, 3, 1};
        int[] end = new int[]{2, 4, 4};
        System.out.println(Arrays.toString(new FlowerGarden().getOrdering(height, start, end)));
        System.out.println(Arrays.toString(new FlowerGarden().getOrdering2(height, start, end)));
    }
}
