/*
* An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.

The ith edge connects nodes edges[i][0] and edges[i][1] together.

Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.

Example 1:

Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation:
Here is a diagram of the given tree:
  0
 / \
1   2
   /|\
  3 4 5
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.
Note: 1 <= N <= 10000

    20181008
    834
    hard
    O(V + E)
    O(E)
*
* */
package leetcode.Excellent;

import java.util.*;

public class SumOfDistancesInTree {
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            if (!map.containsKey(edge[0])) {
                map.put(edge[0], new ArrayList<Integer>());
            }
            if (!map.containsKey(edge[1])) {
                map.put(edge[1], new ArrayList<Integer>());
            }
            map.get(edge[1]).add(edge[0]);
            map.get(edge[0]).add(edge[1]);
        }

        int[] count = new int[N];
        Arrays.fill(count, 1);
        int[] res = new int[N];
        dfs(0, -1, map, count, res);
        dfs2(0, -1, map, count, res, N);
        return res;
    }

    private void dfs(int node, int parent, Map<Integer, List<Integer>> map, int[] count, int[] res) {
        for (int child : map.getOrDefault(node, new ArrayList<Integer>())) {
            if (child != parent) {
                dfs(child, node, map, count, res);
                count[node] += count[child];
                res[node] += res[child] + count[child];
            }
        }
    }

    private void dfs2(int node, int parent, Map<Integer, List<Integer>> map, int[] count, int[] res, int N) {
        for (int child : map.getOrDefault(node, new ArrayList<Integer>())) {
            if (child != parent) {
                int x_X = res[child];
                int y_Y = res[node] - res[child] - count[child];
                int numberOfY = N - count[child];
                res[child] = x_X + y_Y + numberOfY;
                dfs2(child, node, map, count, res, N);
            }
        }
    }
}
