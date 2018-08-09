/*
* Find the sum of all left leaves in a given binary tree.

Example:

    3
   / \
  9  20
    /  \
   15   7

There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.

    20180726
    404
    easy
    O(n)
    O(h)
* */
package leetcode.Tree;

public class SumOfLeftLeaves {
    private int sum;
    public int sumOfLeftLeaves(TreeNode root) {
        inorder(root, false);
        return sum;
    }

    private void inorder(TreeNode root, boolean isLeft) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            sum += isLeft ? root.val : 0;
            return;
        }

        inorder(root.left, true);
        inorder(root.right, false);
    }
}
