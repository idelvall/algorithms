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
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
Everybody knows that a huge number of different chemical reactions happen in cells. A reaction takes as an input a set of chemicals (substrates) and converts them to another set (products). Here we consider all reactions to be one-directional. A substrates for a reaction could be chemicals from the environment or products from other reactions. 

A scientist John Doe was given a cell and a list of chemicals that are present in the environment at the beginning. He already knows what reactions could happen in the cell. You should help him to understand which chemicals could appear in the cell.


Input Format


The first line contains initial chemicals separated by spaces. There is always at least one initial chemical. Each chemical is represented by a random positive integer that fits in 4-byte integer type. Each of the following lines describes a reaction, one per line. Each reaction is presented as two lists of integers separated by '->': the list of chemicals, separated by '+', that is needed to perform a reaction and the list of chemicals, separated by '+', that are produced as a result of the reaction. Each chemical could be present in each reaction maximum 2 times: one time at the left part and the other time at the right part (for example, a catalyst could appear in both parts).

Constraints for the easy version: a total number of chemicals through all reactions does not exceed 103103.
Constraints for the hard version: a total number of chemicals through all reactions does not exceed 105105.


Output Format


The sole line of the output should contain the unordered list of all chemicals that could appear in the cell at any moment of time.


Examples

Sample Input 1

4
4+6->1
2->3+5
4->6
6+4->5
Sample Output 1:

1 4 5 6
Sample Input 2


1 2
1+2->4
1+2->3
3->4+5
4->4
Sample Output 2


1 5 2 4 3

 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class Problem1 {

    private static class Reaction {

        private final ChemicalList substracts;
        private final Set<Reaction> productReactions = new HashSet<Reaction>();
        private final ChemicalListener listener;
        private int substractsAvailable = 0;

        public Reaction(ChemicalList substracts, ChemicalListener listener) {
            this.substracts = substracts;
            this.listener = listener;
        }

        public void addReaction(Reaction reaction) {
            productReactions.add(reaction);
        }

        public void addSubstract() {
            if (substractsAvailable < this.substracts.length()) {
                substractsAvailable++;
                if (substractsAvailable == this.substracts.length()) {
                    if (substractsAvailable == 1) {
                        listener.chemicalAvailable(this.substracts.getUniqueId());
                    }
                    trigger();
                }
            }
        }

        public boolean triggered() {
            return substractsAvailable == this.substracts.length();
        }

        private void trigger() {
            for (Reaction reaction : productReactions) {
                if (!reaction.triggered()) {
                    reaction.addSubstract();
                }
            }
        }

        public ChemicalList getSubstracts() {
            return substracts;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Reaction)) {
                return false;
            }
            Reaction other = (Reaction) obj;
            return this.substracts.equals(other.substracts);
        }

        @Override
        public int hashCode() {
            return this.substracts.hashCode();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(substracts.toString());
            sb.append("->");
            for (Reaction r : productReactions) {
                sb.append(r.getSubstracts());
                sb.append(" ");
            }
            return sb.toString();
        }
    }

    private static interface ChemicalListener {

        public void chemicalAvailable(int id);
    }

    private static class ChemicalList {

        private final int[] ids;

        public ChemicalList(int[] ids) {
            if (ids == null || ids.length == 0) {
                throw new IllegalArgumentException("Ids must contain at least one element");
            }
            this.ids = Arrays.copyOf(ids, ids.length);
            Arrays.sort(this.ids);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof ChemicalList)) {
                return false;
            }
            ChemicalList other = (ChemicalList) obj;
            return Arrays.equals(this.ids, other.ids);
        }

        @Override
        public int hashCode() {
            return 31 * ids.length * ids[0] * ids[ids.length - 1];
        }

        @Override
        public String toString() {
            return Arrays.toString(this.ids);
        }

        public int length() {
            return this.ids.length;
        }

        public int getUniqueId() {
            if (length() > 1) {
                throw new IllegalStateException("More than one element is present in substract set");
            }
            return this.ids[0];
        }
    }

    private static int[] parseChemicalList(String s) {
        String[] split = s.split("\\+");
        int[] ret = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            ret[i] = Integer.valueOf(split[i]);
        }
        return ret;
    }

    private static void generateLargeInput1() {
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

        Set<Reaction> initialReactions = new HashSet<Reaction>();
        Map<ChemicalList, Reaction> reactions = new HashMap<ChemicalList, Reaction>();

        final StringBuilder sb = new StringBuilder();

        final ChemicalListener listener = new ChemicalListener() {
            public void chemicalAvailable(int id) {
                sb.append(id + " ");
            }
        };

        String line = br.readLine();
        String[] tokens = line.split(" ");
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            ChemicalList s = new ChemicalList(new int[]{Integer.valueOf(token)});
            Reaction r = new Reaction(s, listener);
            initialReactions.add(r);
            reactions.put(s, r);
        }

        while ((line = br.readLine()) != null) {
            String[] split = line.split("->");
            int[] substractIds = parseChemicalList(split[0]);
            ChemicalList s = new ChemicalList(substractIds);
            Reaction reaction = reactions.get(s);
            if (reaction == null) {
                reaction = new Reaction(s, listener);
                reactions.put(s, reaction);
            }
            for (int i = 0; i < substractIds.length; i++) {
                int substractId = substractIds[i];
                ChemicalList substract = new ChemicalList(new int[]{substractId});
                Reaction r = reactions.get(substract);
                if (r == null) {
                    r = new Reaction(substract, listener);
                    reactions.put(substract, r);
                }
                r.addReaction(reaction);
            }
            int[] products = parseChemicalList(split[1]);
            for (int i = 0; i < products.length; i++) {
                int id = products[i];
                ChemicalList prodSubstracts = new ChemicalList(new int[]{id});
                Reaction productReaction = reactions.get(prodSubstracts);
                if (productReaction == null) {
                    productReaction = new Reaction(prodSubstracts, listener);
                    reactions.put(prodSubstracts, productReaction);
                }
                reaction.addReaction(productReaction);
            }
        }

        // BFS start
        for (Reaction initialReaction : initialReactions) {
            initialReaction.addSubstract();
        }
        System.out.println(sb.toString());
    }
}
