/*
* Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.

Example:

Input:

   1
    \
     3
    /
   2

Output:
1

Explanation:
The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).

    20181111
    530
    easy
    O(n)
    O(logn)
* */
package leetcode.Tree.BST;

import leetcode.Tree.TreeNode;

public class MinimumAbsoluteDifferenceInBST {
    private Integer prev = null;
    private int globalMin = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        inOrder(root);
        return globalMin == Integer.MAX_VALUE ? 0 : globalMin;
    }

    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrder(root.left);
        if (prev != null) {
            globalMin = Math.min(globalMin, root.val - prev);
        }
        prev = root.val;
        inOrder(root.right);
    }
}
