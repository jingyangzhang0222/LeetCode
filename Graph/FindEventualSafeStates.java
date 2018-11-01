package leetcode.Graph;

import java.util.ArrayList;
import java.util.List;

public class FindEventualSafeStates {
    public static void main(String[] args) {
        System.out.println(new FindEventualSafeStates().eventualSafeNodes(new int[][]{{1,2},{2,3},{5},{0},{5},{},{}}));
    }
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        boolean[][] edges = new boolean[graph.length][graph.length];

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                edges[i][graph[i][j]] = true;
            }
        }

        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i, visited, edges, res);
            }
        }

        return res;
    }

    private boolean dfs(int i, boolean[] visited, boolean[][] edges, List<Integer> res) {
        boolean self = true;
        boolean allNei = true;

        for (int j = 0; j < edges[i].length; j++) {
            if (edges[i][j]) {
                self = false;
                if (visited[j]) {
                    return false;
                } else {
                    visited[j] = true;
                    boolean thisNeighborContainsNoLoop = dfs(j, visited, edges, res);
                    if (!thisNeighborContainsNoLoop) {
                        return false;
                    }
                }
            }

        }


        if (self || allNei) {
            res.add(i);
        }

        return self || allNei;
    }
}
