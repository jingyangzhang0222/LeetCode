/*
* Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]

    20180619
    113
    medium
    O(n)
    O(1)
* */
package leetcode.Tree;

import java.util.ArrayList;
import java.util.List;

public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> sol = new ArrayList<>();
        helper(root, sol, new ArrayList<Integer>(), sum);
        return sol;
    }
    private void helper(TreeNode root, List<List<Integer>> sol, List<Integer> subsol, int sum) {
        //base case
        if (root == null) {
            return;
        }
        subsol.add(root.val);
        if (root.left == null && root.right == null && sum == root.val) {
            sol.add(new ArrayList(subsol));
            subsol.remove(subsol.size() - 1);
            return;
        }
        helper(root.left, sol, subsol, sum - root.val);
        helper(root.right, sol, subsol, sum - root.val);
        subsol.remove(subsol.size() - 1);
    }
}
