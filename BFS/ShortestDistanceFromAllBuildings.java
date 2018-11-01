/*
* You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
Example:

Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 7

Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
             the point (1,2) is an ideal empty land to build a house, as the total
             travel distance of 3+3+1=7 is minimal. So return 7.
Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.

    20181031
    317
    hard
    O(m^2 * n^2)
    O(mn)
* */
package leetcode.BFS;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestDistanceFromAllBuildings {
    private static final int[][] dirs = new int[][]{{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public int shortestDistance(int[][] grid) {
        int[][] count = new int[grid.length][grid[0].length];
        int houseCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    houseCount++;
                    bfs(grid, i, j, count);
                }
            }
        }

        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] < 0 && count[i][j] == houseCount) {
                    minDis = Math.min(minDis, -grid[i][j]);
                }
            }
        }
        return minDis == Integer.MAX_VALUE ? -1 : minDis;
    }

    private void bfs(int[][] grid, int i, int j, int[][] count) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int dis = 0;
        q.offer(new int[]{i, j});

        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                int[] point = q.poll();
                int x = point[0], y = point[1];
                grid[x][y] -= dis;
                count[x][y]++;
                for (int[] dir : dirs) {
                    int dx = x + dir[0];
                    int dy = y + dir[1];
                    if (dx < 0 || dx >= grid.length || dy < 0 || dy >= grid[0].length || visited[dx][dy] || grid[dx][dy] > 0) {
                        continue;
                    }

                    visited[dx][dy] = true;
                    q.offer(new int[]{dx, dy});
                }
            }
            dis++;
        }
    }
}
