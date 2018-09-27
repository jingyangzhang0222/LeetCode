/*
*
In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.

For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.

Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

Note:
The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.
Example 1:
Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
Output: 4

Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
Example 2:
Input: Array = {"10", "0", "1"}, m = 1, n = 1
Output: 2

Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".

    20180922
    474
    medium
    O(max(# of characters, m * n * len))
    O(m*n)
* */
package leetcode.Excellent;

public class OnesAndZeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        // 1. brute force: find all possible combinations of strings int the array, validate one by one
        // 2. dfs: for each elements int the array, there are only 2 possibilities: take it, or not, O(2 ^ n), O(n)
        // 3. DP

        // dp[k][i][j]: maxmimum strings you can form using i 0s and j 1s until kth string
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < strs.length; i++) {
            int[] count = count(strs[i]);
            int[][] prevDp = dp;
            dp = new int[m + 1][n + 1];

            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    if (j - count[0] < 0 || k - count[1] < 0) {
                        dp[j][k] = prevDp[j][k];
                    } else {
                        dp[j][k] = Math.max(prevDp[j][k], prevDp[j - count[0]][k - count[1]] + 1);
                    }
                }
            }
        }

        return dp[m][n];
    }

    private int[] count(String s) {
        int zeroCnt = 0;
        int oneCnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                zeroCnt++;
            }
            if (c == '1') {
                oneCnt++;
            }
        }
        return new int[]{zeroCnt, oneCnt};
    }
}
