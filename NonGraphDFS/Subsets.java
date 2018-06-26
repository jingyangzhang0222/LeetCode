/*
* Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]


    20180626
    78
    medium
    O(2 ^ n)
    O(n)
* */
package leetcode.NonGraphDFS;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> sol = new ArrayList<>();
        dfsHelper(sol, new ArrayList<Integer>(), nums, 0);
        return sol;
    }
    private void dfsHelper(List<List<Integer>> sol, List<Integer> subsol, int[] nums, int index) {
        //base case
        if (index == nums.length) {
            sol.add(new ArrayList(subsol));
            return;
        }

        dfsHelper(sol, subsol, nums, index + 1);

        subsol.add(nums[index]);
        dfsHelper(sol, subsol, nums, index + 1);
        subsol.remove(subsol.size() - 1);
    }
}
