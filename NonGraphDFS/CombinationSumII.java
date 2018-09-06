/*
* Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used once in the combination.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
Example 2:

Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]

    20180905
    40
    medium
    O(n!)
    O(n)
* */
package leetcode.NonGraphDFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> sol = new ArrayList<>();
        // only ascending order can appear in the array
        Arrays.sort(nums);
        dfsHelper(sol, new ArrayList<>(), nums, target, 0);
        return sol;
    }

    private void dfsHelper(List<List<Integer>> sol, List<Integer> subsol, int[] nums, int target, int start) {
        // base case
        if (target <= 0) {
            if (target == 0) {
                sol.add(new ArrayList(subsol));
            }
            return;
        }


        for (int i = start; i < nums.length; i++) {
            // at each level, an element can only be added once, this avoid the following cases:
            // [1a, 1b, 7] => [1a, 7], [1b, 7]
            // but still, [1, 1, 6] can be added, as they will be added at different levels
            if (i != start && nums[i] == nums[i - 1]) {
                continue;
            }
            subsol.add(nums[i]);
            // each element can be used only once, so at next level, start should be i + 1
            dfsHelper(sol, subsol, nums, target - nums[i], i + 1);
            subsol.remove(subsol.size() - 1);
        }
    }
}
