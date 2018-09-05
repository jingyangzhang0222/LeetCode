/*
* A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?



An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Note: m and n will be at most 100.

Example 1:

Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right

    201808010
    63
    medium
    O(m*n)
    O(m*n) -> O(min(m, n))
    7min
    bug: 1

    base: 1: Ob, 0
          2: top or left-most: depends on previous grid

    induction rule: dp[i][j] = dp[i - 1][j] + dp[i][j - 1]

    terminate: i == ..., j == ...
* */
package leetcode.DP;

public class UniquePathsII {
    public int uniquePathsWithObstacles(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                } else if (i == 0) {//top
                    dp[i][j] = j - 1 < 0 ? 1 : dp[i][j - 1];
                    continue;
                } else if (j == 0) {
                    dp[i][j] = i - 1 < 0 ? 1 : dp[i - 1][j];
                    continue;
                }

                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }
}
