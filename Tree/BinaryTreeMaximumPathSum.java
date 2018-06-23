/*
* Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node
in the tree along the parent-child connections. The path must contain at least one node and does
not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42

    20180622
    124
    hard
    trilogy
    O(n)
    O(1)
* */
package leetcode.Tree;

public class BinaryTreeMaximumPathSum {
    public int maxPathSumNaive(TreeNode root) {
        int[] globalMax = new int[]{Integer.MIN_VALUE};
        helper(root, globalMax);
        return globalMax[0];
    }

    private int helper(TreeNode root, int[] globalMax) {
        //base case
        if (root == null) {
            return 0;
        }
        int leftMax = Math.max(0, helper(root.left, globalMax));
        int rightMax = Math.max(0, helper(root.right, globalMax));
        globalMax[0] = Math.max(globalMax[0], root.val + leftMax + rightMax);
        return root.val + Math.max(leftMax, rightMax);
    }

    private int helperNaive(TreeNode root, int[] globalMax) {
        //base case
        if (root == null) {
            return 0;
        }

        int leftMax = helperNaive(root.left, globalMax);
        int rightMax = helperNaive(root.right, globalMax);

        int self = root.val;
        int rootAndLeftOrLeft = root.left != null ? Math.max(leftMax, root.val + leftMax) : Integer.MIN_VALUE;
        int rootAndRightOrRight = root.right != null ? Math.max(rightMax, root.val + rightMax) : Integer.MIN_VALUE;
        int together = (root.left != null && root.right != null) ? root.val + leftMax + rightMax : Integer.MIN_VALUE;

        int curMax = Math.max(Math.max(self, together), Math.max(rootAndLeftOrLeft, rootAndRightOrRight));
        globalMax[0] = Math.max(globalMax[0], curMax);

        int maxChild = Math.max(0, Math.max(leftMax, rightMax));
        return root.val + maxChild;
    }
}
