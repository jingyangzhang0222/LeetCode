/*
* Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

    20180910
    75
    medium
    O(n1)
    O(1)
* */
package leetcode.BuildingBlock;

public class RainbowSort {
    public void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        // [0, left): 0
        // [left, mid]: 1
        // (right, l - 1]: 2
        // 0 0 2 1 1 2
        //   l     r
        // m
        while (mid <= right) {
            if (nums[mid] == 0) {
                // why mid++ ?
                // mid is always > left
                // so nums[left] could no be 2
                // nums[left] must be 1
                // according to physical meaning...
                swap(nums, left++, mid++);
            } else if (nums[mid] == 2) {
                swap(nums, right--, mid);
            } else {
                mid++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
