/*
*
* There is an m by n grid with a ball. Given the start coordinate (i,j) of the ball, you can move the ball to adjacent cell or cross the grid boundary in four directions (up, down, left, right). However, you can at most move N times. Find out the number of paths to move the ball out of grid boundary. The answer may be very large, return it after mod 109 + 7.

Example 1:
Input:m = 2, n = 2, N = 2, i = 0, j = 0
Output: 6
Explanation:

Example 2:
Input:m = 1, n = 3, N = 3, i = 0, j = 1
Output: 12
Explanation:

Note:
Once you move the ball out of boundary, you cannot move it back.
The length and height of the grid is in range [1,50].
N is in range [0,50].


    20180923
    576
    medium
    O(N * m * n)
    O(m * n)
* */
package leetcode.DP;

public class OutOfBoundaryPaths {
    private static final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int findPaths(int m, int n, int N, int row, int col) {
        int M = (int)(1e9 + 7);
        int[][] dp = new int[m][n];
        dp[row][col] = 1;
        int cnt = 0;
        for (int k = 1; k <= N; k++) {
            int[][] prevDp = dp;
            dp = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (prevDp[i][j] == 0) {
                        continue;
                    }
                    if (i == 0) {
                        cnt = (cnt + prevDp[i][j]) % M;
                    }
                    if (i == m - 1) {
                        cnt = (cnt + prevDp[i][j]) % M;
                    }
                    if (j == 0) {
                        cnt = (cnt + prevDp[i][j]) % M;
                    }
                    if (j == n - 1) {
                        cnt = (cnt + prevDp[i][j]) % M;
                    }

                    for (int[] dir : dirs) {
                        int dx = i + dir[0];
                        int dy = j + dir[1];
                        if (dx < 0 || dx >= m || dy < 0 || dy >= n) {
                            continue;
                        }

                        dp[dx][dy] = (dp[dx][dy] + prevDp[i][j]) % M;
                    }
                }
            }
        }
        return cnt % M;
    }
}
