/*
* Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2]
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0

    20180917
    153
    medium
    O(logn)
    O(1)
* */
package leetcode.BinaryrSearch;

public class FindMinimumInRotatedSortedArray {
    public int findMin(int[] nums) {
        // 0 1 2 3 4 5 6 7 8 9
        // 7 8 9 0 1 2 3 4 5 6
        // l       m         r
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                // shifted minimum is to the right of mid point
                left = mid + 1;
            } else {
                // shifted minimum is to the left of the mid point or is mid itself
                right = mid;
            }
        }
        // left == right
        return nums[left];
    }
}
