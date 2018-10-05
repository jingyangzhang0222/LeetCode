package leetcode.BuildingBlock;

public class NumberOfConnectedComponentsInAnUndirectedGraph {
    public int countComponents(int n, int[][] edges) {
        boolean[][] isNeighbor = new boolean[n][n];
        for (int[] edge : edges) {
            isNeighbor[edge[0]][edge[1]] = true;
            isNeighbor[edge[1]][edge[0]] = true;
        }
        boolean[] visited = new boolean[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                cnt++;
                dfs(visited, isNeighbor, n, i);
            }
        }
        return cnt;
    }

    private void dfs(boolean[] visited, boolean[][] isNeighbor, int n, int i) {
        for (int j = 0; j < n; j++) {
            if (isNeighbor[i][j] && !visited[j]) {
                visited[j] = true;
                dfs(visited, isNeighbor, n, j);
            }
        }
    }
}
