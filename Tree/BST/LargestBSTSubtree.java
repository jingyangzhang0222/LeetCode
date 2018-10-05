/*
* Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.

Example:

Input: [10,5,15,1,8,null,7]

   10
   / \
  5  15
 / \   \
1   8   7

Output: 3
Explanation: The Largest BST Subtree in this case is the highlighted one.
             The return value is the subtree's size, which is 3.
Follow up:
Can you figure out ways to solve it with O(n) time complexity?

    20181001
    333
    medium
    O(n)
    O(h)
* */
package leetcode.Tree.BST;

import leetcode.Tree.TreeNode;

public class LargestBSTSubtree {
    int maxSize = 0;

    public int largestBSTSubtree(TreeNode root) {
        dfs(root);
        return maxSize;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0, 0};
        }

        // index 0: 0 indicates a null, < 0 not a BST, > 0 a valid BST
        // index 1: the min in subtree
        // index 2: the max in subtree
        int[] leftRes = dfs(root.left);
        int[] rightRes = dfs(root.right);

        if (leftRes[0] < 0 || rightRes[0] < 0) {
            return new int[]{-1, 0, 0};
        }

        // left and right subtree is BST
        boolean maxInLeftIsSmallerThanMe = leftRes[0] == 0 || leftRes[2] < root.val;
        boolean minInRightIsLargerThanMe = rightRes[0] == 0 || rightRes[1] > root.val;
        if (!(maxInLeftIsSmallerThanMe && minInRightIsLargerThanMe)) {
            return new int[]{-1, 0, 0};
        }

        // the whole tree, whose root is me, is valid BST!
        int size = leftRes[0] + rightRes[0] + 1;
        int min = leftRes[0] == 0 ? root.val : leftRes[1];
        int max = rightRes[0] == 0 ? root.val : rightRes[2];

        maxSize = Math.max(maxSize, size);

        return new int[]{size, min, max};
    }
}
