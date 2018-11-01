package leetcode.TopologySort;

import java.util.*;

public class SequenceReconstruction {
    public static void main(String[] args) {
        List<List<Integer>> in = new ArrayList<>();
        List<Integer> in1 = new ArrayList<>();
        in1.add(1);
        in1.add(2);
        List<Integer> in2 = new ArrayList<>();
        in2.add(2);
        in2.add(3);
        in.add(in1);
        in.add(in2);
        List<Integer> in3 = new ArrayList<>();
        in3.add(10000);
        in.add(in3);
        System.out.println(new SequenceReconstruction().sequenceReconstruction(new int[]{1, 2, 3}, in));
    }

    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();
        int zeroInDegreeCount = 0;

        for (List<Integer> list : seqs) {
            Integer prevNum = null;
            for (int num : list) {
                if (!inDegree.containsKey(num)) {
                    inDegree.put(num, 0);
                    zeroInDegreeCount++;
                }
                if (prevNum != null) {
                    if (!graph.containsKey(prevNum)) {
                        graph.put(prevNum, new HashSet<Integer>());
                    }
                    if (!graph.get(prevNum).contains(num)) {
                        int prevInDegree = inDegree.get(num);
                        if (prevInDegree == 0) {
                            zeroInDegreeCount--;
                        }
                        inDegree.put(num, prevInDegree + 1);
                    }
                    graph.get(prevNum).add(num);
                }
                prevNum = num;
            }
        }
        if (org.length != inDegree.size()) {
            return false;
        }
        for (int i = 0; i < org.length; i++) {
            int num = org[i];
            if (zeroInDegreeCount != 1 || !inDegree.containsKey(num) || inDegree.get(num) != 0) {
                return false;
            }
            zeroInDegreeCount--;
            if (i + 1 < org.length) {
                int next = org[i + 1];
                boolean nextGeneratedByCurrent = false;
                for (int nextCandidate : graph.getOrDefault(num, new HashSet<>())) {
                    if (nextCandidate == next) {
                        nextGeneratedByCurrent = true;
                    }
                    int prevInDegree = inDegree.get(nextCandidate);
                    if (prevInDegree == 1) {
                        zeroInDegreeCount++;
                    }
                    inDegree.put(nextCandidate, prevInDegree - 1);
                }
                if (!nextGeneratedByCurrent) {
                    return false;
                }
            }
        }

        return true;
    }

    /*
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        if (org == null || org.length == 0 || seqs == null || seqs.size() == 0) {
            return false;
        }
        return checkTopo(getMap(seqs), org);
    }
    // 注意建图一定要确定每个点都能放到graph里面。
    // *********这个会有问题。因为如果每一个list只有一个数的话，就根本没办法被读入***********
    // private Map<Integer, List<Integer>> getMap(List<List<Integer>> seqs) {
    //     Map<Integer, List<Integer>> graph = new HashMap<>();
    //     for (List<Integer> list : seqs) {
    //         for (int i = 0; i < list.size() - 1; i++) {
    //             int cur = list.get(i);
    //             int nei = list.get(i+1);
    //             if (!graph.containsKey(cur)) graph.put(cur, new ArrayList<>());
    //             if (!graph.containsKey(nei)) graph.put(nei, new ArrayList<>());
    //             graph.get(cur).add(nei);
    //         }
    //     }
    //     return graph;
    // }
    // *****注意对比这里和上边的区别*********
    // 1. 所有点都会被读入(就算是每个list只有一个点)  2.重复的邻居不会被读入
    private Map<Integer, Set<Integer>> getMap(List<List<Integer>> seqs) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (List<Integer> list : seqs) {
            for (int i = 0; i < list.size(); i++) {
                int cur = list.get(i);
                if (!graph.containsKey(cur)) graph.put(cur, new HashSet<>());
                if (i+1 < list.size()) {
                    int nei = list.get(i+1);
                    if (!graph.containsKey(nei)) graph.put(nei, new HashSet<>());
                    graph.get(cur).add(nei);
                }
            }
        }
        return graph;
    }
    // solution1 general的方法来确定是否是topo sequence.
    // 比solution1.1 麻烦，corner case多
    // private boolean checkTopo(Map<Integer, Set<Integer>> graph, int[] org) {
    //     // <node id, indegree>
    //     Map<Integer, Integer> indegree = new HashMap<>();
    //     Queue<Integer> queue = new LinkedList<>();
    //     // get the indegree of all nodes
    //     for (Map.Entry<Integer, Set<Integer>>entry : graph.entrySet()) {
    //         int cur = entry.getKey();
    //         Set<Integer> neiList = entry.getValue();
    //         // ***some nodes has no indegree!!****
    //         if (!indegree.containsKey(cur)) indegree.put(cur, 0);
    //         for (Integer node : neiList) {
    //             indegree.put(node, indegree.getOrDefault(node, 0) + 1);
    //         }
    //     }
    //     // put the initial nodes(indegree=0) into queue.
    //     for (Map.Entry<Integer, Integer> entry: indegree.entrySet()) {
    //         if (entry.getValue() == 0) queue.offer(entry.getKey());
    //     }
    //     int i = 0;
    //     int topocnt = 0;
    //     while (!queue.isEmpty()) {
    //         // 1. make sure only one topo
    //         if (queue.size() > 1) return false;
    //         // make sure given list is valid
    //         // ***1. org 的元素比seqs少 2. org的元素比seq多 两种情况都要考虑****
    //         // make sure valid
    //         if (i >= org.length || indegree.getOrDefault(org[i++], 1) != 0) return false;
    //         int cur = queue.poll();
    //         topocnt++;
    //         for (int nei : graph.get(cur)) {
    //             if (indegree.get(nei) == 0) continue;
    //             indegree.put(nei, indegree.get(nei) - 1);
    //             if (indegree.get(nei) == 0) queue.offer(nei);
    //         }
    //     }
    //     // *****有可能有环！！*******
    //     // 3. make sure cycle and valid
    //     return topocnt == graph.size() & i == org.length;
    // }

    // solution 1.1 直接生成题目中的topo,然后和org对比。这种方法会上面方法corner case少
    // 只需要check 1 2 3 三个地方就行了
    private boolean checkTopo(Map<Integer, Set<Integer>> graph, int[] org) {
        Map<Integer, Integer> indegree = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        for (Map.Entry<Integer, Set<Integer>>entry : graph.entrySet()) {
            int cur = entry.getKey();
            Set<Integer> neiList = entry.getValue();
            if (!indegree.containsKey(cur)) indegree.put(cur, 0);
            for (Integer node : neiList) {
                indegree.put(node, indegree.getOrDefault(node, 0) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry: indegree.entrySet()) {
            if (entry.getValue() == 0) queue.offer(entry.getKey());
        }

        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            // 1. check unique
            if (queue.size() > 1) return false;
            int cur = queue.poll();
            result.add(cur);
            for (int nei : graph.get(cur)) {
                if (indegree.get(nei) == 0) continue;
                indegree.put(nei, indegree.get(nei) - 1);
                if (indegree.get(nei) == 0) queue.offer(nei);
            }
        }
        // 2. check cycle
        // 3. check totally same or not
        if (result.size() != graph.size() || result.size() != org.length) return false;
        for (int i = 0; i < org.length; i++) {
            if (result.get(i) != org[i]) return false;
        }
        return true;
    }
    */


    public boolean sequenceReconstruction1(int[] org, List<List<Integer>> seqs) {
        int len = org.length;
        int[] map = new int[len + 1];//map number to its index
        Arrays.fill(map, -1);
        int[] memo = new int[org.length];//count how many numbers are smaller(on the right)
        for (int i = 0; i < len; i++) {
            map[org[i]] = i;
        }
        for (List<Integer> seq : seqs) {
            if (seq.size() == 0) continue;
            int prev = seq.get(0);
            if (prev <= 0 || prev > len || map[prev] == -1) return false;
            for (int i = 1; i < seq.size(); i++) {
                int curr = seq.get(i);
                if (curr <= 0 || curr > len || map[curr] == -1) return false;
                memo[map[prev]] = Math.max(memo[map[prev]], len - map[curr] + 1);
                prev = curr;
            }
            memo[map[prev]] = Math.max(memo[map[prev]], 1);
        }
        for (int i = 0; i < memo.length; i++) {
            if (memo[i] != len - i) return false;
        }
        return true;
    }
}
