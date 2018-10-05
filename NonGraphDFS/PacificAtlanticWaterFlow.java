/*
* Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.
Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).

    20180927
    417
    medium
    O(m * n)
    O(m * n)
* */
package leetcode.NonGraphDFS;

import java.util.ArrayList;
import java.util.List;

public class PacificAtlanticWaterFlow {
    private static final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0 ,1}};
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        boolean[][] toPa = new boolean[matrix.length][matrix[0].length];
        boolean[][] toAt = new boolean[matrix.length][matrix[0].length];
        // false: unvisited
        // true:  vistied
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0 || j == 0) {
                    dfs(matrix, toPa, i, j);
                }
                if (i == matrix.length - 1 || j == matrix[0].length - 1) {
                    dfs(matrix, toAt, i, j);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (toPa[i][j] && toAt[i][j]) {
                    res.add(new int[]{i, j});
                }
            }
        }
        return res;
    }

    private void dfs(int[][] matrix, boolean[][] toOcean, int x, int y) {
        if (toOcean[x][y]) {
            return;
        }

        toOcean[x][y] = true;
        for (int[] dir : dirs) {
            int dx = x + dir[0];
            int dy = y + dir[1];

            if (dx < 0 || dx >= toOcean.length || dy < 0 || dy >= toOcean[0].length) {
                continue;
            }

            if (matrix[dx][dy] >= matrix[x][y]) {
                dfs(matrix, toOcean, dx, dy);
            }
        }
    }
}
