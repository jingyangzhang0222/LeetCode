/*
* A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).

    20180810
    91
    medium
    O(n)
    O(1)
    17min
    corner
*
* */
package leetcode.DP;

public class DecodeWays {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= s.length(); i++) {
            int index = i - 1;
            int thisNum = s.charAt(index) - '0';
            int prevNum = s.charAt(index - 1) - '0';
            int twoDig = prevNum * 10 + thisNum;

            if (thisNum == 0) {
                if (prevNum != 1 && prevNum != 2) {
                    return 0;
                }
                dp[i] = dp[i - 2];
            } else if (prevNum == 0 || twoDig > 26) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[s.length()];
    }
}
