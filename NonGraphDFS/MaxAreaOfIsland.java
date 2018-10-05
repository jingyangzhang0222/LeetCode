/*
* Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:
[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.

    20181004
    695
    easy
    O(mn)
    O(mn)
* */
package leetcode.NonGraphDFS;

public class MaxAreaOfIsland {
    private static final int[][] dirs = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    public int maxAreaOfIsland(int[][] grid) {
        int maxSize = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int size = dfs(grid, i ,j);
                    maxSize = Math.max(maxSize, size);
                }
            }
        }
        return maxSize;
    }

    private int dfs(int[][] grid, int x, int y) {
        if (grid[x][y] == 0) {
            return 0;
        }

        int cnt = 0;
        grid[x][y] = 0;
        for (int[] dir : dirs) {
            int dx = x + dir[0];
            int dy = y + dir[1];
            if (dx < 0 || dx >= grid.length || dy < 0 || dy >= grid[0].length) {
                continue;
            }
            cnt += dfs(grid, dx, dy);
        }
        return ++cnt;
    }
}
