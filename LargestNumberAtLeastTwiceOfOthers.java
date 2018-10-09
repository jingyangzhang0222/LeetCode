/*
* In a given integer array nums, there is always exactly one largest element.

Find whether the largest element in the array is at least twice as much as every other number in the array.

If it is, return the index of the largest element, otherwise return -1.

Example 1:

Input: nums = [3, 6, 1, 0]
Output: 1
Explanation: 6 is the largest integer, and for every other number in the array x,
6 is more than twice as big as x.  The index of value 6 is 1, so we return 1.


Example 2:

Input: nums = [1, 2, 3, 4]
Output: -1
Explanation: 4 isn't at least as big as twice the value of 3, so we return -1.


Note:

nums will have a length in the range [1, 50].
Every nums[i] will be an integer in the range [0, 99].


    20181005
    747
    easy
    O(n)
    O(1)
* */
package leetcode;

public class LargestNumberAtLeastTwiceOfOthers {
    public int dominantIndex(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        int largestIndex = -1;
        int secondLargestIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (largestIndex == -1 || nums[i] > nums[largestIndex]) {
                secondLargestIndex = largestIndex;
                largestIndex = i;
            } else if (secondLargestIndex == -1 || nums[i] > nums[secondLargestIndex]) {
                secondLargestIndex = i;
            }
        }

        return nums[largestIndex] >= 2 * nums[secondLargestIndex] ? largestIndex : -1;
    }
}
