/*
* Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

    20180618
    15
    medium
    O(n ^ 2)
    O(quickSort)

     The sorting algorithm is a Dual-Pivot Quicksort by Vladimir Yaroslavskiy,
     Jon Bentley, and Joshua Bloch. This algorithm offers O(n log(n)) performance
     on many data sets that cause other quicksorts to degrade to quadratic performance,
     and is typically faster than traditional (one-pivot) Quicksort implementations.
* */
package leetcode.Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> sol = new ArrayList<>();
        final int target = 0;
        Arrays.sort(nums);
        int first = 0;
        while (first < nums.length - 2) {
            if (first != 0 && nums[first] == nums[first - 1]) {
                first++;
                continue;
            }
            int second = first + 1;
            int third = nums.length - 1;

            while (second < third) {
                if (second != first + 1 && nums[second - 1] == nums[second]) {
                    second++;
                    continue;
                }
                if (third != nums.length - 1 && nums[third + 1] == nums[third]) {
                    third--;
                    continue;
                }
                int sum = nums[first] + nums[second] + nums[third];
                if (sum == target) {
                    sol.add(Arrays.asList(nums[first], nums[second], nums[third]));
                }

                if (sum < target) {
                    second++;
                } else {
                    third--;
                }
            }
            first++;
        }
        return sol;
    }
}
