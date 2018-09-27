/*
* Alice plays the following game, loosely based on the card game "21".

Alice starts with 0 points, and draws numbers while she has less than K points.  During each draw, she gains an integer number of points randomly from the range [1, W], where W is an integer.  Each draw is independent and the outcomes have equal probabilities.

Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?

Example 1:

Input: N = 10, K = 1, W = 10
Output: 1.00000
Explanation:  Alice gets a single card, then stops.
Example 2:

Input: N = 6, K = 1, W = 10
Output: 0.60000
Explanation:  Alice gets a single card, then stops.
In 6 out of W = 10 possibilities, she is at or below N = 6 points.
Example 3:

Input: N = 21, K = 17, W = 10
Output: 0.73278
Note:

0 <= K <= N <= 10000
1 <= W <= 10000
Answers will be accepted as correct if they are within 10^-5 of the correct answer.
The judging time limit has been reduced for this question.

    20180923
    837
    medium
    O()
    O(checkpoint + range)
* */
package leetcode.Google;

public class New21Game {
    public static void main (String[] args) {
        New21Game test = new New21Game();
        System.out.println(test.new21Game(21, 15, 10));
    }
    public double new21Game(int checkpoint, int threshold, int range) {
        //[0, 1, 2, ..., threshold, ..., checkpoint, ... checkpoint + range]
        //[?, ?, ?, ..., 1.0, ........., 1.0       , 0.0, 0.0, ..., 0.0]
        //               | <--------------- base cases --------------> |

        // dp[i]: the possibility of ( # of final points <= checkpoint) when we have i points at first

        double[] dp = new double[checkpoint + range + 1];
        for (int i = threshold; i <= checkpoint; i++) {
            dp[i] = 1.0;
        }

        for (int i = threshold - 1; i >= 0; i--) {
            if (i == threshold - 1) {
                double firstRes = 0.0;
                for (int j = 1; j <= range; j++) {
                    firstRes += dp[i + j];
                }
                dp[i] = firstRes / (double)range;
                continue;
            }
            dp[i] = dp[i + 1] + (1.0 / (double)range) * (dp[i + 1] - dp[i + 1 + range]);
        }

        return dp[0];
    }
}
