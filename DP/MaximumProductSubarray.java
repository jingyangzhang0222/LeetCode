/*
* Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

    20180629
    152
    medium
    O(n)
    O(1)
* */
package leetcode.DP;

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int globalMax = nums[0];
        int curMax = nums[0];
        int curMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int prevMax = curMax;
            int prevMin = curMin;

            curMax = Math.max(nums[i], Math.max(prevMax * nums[i], prevMin * nums[i]));
            curMin = Math.min(nums[i], Math.min(prevMax * nums[i], prevMin * nums[i]));

            globalMax = Math.max(curMax, globalMax);
        }
        return globalMax;
    }
}
