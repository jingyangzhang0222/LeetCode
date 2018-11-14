/*
* Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Note:
The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.

    20181113
    583
    medium
    O(len1 * len2)
    O(len1 * len2) -> O(min(len1, len2))
* */
package leetcode.DP;

public class DeleteOperationForTwoStrings {
    public int minDistance(String word1, String word2) {
        // 1. find the longest common subsequence

        // 2. delete and dp
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int len1 = 0; len1 <= word1.length(); len1++) {
            for (int len2 = 0; len2 <= word2.length(); len2++) {
                if (len1 == 0 || len2 == 0) {
                    dp[len1][len2] = len1 + len2;
                    continue;
                }

                // s t h
                // s y t k
                int index1 = len1 - 1, index2 = len2 - 1;
                if (word1.charAt(index1) == word2.charAt(index2)) {
                    dp[len1][len2] = dp[len1 - 1][len2 - 1];
                } else {
                    int min = len1 + len2;
                    min = Math.min(Math.min(min, dp[len1 - 1][len2 - 1] + 2),
                            Math.min(dp[len1 - 1][len2] + 1, dp[len1][len2 - 1] + 1));
                    dp[len1][len2] = min;
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }
}
