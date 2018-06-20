/*
* Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.

    20180619
    64
    medium
    O(m * n)
    O(m * n)
* */
package leetcode.DP;

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        int[][] M = new int[grid.length][grid[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (i == 0) {
                    M[i][j] = j == 0 ? grid[i][j] : grid[i][j] + M[i][j - 1];
                    continue;
                }
                if (j == 0) {
                    M[i][j] = M[i - 1][j] + grid[i][j];
                } else {
                    M[i][j] = grid[i][j] + Math.min(M[i - 1][j], M[i][j - 1]);
                }
            }
        }
        return M[M.length - 1][M[0].length - 1];
    }
}