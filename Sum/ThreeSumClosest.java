/*
* Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example:

Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

    20180618
    16
    medium
    two pointers
    O(n ^ 2)
    O(Arrays.sort)

    5 / 12 min
    typo: 0
* */
package leetcode.Sum;

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int globalMinDiff = Integer.MAX_VALUE;
        int res = 0;

        for (int first = 0; first < nums.length - 2; first++) {
            int second = first + 1;
            int third = nums.length - 1;
            while (second < third) {
                int sum = nums[first] + nums[second] + nums[third];
                if (Math.abs(sum - target) < globalMinDiff) {
                    res = sum;
                    globalMinDiff = Math.abs(sum - target);
                }
                if (sum < target) {
                    second++;
                } else {
                    third--;
                }
            }
        }
        return res;
    }
}
