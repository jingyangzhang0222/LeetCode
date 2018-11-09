/*
* Given an undirected graph, return true if and only if it is bipartite.

Recall that a graph is bipartite if we can split it's set of nodes into two independent subsets A and B such that every edge in the graph has one node in A and another node in B.

The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.  Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges: graph[i] does not contain i, and it doesn't contain any element twice.

Example 1:
Input: [[1,3], [0,2], [1,3], [0,2]]
Output: true
Explanation:
The graph looks like this:
0----1
|    |
|    |
3----2
We can divide the vertices into two groups: {0, 2} and {1, 3}.
Example 2:
Input: [[1,2,3], [0,2], [0,1,3], [0,2]]
Output: false
Explanation:
The graph looks like this:
0----1
| \  |
|  \ |
3----2
We cannot find a way to divide the set of nodes into two independent subsets.


Note:

graph will have length in range [1, 100].
graph[i] will contain integers in range [0, graph.length - 1].
graph[i] will not contain i or duplicate values.
The graph is undirected: if any element j is in graph[i], then i will be in graph[j].


    20181106
    785
    medium
    O(V + E)
    O(E)
* */
package leetcode.Graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class IsGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        int[] dp = new int[graph.length];
        for (int node = 0; node < graph.length; node++) {
            //if (dp[node] == 0 && !bfs(graph, node, dp)) {
            //return false;
            //}
            if (dp[node] == 0) {
                dp[node] = 1;
                if (!dfs(graph, node, dp)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int node, int[] dp) {
        int color = dp[node];

        for (int nei : graph[node]) {
            if (dp[nei] == color) {
                return false;
            } else if (dp[nei] == 0) {
                dp[nei] = color == 1 ? 2 : 1;
                if (!dfs(graph, nei, dp)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean bfs(int[][] graph, int root, int[] dp) {
        Queue<Integer> q = new ArrayDeque<>();
        dp[root] = 1;
        q.offer(root);

        while (!q.isEmpty()) {
            int node = q.poll();
            for (int nei : graph[node]) {
                if ((dp[node] == 1 && dp[nei] == 1) || (dp[node] == 2 && dp[nei] == 2)) {
                    return false;
                }

                if (dp[nei] == 0) {
                    q.offer(nei);
                    dp[nei] = dp[node] == 1 ? 2 : 1;
                }
            }
        }
        return true;
    }
}
