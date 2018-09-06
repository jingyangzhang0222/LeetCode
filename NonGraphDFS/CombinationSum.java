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
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> sol = new ArrayList<>();
        dfsHelper(sol, new ArrayList<Integer>(), nums, target, 0);
        return sol;
    }

    private void dfsHelper(List<List<Integer>> sol, List<Integer> subsol, int[] nums, int target, int start) {
        //base case
        if (target <= 0) {
            if (target == 0) {
                sol.add(new ArrayList(subsol));
            }
            return;
        }

        for (int i = start; i < nums.length; i++) {
            subsol.add(nums[i]);
            dfsHelper(sol, subsol, nums, target - nums[i], i);
            subsol.remove(subsol.size() - 1);
        }
    }
}
