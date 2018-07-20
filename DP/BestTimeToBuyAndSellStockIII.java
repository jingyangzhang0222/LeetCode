/*
* Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

    20180717
    123
    hard
    O(n)
    O(1)
* */
package leetcode.DP;

public class BestTimeToBuyAndSellStockIII {
    public int maxProfit1(int[] prices) {
        int firstBuyCost = Integer.MAX_VALUE;
        int secondBuyCost = Integer.MAX_VALUE;
        int firstSellPro = 0;
        int secondSellPro = 0;

        for (int price : prices) {
            // p4 - b2 = p4 - (p3 - p2 + p1)
            // b2 = p3 - p2 + p1 = p3 - s1
            // s1 = p2 - p1
            // b1 = p1
            secondSellPro = Math.max(secondSellPro, price - secondBuyCost);
            secondBuyCost = Math.min(secondBuyCost, price - firstSellPro);
            firstSellPro = Math.max(firstSellPro, price - firstBuyCost);
            firstBuyCost = Math.min(firstBuyCost, price);
        }

        return secondSellPro;
    }

    public int maxProfit2(int[] prices) {
        int loan1 = Integer.MIN_VALUE;
        int loan2 = Integer.MIN_VALUE;
        int sell1 = 0;
        int sell2 = 0;

        for (int price : prices) {
            sell2 = Math.max(sell2, price + loan2);
            loan2 = Math.max(loan2, sell1 - price);
            sell1 = Math.max(sell1, price + loan1);
            loan1 = Math.max(loan1, -price);
        }

        return sell2;
    }
}
