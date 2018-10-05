/*
* There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
Follow up:
Could you solve it in O(nk) runtime?

    20180929
    265
    hard
    O(n*k)
    O(1)
* */
package leetcode.DP;

public class PaintHouseII {
    public static void main(String[] args) {
        PaintHouseII test = new PaintHouseII();
        int[][] costs = new int[][]{{4, 16},{15, 5},{18, 17},{10, 12},{14, 10},{3, 10},{2, 11},{18, 14},{9, 1},{14, 13}};
        System.out.println(test.minCostII(costs));
    }

    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }
        final int n = costs.length;
        final int k = costs[0].length;
        int m1 = 0, m2 = 0, mIndex = -1;

        for (int[] costRow : costs) {
            int pm1 = m1, pm2 = m2, pmIndex = mIndex;
            m1 = Integer.MAX_VALUE;
            m2 = Integer.MAX_VALUE;
            mIndex = -1;
            for (int i = 0; i < k; i++) {
                int cost = costRow[i];
                int res = 0;
                if (i != pmIndex) {
                    res = pm1 + cost;
                } else {
                    res = pm2 + cost;
                }

                if (res < m1) {
                    m2 = m1;
                    m1 = res;
                    mIndex = i;
                } else if (res < m2) {
                    m2 = res;
                }
            }
        }

        return m1;
    }
}
