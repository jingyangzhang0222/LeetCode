/*
* Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
Note:
You may assume that the array does not change.
There are many calls to sumRange function.

    20190324
    303
    easy
    O(1)
    O(n)
* */
package leetcode.Design;

public class RangeSumQueryImmutable {
    private final int[] nums;
    public RangeSumQueryImmutable(int[] nums) {
        this.nums = nums;
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return nums[j] - (i == 0 ? 0 : nums[i - 1]);
    }
}
