/*
* Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?

    20181001
    300
    medium
    O(nlogn)
    O(1)
* */
package leetcode.BuildingBlock;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int right = 0;
        //[0, right): longest increasing subsequence
        for (int num : nums) {
            if (right == 0 || num > nums[right - 1]) {
                nums[right++] = num;
            } else {
                // right != 0 && num <= nums[right - 1]
                int index = findSmallestLargerOrEqualIndex(nums, right - 1, num);
                nums[index] = num;
            }
        }

        return right;
    }

    private int findSmallestLargerOrEqualIndex(int[] nums, int right, int num) {
        int left = 0;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == num) {
                return mid;
            } else if (nums[mid] < num) {
                left = mid + 1;
            } else {
                // nums[mid] > num
                right = mid;
            }
        }

        // left == right
        return left;
    }
}
