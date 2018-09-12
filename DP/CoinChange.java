/*
* You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.

    20180909
    322
    medium
    O(# of Kind of coins * amount)
    O(amount)
* */
package leetcode.DP;

import java.util.Arrays;

public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {///////////////
            return 0;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        for (int coin : coins) {
            if (coin <= amount) {
                dp[coin] = 1;
            }
        }

        for (int val = 1; val <= amount; val++) {
            if (dp[val] == 1) {////////////////////
                continue;
            }
            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                int remaining = val - coin;
                if (remaining > 0 && dp[remaining] != -1) {
                    min = Math.min(min, dp[remaining] + 1);
                }
            }
            dp[val] = min == Integer.MAX_VALUE ? -1 : min;
        }

        return dp[amount];///////////////////////
    }
}
