/*
* Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Note:

0 < nums.length <= 50000.
0 < nums[i] < 1000.
0 <= k < 10^6.


    20190128
    713
    medium
    O(n)
    O(1)
* */
package leetcode.SlidingWindow;

public class SubarrayProductLessThanK {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0;
        int product = 1;
        int count = 0;
        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            while (left < right && product >= k) {
                product /= nums[left++];
            }

            if (product >= k) {
                continue;
            }
            count += right - left + 1;
        }

        return count;
    }
}
