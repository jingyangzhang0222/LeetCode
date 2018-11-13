/*
* Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].

    20181111
    581
    easy
    O(n)
    O(1)
* */
package leetcode.Excellent;

public class ShortestUnsortedContinuousSubarray {
    public int findUnsortedSubarray(int[] nums) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        int left = nums.length - 1, right = 0;
        for (int i= 0; i< nums.length; i++) {
            int j = nums.length - 1 - i;
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[j]);
            if (nums[i] < max) {
                right = i;
            }
            if (nums[j] > min) {
                left = j;
            }
        }
        return right <= left ? 0 : right - left + 1;
    }
}
