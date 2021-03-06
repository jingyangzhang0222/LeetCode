/*
* Given an array w of positive integers, where w[i] describes the weight of index i, write a function pickIndex which randomly picks an index in proportion to its weight.

Note:

1 <= w.length <= 10000
1 <= w[i] <= 10^5
pickIndex will be called at most 10000 times.
Example 1:

Input:
["Solution","pickIndex"]
[[[1]],[]]
Output: [null,0]
Example 2:

Input:
["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
[[[1,3]],[],[],[],[],[]]
Output: [null,0,1,1,1,0]
Explanation of Input Syntax:

The input is two lists: the subroutines called and their arguments. Solution's constructor has one argument, the array w. pickIndex has no arguments. Arguments are always wrapped with a list, even if there aren't any.

    20181110
    528
    medium
    O(n) processing, O(logn) each call
    O(n)
* */
package leetcode.Design;

public class RandomPickWithWeight {
    private int[] prefixSum;
    private final int upperIndex;
    private final int upperRange;

    public RandomPickWithWeight(int[] w) {
        prefixSum = new int[w.length];
        upperIndex = w.length - 1;
        int start = 0;

        for (int i = 0; i < w.length; i++) {
            start += w[i];
            prefixSum[i] = start - 1;
        }
        upperRange = start;
    }

    public int pickIndex() {
        int range = (int)(upperRange * Math.random());
        int lo = 0, hi = upperIndex;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int start = mid == 0 ? 0 : prefixSum[mid - 1] + 1;
            int end = prefixSum[mid];
            if (start <= range && range <= end) {
                return mid;
            } else if (start > range) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        //lo == hi
        return hi;
    }
}
