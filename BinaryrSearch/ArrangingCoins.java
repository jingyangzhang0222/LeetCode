/*
* You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.

Given n, find the total number of full staircase rows that can be formed.

n is a non-negative integer and fits within the range of a 32-bit signed integer.

Example 1:

n = 5

The coins can form the following rows:
¤
¤ ¤
¤ ¤

Because the 3rd row is incomplete, we return 2.
Example 2:

n = 8

The coins can form the following rows:
¤
¤ ¤
¤ ¤ ¤
¤ ¤

Because the 4th row is incomplete, we return 3.

    20180927
    441
    easy
    O(lonM)
    O(1)
* */
package leetcode.BinaryrSearch;

public class ArrangingCoins {
    public int arrangeCoins(int n) {
        int lo = 0;
        int hi = 65535;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            long sum = (1 + (long)mid) * (long)mid / 2;
            long nextSum = (long)sum + (long)mid + 1;
            if (sum <= (long)n && nextSum > (long)n) {
                return mid;
            } else if (sum > (long)n) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }

        }
        return lo;
    }
}
