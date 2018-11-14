/*
* Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.

Example 1:
11000
11000
00011
00011
Given the above grid map, return 1.
Example 2:
11011
10000
00001
11011
Given the above grid map, return 3.

Notice that:
11
1
and
 1
11
are considered different island shapes, because we do not consider reflection / rotation.
Note: The length of each dimension in the given grid does not exceed 50.

    20181113
    694
    medium
    O(m * n)
    O(m * n)
* */
package leetcode.Hash;

import java.util.HashSet;
import java.util.Set;

public class NumberOfDistinctIslands {
    private static final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int numDistinctIslands(int[][] grid) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, 0, 0, sb);
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }

    private void dfs(int[][] grid, int x, int y, int reX, int reY, StringBuilder sb) {
        if (grid[x][y] == 0) {
            return;
        }

        sb.append(String.valueOf(reX) + ',' + String.valueOf(reY) + '|');
        grid[x][y] = 0;
        for (int[] dir : dirs) {
            int dx = x + dir[0];
            int dy = y + dir[1];
            if (dx < 0 || dy < 0 || dx >= grid.length || dy >= grid[0].length) {
                continue;
            }

            dfs(grid, dx, dy, reX + dir[0], reY + dir[1], sb);
        }
    }
}
