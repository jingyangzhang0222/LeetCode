/*
* Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its minimum depth = 2.

    20180723
    111
    easy
    O(n)
    O(h)
* */
package leetcode.Tree;

public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        int[] max = new int[]{Integer.MAX_VALUE};
        helper(root, 1, max);
        return max[0] == Integer.MAX_VALUE ? 0 : max[0];
    }
    private void helper(TreeNode root, int depth, int[] max) {
        if (root == null || depth >= max[0]) {
            return;
        }
        if (root.left == null && root.right == null) {
            max[0] = depth;
            return;
        }
        helper(root.left, depth + 1, max);
        helper(root.right, depth + 1, max);
    }
}
