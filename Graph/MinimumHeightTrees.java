/*
* For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1 :

Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3

Output: [1]
Example 2 :

Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5

Output: [3, 4]
Note:

According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”
The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.


    20190305
    310
    medium
    O(n)
    O(n)
* */
package leetcode.Graph;

import java.util.*;

public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // idea:
        // 1. find all leaves
        // 2. bfs from all leaves, delete current "leaves" from the candidates
        // 3. find all nodes with minimum distance
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            if (!graph.containsKey(edge[0])) {
                graph.put(edge[0], new HashSet<Integer>());
            }
            if (!graph.containsKey(edge[1])) {
                graph.put(edge[1], new HashSet<Integer>());
            }
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int node : graph.keySet()) {
            if (graph.get(node).size() == 1) {
                q.offer(node);
            }
        }
        while (n > 2) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int leaf = q.poll();
                n--;
                Set<Integer> neighbors = graph.get(leaf);
                for (int neighbor : neighbors) {
                    neighbors.remove(neighbor);
                    graph.get(neighbor).remove(leaf);
                    if (graph.get(neighbor).size() == 1) {
                        q.offer(neighbor);
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            res.add(q.poll());
        }
        return res;
    }
}
