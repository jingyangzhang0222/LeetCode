/*
* Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
Note:
1 <= k <= n <= 30,000.
Elements of the given array will be in the range [-10,000, 10,000].

    20181121
    643
    easy
    O(n)
    O(1)
* */
package leetcode.SlidingWindow;

public class MaximumAverageSubarrayI {
    public double findMaxAverage(int[] nums, int k) {
        double max = Integer.MIN_VALUE;
        int s = 0;
        long sum = 0;
        for (int f = 0; f < nums.length; f++) {
            sum += nums[f];
            if (f >= k - 1) {
                max = Math.max(max, sum / (double)k);
                sum -= nums[f - k + 1];
            }
        }
        return max;
    }
}
