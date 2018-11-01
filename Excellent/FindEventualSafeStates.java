/*
* In a directed graph, we start at some node and every turn, walk along a directed edge of the graph.  If we reach a node that is terminal (that is, it has no outgoing directed edges), we stop.

Now, say our starting node is eventually safe if and only if we must eventually walk to a terminal node.  More specifically, there exists a natural number K so that for any choice of where to walk, we must have stopped at a terminal node in less than K steps.

Which nodes are eventually safe?  Return them as an array in sorted order.

The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the length of graph.  The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph.

Example:
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Here is a diagram of the above graph.

Illustration of graph

Note:

graph will have length at most 10000.
The number of edges in the graph will not exceed 32000.
Each graph[i] will be a sorted list of different integers, chosen within the range [0, graph.length - 1].

    20181030
    802
    medium
    O(V + E)
    O(E)
* */
package leetcode.Excellent;

import java.util.ArrayList;
import java.util.List;

public class FindEventualSafeStates {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int[] color = new int[graph.length];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == 2 || color[i] == 0 && dfs(i, color, graph)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean dfs(int i, int[] color, int[][] graph) {
        // 0: not visited
        // 1: cyclic
        // 2: acyclic
        boolean acyclic = true;
        color[i] = 1;
        for (int j : graph[i]) {
            if (color[j] == 1 || (color[j] == 0 && !dfs(j, color, graph))) {
                acyclic = false;
                break;
            }
        }

        if (acyclic) color[i] = 2;
        return acyclic;
    }
}
