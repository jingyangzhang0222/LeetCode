/*
* Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]

    20180621
    39
    medium
    O((target / min) ^ nums.length)
    O(nums.length)
* */
package leetcode.NonGraphDFS;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> sol = new ArrayList<>();
        dfsHelper1(sol, new ArrayList<Integer>(), candidates, 0, target);
        return sol;
    }

    private void dfsHelper1(List<List<Integer>> sol, List<Integer> subsol, int[] nums, int index, int target) {
        // base case
        if (index == nums.length) {
            if (target == 0) {
                sol.add(new ArrayList(subsol));
            }
            return;
        }

        int count = 0;
        while (count * nums[index] <= target) {
            if (count > 0) subsol.add(nums[index]);
            dfsHelper1(sol, subsol, nums, index + 1, target - count * nums[index]);
            count++;
        }
        count--;
        while (count > 0) {
            subsol.remove(subsol.size() - 1);
            count--;
        }
    }

    private void dfsHelper2(List<List<Integer>> sol, List<Integer> subsol, int[] nums, int start, int target) {
        // base case
        if (target < 0) {
            return;
        }
        if (target == 0) {
            sol.add(new ArrayList(subsol));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            subsol.add(nums[i]);
            dfsHelper2(sol, subsol, nums, i, target - nums[i]);
            subsol.remove(subsol.size() - 1);
        }
    }
}
