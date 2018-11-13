/*
* Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).

Example 1:
Input: [1,3,5,4,7]
Output: 3
Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3.
Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4.
Example 2:
Input: [2,2,2,2,2]
Output: 1
Explanation: The longest continuous increasing subsequence is [2], its length is 1.
Note: Length of the array will not exceed 10,000.

    20181111
    674
    easy
    O(n)
    O(1)
* */
package leetcode.SlidingWindow;

public class LongestContinuousIncreasingSubsequence {
    public int findLengthOfLCIS(int[] nums) {
        int s = 0;
        int max = 0;
        for (int f = 1; f <= nums.length; f++) {
            if (f == nums.length || nums[f] <= nums[f - 1]) {
                max = Math.max(max, f - s);
                s = f;
            }
        }
        return max;
    }
}
