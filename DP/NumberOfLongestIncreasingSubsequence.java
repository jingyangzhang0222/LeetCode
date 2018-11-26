/*
* Given an unsorted array of integers, find the number of longest increasing subsequence.

Example 1:
Input: [1,3,5,4,7]
Output: 2
Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
Example 2:
Input: [2,2,2,2,2]
Output: 5
Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length is 1, so output 5.
Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed int.

    20181124
    673
    medium
    O(n ^ 2)
    O(n)
* */
package leetcode.DP;

public class NumberOfLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int[][] dp = new int[nums.length][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        int globalMaxLength = 1;
        int waysToGetMax = 1;

        for (int i = 1; i < nums.length; i++) {
            int curMaxLength = 1;
            int curWaysToGetMax = 1;

            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[j][0] + 1 > curMaxLength) {
                        curMaxLength = dp[j][0] + 1;
                        curWaysToGetMax = dp[j][1];
                    } else if (dp[j][0] + 1 == curMaxLength) {
                        curWaysToGetMax += dp[j][1];
                    }
                }
            }

            if (curMaxLength > globalMaxLength) {
                globalMaxLength = curMaxLength;
                waysToGetMax = curWaysToGetMax;
            } else if (curMaxLength == globalMaxLength) {
                waysToGetMax += curWaysToGetMax;
            }

            dp[i][0] = curMaxLength;
            dp[i][1] = curWaysToGetMax;
        }

        return waysToGetMax;
    }
}
