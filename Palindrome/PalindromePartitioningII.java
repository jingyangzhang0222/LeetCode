/*
* Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example:

Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.


    20181017
    132
    hard
    O(n ^ 3) -> O(n ^ 2) -> ~O(n)
    O(n ^ 2) -> O(n)
* */
package leetcode.Palindrome;

public class PalindromePartitioningII {
    public int minCut(String s) {
        int[][] dp = new int[s.length()][s.length()];
        // dp[0][j]: minCut for substring(0, j) (index inclusively)
        // dp[i][j] (i != 0):
        //      -1: substring(i, j) is not palindrome
        //       0: substring(i, j) is palindrome

        for (int right = 0; right < s.length(); right++) {
            int minCut = right;

            for (int left = right; left >= 0; left--) {
                int length = right - left + 1;
                dp[left][right] = -1;
                if (s.charAt(left) == s.charAt(right) && (length <= 2 || dp[left + 1][right - 1] == 0)) {
                    dp[left][right] = 0;
                    int minLeftCut = left == 0 ? -1 : dp[0][left - 1];
                    minCut = Math.min(minCut, minLeftCut + 1);
                }
            }

            dp[0][right] = minCut;
        }

        return dp[0][s.length() - 1];
    }
}
