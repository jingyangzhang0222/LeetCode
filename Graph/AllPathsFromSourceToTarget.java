/*
*
Given a directed, acyclic graph of N nodes.  Find all possible paths from node 0 to node N-1, and return them in any order.

The graph is given as follows:  the nodes are 0, 1, ..., graph.length - 1.  graph[i] is a list of all nodes j for which the edge (i, j) exists.

Example:
Input: [[1,2], [3], [3], []]
Output: [[0,1,3],[0,2,3]]
Explanation: The graph looks like this:
0--->1
|    |
v    v
2--->3
There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Note:

The number of nodes in the graph will be in the range [2, 15].
You can print different paths in any order, but you should keep the order of nodes inside one path.

    20190323
    797
    medium
    O(n^2 * n!)
    O(n! * n)
* */
package leetcode.Graph;

import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSourceToTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> sol = new ArrayList<>();
        if (graph.length == 0) return sol;
        boolean[] visited = new boolean[graph.length];
        visited[0] = true;
        List<Integer> subsol = new ArrayList<>();
        subsol.add(0);
        dfs(sol, subsol, graph, visited, 0);
        return sol;
    }

    private void dfs(List<List<Integer>> sol, List<Integer> subsol, int[][] graph, boolean[] visited, int node) {
        if (node == graph.length - 1) {
            sol.add(new ArrayList<>(subsol));
            return;
        }

        for (int neighbor : graph[node]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                subsol.add(neighbor);
                dfs(sol, subsol, graph, visited, neighbor);
                subsol.remove(subsol.size() - 1);
                visited[neighbor] = false;
            }
        }
    }
}
