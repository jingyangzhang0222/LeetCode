/*
* Given a 01 matrix M, find the longest line of consecutive one in the matrix. The line could be horizontal, vertical, diagonal or anti-diagonal.
Example:
Input:
[[0,1,1,0],
 [0,1,1,0],
 [0,0,0,1]]
Output: 3
Hint: The number of elements in the given matrix will not exceed 10,000.

    20181010
    562
    medium
    O(m * n)
    O(m*n) -> O(min(m,n))
* */
package leetcode.DP;

public class LongestLineOfConsecutiveOneInMatrix {
    public int longestLine(int[][] M) {
        if (M.length== 0 || M[0].length == 0) {
            return 0;
        }
        int[][][] dp = new int[M.length][M[0].length][4];
        int max = 0;
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (i - 1 < 0) {
                    dp[i][j][0] = M[i][j];
                } else {
                    dp[i][j][0] = M[i][j] == 1 ? dp[i - 1][j][0] + 1 : 0;
                }

                if (j - 1 < 0) {
                    dp[i][j][1] = M[i][j];
                } else {
                    dp[i][j][1] = M[i][j] == 1 ? dp[i][j - 1][1] + 1 : 0;
                }

                if (i - 1 < 0 || j - 1 < 0) {
                    dp[i][j][2] = M[i][j];
                } else {
                    dp[i][j][2] = M[i][j] == 1 ? dp[i - 1][j - 1][2] + 1 : 0;
                }

                if (i - 1 < 0 || j + 1 >= M[0].length) {
                    dp[i][j][3] = M[i][j];
                } else {
                    dp[i][j][3] = M[i][j] == 1 ? dp[i - 1][j + 1][3] + 1 : 0;
                }

                max = Math.max(max, Math.max(Math.max(dp[i][j][0], dp[i][j][1]), Math.max(dp[i][j][2], dp[i][j][3])));
            }
        }
        return max;
    }
}
