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
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * The DNA strand consists of exons and introns. The exons are the coding parts
 * of the DNA, while the introns are not. During the process of transcription,
 * DNA sequence of a gene gets copied into an RNA molecule, where the introns
 * are removed during a process called RNA splicing.
 *
 * Recently, BioLabs Inc. developed an instrument to get some information on
 * what gets spliced out of a gene sequence. Using black magic a complicated
 * biochemical process this instrument produces a number of "reads": substrings
 * of a DNA sequence after the splicing. They hired you to write a program to
 * restore a possible DNA sequence after the splicing operation: that is a
 * sequence that contains as many reads as possible as substrings and that can
 * be obtained from original DNA sequence by a splicing procedure.
 *
 * In other words, there is a string S, the splicing results in a string T which
 * is a subsequence of S. Your task it to restore T given S and a number of T's
 * substrings.
 *
 * Notes
 *
 *
 * In tests almost all the DNA sequences, exons and introns are generated using
 * real-life data. The reads are emulated using known result of DNA splicing.
 * That means that for each test there exists an answer that satisfies all the
 * reads.
 *
 * Note, that the tests are approximately sorted by their sizes and not
 * specifically by difficulty. Probably, it will be easier to solve test 6
 * rather than 4, for example.
 *
 *
 * Input Format
 *
 *
 * The first line of the input contains an original DNA sequence represented as
 * a string of characters A, C, G, T. The second line contains one integer nn
 * --- the number of reads. Each of the next nn lines contains one read each.
 *
 *
 * Output Format
 *
 *
 * The first line of the output should contain a possible DNA sequence after the
 * splicing. Next nn lines should contain one integer each - the position of the
 * corresponding read in the proposed DNA sequence after the splicing. The
 * position are counted starting from one. If your output doesn't contain some
 * of the reads then you can put −1−1 in the corresponding line.
 *
 * @author Ignacio del Valle Alles idelvall@brutusin.org
 */
public class Problem3 {

    private static Map<String, TreeSet<Integer>> starts = new HashMap<String, TreeSet<Integer>>();

    private static class Node implements Comparable<Node> {

        private static int instanceCounter = 0;

        private String id;
        private final int instanceId = ++instanceCounter;
        private TreeSet<Integer> starts;
        private final Map<String, Edge> outEdges = new HashMap<String, Edge>();

        private int indegree;

        public Node(String id) {
            this.id = id;
        }

        public TreeSet<Integer> getStartSet() {
            return starts;
        }

        void setStarts(TreeSet<Integer> starts) {
            this.starts = starts;
        }

        void setId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        void addEdgeTo(Node n) {
            if (!outEdges.containsKey(n.getId())) {
                outEdges.put(n.getId(), new Edge(this, n));
                n.incrementInDegree();
            }
        }

        public int getInstanceId() {
            return instanceId;
        }

        public int compareTo(Node o) {
            if (starts == null) {
                return 1;
            }
            if (o.starts == null) {
                return -1;
            }
            return Integer.compare(starts.first(), o.starts.first());
        }

        @Override
        public String toString() {
            return instanceId + "(" + indegree + "," + outEdges.size() + ")" + (starts != null && starts.size() > 0 ? (": " + starts) : "");
        }

        void incrementInDegree() {
            indegree++;
        }

        public int getIndegree() {
            return indegree;
        }

        public int getOutdegree() {
            return outEdges.size();
        }

        public Map<String, Edge> getOutEdges() {
            return outEdges;
        }
    }

    private static class Edge {

        private static int instanceCounter = 0;
        private Node source;
        private Node target;
        private final int instanceId = ++instanceCounter;

        public Edge(Node source, Node target) {
            this.source = source;
            this.target = target;
        }

        public Node getSource() {
            return source;
        }

        public void setSource(Node source) {
            this.source = source;
        }

        public Node getTarget() {
            return target;
        }

        public void setTarget(Node target) {
            this.target = target;
        }

        @Override
        public String toString() {
            return source.getInstanceId() + "->" + target.getInstanceId();
        }

        public int getInstanceId() {
            return instanceId;
        }
    }

    private static List<Path> assemble(String ref, String[] cdna, int k) {

        for (int i = 0; i < ref.length() - k; i++) {
            String id = ref.substring(i, i + k);
            TreeSet<Integer> t = starts.get(id);
            if (t == null) {
                t = new TreeSet<Integer>();
                starts.put(id, t);
            }
            t.add(i);
        }
        Map<String, Node> nodes = new HashMap<String, Node>();
        for (String read : cdna) {
            Node prev = null;
            for (int i = 0; i <= read.length() - k; i++) {
                String id = read.substring(i, i + k);
                Node n = nodes.get(id);
                if (n == null) {
                    n = new Node(id);
                    nodes.put(id, n);
                    n.setStarts(starts.get(id));
                }
                if (prev != null) {
                    prev.addEdgeTo(n);
                }
                prev = n;
            }
        }
        Set<String> toVisit = new HashSet<String>(nodes.keySet());
        while (toVisit.size() > 0) {
            String id = toVisit.iterator().next();
            toVisit.remove(id);
            Node n = nodes.get(id);
            if (n.getOutdegree() == 1) {
                Edge edge = n.getOutEdges().values().iterator().next();
                if (edge.getTarget().getIndegree() == 1) {
                    // merge
                    Node n2 = edge.getTarget();
                    nodes.remove(n.getId());
                    nodes.remove(n2.getId());
                    toVisit.remove(n.getId());
                    toVisit.remove(n2.getId());
                    n.setId(n.getId() + n2.getId().substring(k - 1));
                    n.outEdges.clear();
                    n.outEdges.putAll(n2.getOutEdges());
                    if (n.getStartSet() == null) {
                        n.setStarts(n2.getStartSet());
                    }
                    nodes.put(n.getId(), n);
                    toVisit.add(n.getId());
                }
            }
        }

        TreeSet<Path> paths = new TreeSet<Path>();
        for (Node n : nodes.values()) {
            if (n.getIndegree() == 0) {
                traverse(cdna, k, n, null, new HashSet<Integer>(), paths);
            }
        }
        paths = getNonOverlappingPaths(paths, cdna);
        TreeMap<Integer, Path> orderedMap = new TreeMap<Integer, Path>();
        for (Path path : paths) {
            Integer position = getPosition(path.path, k);
            orderedMap.put(position, path);
        }
        return new ArrayList<Path>(orderedMap.values());
    }

//    private static Set<Path> reducePaths(Set<Path> paths) {
//        Set<Integer> remainingReads = new TreeSet<Integer>();
//        for (int i = 0; i < paths.iterator().next().mappedReads.length; i++) {
//            remainingReads.add(i);
//        }
//        return reducePaths(paths, remainingReads);
//    }
//    private static Set<Path> reducePaths(Set<Path> paths, Set<Integer> remainingReads) {
//
//        Set<Path> ret = new HashSet<Path>();
//
//        Map<Integer, Set<Path>> invertedCoverage = new TreeMap();
//        for (Path p : paths) {
//            for (int i = 0; i < p.mappedReads.length; i++) {
//                if (p.mappedReads[i] && remainingReads.contains(i)) {
//                    Set<Path> coveringPaths = invertedCoverage.get(i);
//                    if (coveringPaths == null) {
//                        coveringPaths = new HashSet<Path>();
//                        invertedCoverage.put(i, coveringPaths);
//                    }
//                    coveringPaths.add(p);
//                }
//            }
//        }
//
//        HashSet<Integer> solvedCases = new HashSet<Integer>();
//
//        for (Set<Path> coveringPaths : invertedCoverage.values()) {
//            if (coveringPaths.size() == 1) {
//                Path p = coveringPaths.iterator().next();
//                if (!ret.contains(p)) {
//                    ret.add(p);
//                    paths.remove(p);
//                    for (int j = 0; j < p.mappedReads.length; j++) {
//                        if (p.mappedReads[j]) {
//                            solvedCases.add(j);
//                            remainingReads.remove(j);
//                        }
//                    }
//                }
//            }
//        }
//        for (Integer i : solvedCases) {
//            invertedCoverage.remove(i);
//        }
//
//        Iterator<Path> iterator = paths.iterator();
//        Path perfectCandidate = null;
//        while (iterator.hasNext()) {
//            Path p = iterator.next();
//            int excessReads = 0;
//            int covering = 0;
//            for (int j = 0; j < p.mappedReads.length; j++) {
//                if (p.mappedReads[j]) {
//                    if (remainingReads.contains(j)) {
//                        covering++;
//                    } else {
//                        excessReads++;
//                    }
//                }
//            }
//            if (covering == 0) {
//                iterator.remove();
//            }
//            if (excessReads == 0) {
//                if (perfectCandidate == null || perfectCandidate.path.length() < p.path.length()) {
//                    perfectCandidate = p;
//                }
//            }
//        }
//        if (perfectCandidate != null) {
//            ret.add(perfectCandidate);
//            paths.remove(perfectCandidate);
//            for (int j = 0; j < perfectCandidate.mappedReads.length; j++) {
//                if (perfectCandidate.mappedReads[j]) {
//                    remainingReads.remove(j);
//                }
//            }
//        }
//
//        if (remainingReads.size() > 0) {
//            return reducePaths(paths, remainingReads);
//        } else {
//            return ret;
//        }
//    }
    private static Path createPathFromReads(List<Integer> readIds, String[] reads) {
        int[] readStarts = new int[reads.length];
        int start = 1;
        StringBuilder path = new StringBuilder();
        for (int read : readIds) {
            path.append(reads[read]);
            readStarts[read] = start;
            start += reads[read].length();
        }
        return new Path(path.toString(), readStarts);
    }

    private static TreeSet<Path> getNonOverlappingPaths(TreeSet<Path> paths, String[] reads) {
        TreeSet<Path> ret = new TreeSet<Path>();
        Set<Integer> remainingReads = new TreeSet<Integer>();
        for (int i = 0; i < reads.length; i++) {
            remainingReads.add(i);
        }
        while (paths.size() > 0) {
            Path p = paths.pollFirst();
            // test not overlappimg
            List<Integer> fragment = null;
            for (int i = 0; i < reads.length; i++) {
                if (p.readStarts[i] > 0) {
                    if (remainingReads.contains(i)) {
                        if (fragment != null) {
                            fragment.add(i);
                        }
                    } else {
                        if (fragment != null && fragment.size() > 0) {
                            Path f = createPathFromReads(fragment, reads);
                            paths.add(f);
                        }
                        fragment = new ArrayList<Integer>();
                    }
                }
            }
            if (fragment == null) {
                ret.add(p);
                for (int j = 0; j < p.readStarts.length; j++) {
                    if (p.readStarts[j] > 0) {
                        remainingReads.remove(j);
                    }
                }
            } else if (fragment.size() > 0) {
                Path f = createPathFromReads(fragment, reads);
                paths.add(f);
            }
        }
        for (Integer readId : remainingReads) {
            int[] readStarts = new int[reads.length];
            readStarts[readId] = 1;
            ret.add(new Path(reads[readId], readStarts));
        }
        return ret;
    }

    public static class Path implements Comparable<Path> {

        private static int instanceCounter = 0;

        public final int instanceId = ++instanceCounter;
        public final int[] readStarts;
        public final String path;
        public final String toString;
        public final int coverage;

        public Path(String path, int[] readStarts) {
            this.path = path;
            this.readStarts = readStarts;
            TreeMap<Integer, Integer> t = new TreeMap<Integer, Integer>();
            int sum = 0;
            for (int i = 0; i < readStarts.length; i++) {
                if (readStarts[i] > 0) {
                    t.put(readStarts[i], i);
                    sum++;
                }
            }
            this.coverage = sum;
            this.toString = t.values().toString();
        }

        @Override
        public String toString() {
            return toString;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 47 * hash + this.instanceId;
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Path other = (Path) obj;
            if (this.instanceId != other.instanceId) {
                return false;
            }
            return true;
        }

        public int compareTo(Path o) {
            int ret = -1 * Integer.compare(coverage, o.coverage);
            if (ret == 0) {
                ret = Integer.compare(instanceId, o.instanceId);
            }
            return ret;
        }
    }

    private static void traverse(String[] reads, int k, Node n, String path, Set<Integer> visitedEdges, Set<Path> paths) {
        Collection<Edge> edges = n.getOutEdges().values();
        if (path == null) {
            path = n.getId();
        } else {
            path = path + n.getId().substring(k - 1);
        }
        boolean end = true;
        for (Edge edge : edges) {
            if (visitedEdges.contains(edge.getInstanceId())) {
                continue;
            }
            end = false;
            visitedEdges.add(edge.getInstanceId());
            Node next = edge.getTarget();
            traverse(reads, k, next, path, visitedEdges, paths);
            visitedEdges.remove(edge.getInstanceId());
        }
        // end node
        if (end) {
            paths.add(new Path(path, countCoveredReads(reads, path)));
        }
    }

    private static int[] countCoveredReads(String[] reads, String dnaAssembled) {
        int[] ret = new int[reads.length];
        for (int i = 0; i < reads.length; i++) {
            String read = reads[i];
            int index = dnaAssembled.indexOf(read);
            ret[i] = index + 1;
        }
        return ret;
    }

    private static class IntronRate {

        double rate;
        int finalPos;

        public IntronRate(double rate, int finalPos) {
            this.rate = rate;
            this.finalPos = finalPos;
        }
    }

    private static IntronRate getIntronRate(String ref, int start, Path p) {
        int j = start;
        for (int i = 0; i < p.path.length(); i++) {
            char base = p.path.charAt(i);
            while (ref.charAt(j) != base) {
                j++;
                if (j >= ref.length()) {
                    return null;
                }
            }
        }
        double rate = 1.0 * (j - start) / p.path.length();
        return new IntronRate(rate, j);
    }

//    private static void setOrder(List<Path> result, String ref, int start, Set<Path> paths) {
//        double min = Double.MAX_VALUE;
//        int nextStart = 0;
//        Path choosen = null;
//        for (Path p : paths) {
//            IntronRate ir = getIntronRate(ref, start, p);
//            if (ir.rate < min) {
//                min = ir.rate;
//                nextStart = ir.finalPos + 1;
//                choosen = p;
//            }
//        }
//        System.out.println("choosen " + choosen + " " + min);
//        paths.remove(choosen);
//        result.add(choosen);
//        if (paths.size() > 0) {
//            setOrder(result, ref, nextStart, paths);
//        }
//    }
    private static boolean validate(String ref, String[] dna, List<Path> paths) {
        int j = 0;
        StringBuilder assembly = new StringBuilder();
        for (Path p : paths) {
            assembly.append(p.path);
            int startingPos = j;
            for (int i = 0; i < p.path.length(); i++) {
                char base = p.path.charAt(i);
                while (ref.charAt(j) != base) {
                    j++;
                    if (j >= ref.length()) {
                        return false;
                    }
                }
            }
            System.out.println(p + ": " + 1.0 * (j - startingPos) / p.path.length());
        }
        int falseCounter = 0;
        int trueCounter = 0;
        for (String read : dna) {
            int index = assembly.indexOf(read);
            if (index == -1) {
                falseCounter++;
            } else {
                trueCounter++;
            }
        }
        System.out.println("false: " + falseCounter + ", true: " + trueCounter);
        return falseCounter == 0;
    }

    private static Integer getPosition(String path, int k) {
        Integer ret = null;
        int prev = -1;
        int j = 0;
        int offset = 0;
        for (int i = 0; i < path.length() - k; i++) {
            String id = path.substring(i, i + k);
            TreeSet<Integer> sts = starts.get(id);
            if (sts != null && sts.size() == 1) {
                if (sts.first() != prev + 1) {
                    offset = i;
                    prev = sts.first();
                    continue;
                }
                if (j == 0) {
                    ret = sts.first();
                    prev = ret;
                } else {
                    ret = (ret * j + sts.first()) / (j + 1);
                    prev = ret;
                }
                j++;
            }

        }
        return ret - offset;
    }

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("C:\\Users\\DGPORTATIL01\\Documents\\GitHub\\algorithms\\src\\main\\java\\org\\brutusin\\questions\\biocontest2017\\8");
        //generateLargeInput1();
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String ref = br.readLine();
        Integer n = Integer.valueOf(br.readLine());
        String[] cdna = new String[n];
        for (int i = 0; i < n; i++) {
            cdna[i] = br.readLine();
        }

//        String ref = "CAAGGAATCGAGGATAGGCTGTGTCCGTCCATGAGGCCTTTTTCGGTACGGTCTTGATTACTTTTTTC";
//        String[] cdna = new String[]{
//            "CTTTTT",
//            "AGGCTGG",
//            "TACTTTTTT",
//            "GGCTGGGCCTTTTCT",
//            "GCCTTTTCTTG"};
        List<Path> paths = assemble(ref, cdna, 100);
        System.out.println(validate(ref, cdna, paths));

    }
}
