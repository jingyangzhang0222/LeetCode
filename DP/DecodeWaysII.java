/*
* A message containing letters from A-Z is being encoded to numbers using the following mapping way:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.

Given the encoded message containing digits and the character '*', return the total number of ways to decode it.

Also, since the answer may be very large, you should return the output mod 109 + 7.

Example 1:
Input: "*"
Output: 9
Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
Example 2:
Input: "1*"
Output: 9 + 9 = 18
Note:
The length of the input string will fit in range [1, 105].
The input string will only contain the character '*' and digits '0' - '9'.

    20181113
    639
    hard
    O(n)
    O(1)
* */
package leetcode.DP;

public class DecodeWaysII {
    public static void main(String[] args) {
        //System.out.println(new DecodeWaysII().numDecodings("**"));
        System.out.println("".length());
    }

    public int numDecodings(String s) {
        int len = s.length();
        if (len == 0 || s.charAt(0) == '0') {
            return 0;
        }

        int[][] dp = new int[len][11];
        if (s.charAt(0) == '*') {
            for (int i = 1; i <= 9; i++) {
                dp[0][i] = 1;
            }
            dp[0][10] = 9;
        } else {
            dp[0][s.charAt(0) - '0'] = 1;
            dp[0][10] = 1;
        }


        for (int i = 1; i < len; i++) {
            char prev = s.charAt(i - 1);
            char curr = s.charAt(i);
            int ans2 = i >= 2 ? dp[i - 2][10] : 1;
            int ans1 = dp[i - 1][10];

            if (prev != '*' && curr != '*') {
                int num = curr - '0';
                int code = 10 * (prev - '0') + num;
                if (code % 10 == 0 && code != 20 && code != 20) {
                    return 0;
                } else if (code == 20 || code == 10) {
                    dp[i][num] = ans2;
                } else if (code < 10 || code > 26) {
                    dp[i][num] = ans1;
                } else {
                    dp[i][num] = ans1 + ans2;
                }

                dp[i][10] = dp[i][num];
            } else if (prev == '*' && curr != '*') {
                int num = curr - '0';
                for (int prevNum = 1; prevNum <= 9; prevNum++) {
                    int code = 10 * prevNum + num;
                    if (code == 10 || code == 20) {
                        dp[i][num] += ans2;
                    } else if ((0 < code && code < 10) || code > 26) {
                        dp[i][num] += dp[i - 1][prevNum];
                    } else {
                        dp[i][num] += ans2 + dp[i - 1][prevNum];
                    }
                }

                dp[i][10] = dp[i][num];
            } else if (prev != '*' && curr == '*') {
                int prevNum = prev - '0';

                for (int num = 1; num <= 9; num++) {
                    int code = 10 * (prev - '0') + num;
                    if (code == 10 || code == 20) {
                        dp[i][num] += ans2;
                    } else if ((0 < code && code < 10) || code > 26) {
                        dp[i][num] += dp[i - 1][prevNum];
                    } else {
                        dp[i][num] += ans2 + dp[i - 1][prevNum];
                    }

                    dp[i][10] += dp[i][num];
                }
            } else {
                // **
                for (int prevNum = 1; prevNum <= 9; prevNum++) {
                    for (int num = 1; num <= 9; num++) {
                        int code = 10 * prevNum + num;
                        if (code == 10 || code == 20) {
                            dp[i][num] += ans2;
                        } else if ((0 < code && code < 10) || code > 26) {
                            dp[i][num] += dp[i - 1][prevNum];
                        } else {
                            dp[i][num] += ans2 + dp[i - 1][prevNum];
                        }

                        dp[i][10] += dp[i][num];
                    }
                }
            }
        }

        return dp[len - 1][10];
    }
}
