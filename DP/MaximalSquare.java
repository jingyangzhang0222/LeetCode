/*
* Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4

    20180627
    221
    medium
    O(m * n)
    O(n)
* */
package leetcode.DP;

import java.util.Arrays;

public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int globalMax = 0;
        int[] dp = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int[] prevDp = Arrays.copyOf(dp, dp.length);
            for (int j = 0; j < matrix[0].length; j++) {
                if (i == 0 || j == 0) {
                    dp[j] = matrix[i][j] - '0';
                } else {
                    dp[j] = matrix[i][j] == '1' ? Math.min(Math.min(prevDp[j - 1], prevDp[j]), dp[j - 1]) + 1 : 0;
                }
                globalMax = Math.max(globalMax, dp[j]);
            }
        }
        return globalMax * globalMax;
    }
}
