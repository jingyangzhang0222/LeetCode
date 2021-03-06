/*
* A chess knight can move as indicated in the chess diagram below:

 .



This time, we place our chess knight on any numbered key of a phone pad (indicated above), and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.

Each time it lands on a key (including the initial placement of the knight), it presses the number of that key, pressing N digits total.

How many distinct numbers can you dial in this manner?

Since the answer may be large, output the answer modulo 10^9 + 7.



Example 1:

Input: 1
Output: 10
Example 2:

Input: 2
Output: 20
Example 3:

Input: 3
Output: 46


Note:

1 <= N <= 5000


    20181125
    935
    medium
    O(n)
    O(1)
* */
package leetcode.DP;

import java.util.Arrays;

public class KnightDialer {
    private static final int[][] neighbors = new int[][]{{4, 6},
            {6, 8}, {7, 9}, {4, 8},
            {0, 3, 9}, {}, {0, 1, 7},
            {2, 6}, {1, 3}, {2, 4}};

    public int knightDialer(int N) {
        if (N == 1) return 10;
        final int M = 1000000007;
        int[] dp = new int[10];
        Arrays.fill(dp, 1);
        dp[5] = 0;
        for (int i = 0; i < N - 1; i++) {
            int[] prevDp = dp;
            dp = new int[10];
            for (int j = 0; j < 10; j++) {
                for (int next : neighbors[j]) {
                    dp[next] = (dp[next] + prevDp[j]) % M;
                }
            }
        }

        int sum = 0;
        for (int subsum : dp) {
            sum = (sum + subsum) % M;
        }

        return sum;
    }
}
