/*
* We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

Example:

n = 10, I pick 8.

First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.

You end up paying $5 + $7 + $9 = $21.
Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.

    20180906
    375
    medium
    O(n ^ 3)
    O(n ^ 2)
* */
package leetcode.DP;

public class GuessNumberHigherOrLowerII {
    public static void main(String[] args) {
        GuessNumberHigherOrLowerII test = new GuessNumberHigherOrLowerII();
        test.getMoneyAmount(2);
    }
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int size = 2; size <= n; size++) {
            // size: 2: [1, 2], [2, 3], ...
            //       3: [1, 2, 3], [2, 3, 4], ...
            //       4: ...
            //       ...
            //       n: [1, 2, ..., n]: FINISHED!
            for (int start = 1; start + size - 1 <= n; start++) {
                //              ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
                //                   end <= n

                // now size is fixed!
                // start: 1: [1, 2, ...]
                //        2: [2, 3, ...]
                int end = start + size - 1;
                int min = Integer.MAX_VALUE;
                for (int mid = start; mid <= end; mid++) {
                    // [2, 3, 4, 5, 6]
                    //  ↑     ↑     ↑
                    //start  mid    end
                    int prevStart = start;
                    int nextStart = mid + 1;
                    int prevEnd = mid - 1;
                    int nextEnd = end;
                    int prevCost = prevEnd < prevStart ? 0 : dp[prevStart][prevEnd];
                    int nextCost = nextEnd < nextStart ? 0 : dp[nextStart][nextEnd];
                    // the first Math.min() is straight-forward
                    // what about Math.max() ?
                    // "guarantee a win" means you should always pay attention to the higher-cost half
                    // for example,  [1, 2, (3), 4, 5, 6]
                    // if we pick 3 as mid pointer, the worse case is that the number is among [4,5,6] instead of [1,2]
                    // so we don't need to deal with [1,2] any more, only care about [4,5,6]
                    // (and [4,5,6] has already been solved! Check dp[4][6] for the answer!)
                    // in the code, it is
                    // ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
                    // nextCost > prevCost
                    min = Math.min(min, mid + Math.max(prevCost, nextCost));
                }

                dp[start][end] = min;
            }
        }
        // final answer: start == 1, end == n
        return dp[1][n];
    }
}
