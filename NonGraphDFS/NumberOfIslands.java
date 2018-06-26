/*
* Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3

    20180626
    200
    medium
    O(m * n)
    O(m * n)
* */
package leetcode.NonGraphDFS;

public class NumberOfIslands {
    private static final int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfsHelper(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfsHelper(char[][] grid, int x, int y) {
        if (grid[x][y] == '0') {
            return;
        }

        grid[x][y] = '0';
        for (int[] dir : dirs) {
            int dx = x + dir[0];
            int dy = y + dir[1];
            if (dx < 0 || dx >= grid.length || dy < 0 || dy >= grid[0].length) {
                continue;
            }
            dfsHelper(grid, dx, dy);
        }
    }
}
