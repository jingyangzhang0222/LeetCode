/*
* Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.

    20181011
    261
    medium
    O(n)
    O(n)
* */
package leetcode.UnionFind;

import java.util.*;

public class GraphValidTree {
    public boolean validTreeUnionFind(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }

        UnionFindSet ufs = new UnionFindSet(n, true);
        for (int[] edge : edges) {
            if (ufs.find(edge[0]) == ufs.find(edge[1])) {
                return false;
            }

            ufs.union(edge[0], edge[1]);
        }
        return true;
    }

    public boolean validTreeDFS(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            Set<Integer> visited = new HashSet<>();
            if (hasPath(edge[0], edge[1], map, visited)) {
                return false;
            }

            if (!map.containsKey(edge[0])) {
                map.put(edge[0], new ArrayList<Integer>());
            }
            if (!map.containsKey(edge[1])) {
                map.put(edge[1], new ArrayList<Integer>());
            }

            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        return true;
    }

    private boolean hasPath(int cur, int dest, Map<Integer, List<Integer>> map, Set<Integer> visited) {
        if (!map.containsKey(cur) || !map.containsKey(dest)) {
            return false;
        }
        if (cur == dest) {
            return true;
        }

        visited.add(cur);
        // contains cur && contains dest, cur != dest
        for (int nei : map.get(cur)) {
            if (!visited.contains(nei) && hasPath(nei, dest, map, visited)) {
                return true;
            }
        }

        return false;
    }
}
