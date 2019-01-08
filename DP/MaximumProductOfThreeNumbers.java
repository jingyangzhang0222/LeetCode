/*
* Given an integer array, find three numbers whose product is maximum and output the maximum product.

Example 1:
Input: [1,2,3]
Output: 6
Example 2:
Input: [1,2,3,4]
Output: 24
Note:
The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.

    20181229
    628
    easy
    O(n)
    O(1)
* */
package leetcode.DP;

public class MaximumProductOfThreeNumbers {
    public int maximumProduct(int[] nums) {
        int maxSingle = Math.max(nums[0], nums[1]);
        int minSingle = maxSingle == nums[0] ? nums[1] : nums[0];
        int maxDouble = nums[0] * nums[1];
        int minDouble = maxDouble;
        int maxTriple = Integer.MIN_VALUE;
        for (int i = 2; i < nums.length; i++) {
            maxTriple = Math.max(maxTriple, Math.max(maxDouble * nums[i], minDouble * nums[i]));

            maxDouble = Math.max(maxDouble, Math.max(maxSingle * nums[i], minSingle * nums[i]));
            minDouble = Math.min(minDouble, Math.min(maxSingle * nums[i], minSingle * nums[i]));
            maxSingle = Math.max(maxSingle, nums[i]);
            minSingle = Math.min(minSingle, nums[i]);
        }
        return maxTriple;
    }
}
