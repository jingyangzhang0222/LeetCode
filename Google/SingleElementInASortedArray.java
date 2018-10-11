/*
* Given a sorted array consisting of only integers where every element appears twice except for one element which appears once. Find this single element that appears only once.

Example 1:
Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:
Input: [3,3,7,7,10,11,11]
Output: 10
Note: Your solution should run in O(log n) time and O(1) space.

    20181010
    540
    medium
    O(logn)
    O(1)
* */
package leetcode.Google;

public class SingleElementInASortedArray {
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        // 1 1 2 2 3 3 4 4 5 5  6  6  7  8  8  9  9
        // 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
        // 0               m                      r
        // before single:
        // 1      1
        // even odd
        // after single:
        // 1      1
        // odd   even
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid % 2 == 1) {
                mid--;
            }
            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                right = mid;
            }
        }

        return nums[left];
    }
}
