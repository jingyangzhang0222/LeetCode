/*
* Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.

Example 1:

Input: 2
Output: 1
Explanation: 2 = 1 + 1, 1 × 1 = 1.
Example 2:

Input: 10
Output: 36
Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
Note: You may assume that n is not less than 2 and not larger than 58.

    20180810
    343
    medium
    O(n^2)
    O(n)
    12min
    bug: 1
* */
package leetcode.DP;

public class IntegerBreak {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        //base case
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= i - 1; j++) {
                max = Math.max(max, j * (i - j));///////////////////////////////
                max = Math.max(max, dp[j] * (i - j));
            }
            dp[i] = max;
        }
        return dp[n];
    }
}
