/*
* Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .

Example:
Input: [4, 6, 7, 7]
Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
Note:
The length of the given array will not exceed 15.
The range of integer in the given array is [-100,100].
The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.

    20181002
    491
    medium
    O(2 ^ n)
    O(n)
* */
package leetcode.Excellent;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IncreasingSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> sol = new ArrayList<>();
        if (nums == null || nums.length <= 1) {
            return sol;
        }

        dfs(nums, sol, new ArrayList<Integer>(), 0);
        return sol;
    }

    private void dfs(int[] nums, List<List<Integer>> sol, List<Integer> subsol, int start) {
        if (subsol.size() >= 2) {
            sol.add(new ArrayList(subsol));
        }
        if (start >= nums.length) {
            return;
        }

        Set<Integer> set = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            if ((subsol.size() == 0 || subsol.get(subsol.size() - 1) <= nums[i]) &&
                    set.add(nums[i])) {
                subsol.add(nums[i]);
                dfs(nums, sol, subsol, i + 1);
                subsol.remove(subsol.size() - 1);
            }
        }
    }

    private void dfs2(int[] nums, List<List<Integer>> sol, List<Integer> subsol, int index) {
        if (index == nums.length) {
            if (subsol.size() >= 2) {
                sol.add(new ArrayList(subsol));
            }
            return;
        }

        if (subsol.size() == 0 || subsol.get(subsol.size() - 1) <= nums[index]) {
            subsol.add(nums[index]);
            dfs2(nums, sol, subsol, index + 1);
            subsol.remove(subsol.size() - 1);
        }
        if (!(subsol.size() >= 1 && subsol.get(subsol.size() - 1) == nums[index])) {
            dfs2(nums, sol, subsol, index + 1);
        }
    }
}
