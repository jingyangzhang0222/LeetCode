package leetcode.Graph;

import java.util.ArrayList;
import java.util.List;

public class FindEventualSafeStates {
    public static void main(String[] args) {
        System.out.println(new FindEventualSafeStates().eventualSafeNodes(new int[][]{{1,2},{2,3},{5},{0},{5},{},{}}));
    }
    public List<Integer> eventualSafeNodes(int[][] graph) {
        // find nodes that are not in any cycle, nor can we go to a cycle through it
        // color:
        // 0: not visited
        // 1: visiting its neighbors, or can go to a cycle through it
        // 2: visited
        int n = graph.length;
        int[] color = new int[n];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (color[i] == 2 || (color[i] == 0 && dfs(i, graph, color))) {
                res.add(i);
            }
        }

        return res;
    }

    private boolean dfs(int node, int[][] graph, int[] color) {
        color[node] = 1;
        for (int neighbor : graph[node]) {
            if (color[neighbor] == 1 || (color[neighbor] == 0 && !dfs(neighbor, graph, color))) {
                return false;
            }
        }
        color[node] = 2;
        return true;
    }
}
