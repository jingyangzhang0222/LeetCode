/*
* Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree
          1
         / \
        2   3
       / \
      4   5
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.

    20180622
    543
    easy
    trilogy
    O(n)
    O(h)
* */
package leetcode.Tree;

public class DiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] globalMax = new int[1];
        helper(root, globalMax);
        return globalMax[0];
    }

    private int helper(TreeNode root, int[] globalMax) {
        // base case
        if (root == null) {
            return 0;
        }

        int left = helper(root.left, globalMax);
        int right = helper(root.right, globalMax);
        globalMax[0] = Math.max(globalMax[0], left + right);
        return Math.max(left, right) + 1;
    }
}
