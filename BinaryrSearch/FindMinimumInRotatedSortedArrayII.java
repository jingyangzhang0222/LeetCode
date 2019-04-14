/*
* Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

The array may contain duplicates.

Example 1:

Input: [1,3,5]
Output: 1
Example 2:

Input: [2,2,2,0,1]
Output: 0
Note:

This is a follow up problem to Find Minimum in Rotated Sorted Array.
Would allow duplicates affect the run-time complexity? How and why?


    20190311
    154
    hard
    O(logn) worst: O(n)
    O(1)
* */
package leetcode.BinaryrSearch;

public class FindMinimumInRotatedSortedArrayII {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (left < mid && nums[left] == nums[mid]) {
                left++;
                continue;
            }
            if (nums[left] == nums[right] || nums[mid] == nums[right]) {
                right--;
                continue;
            }

            // left < right
            //
            // nums[left] != nums[right]
            // nums[mid] != nums[right]
            if (nums[left] <= nums[mid]) {
                if (nums[right] < nums[left]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            } else {
                // nums[left] > nums[mid]
                right = mid;
            }
        }

        return nums[left];
    }
}
