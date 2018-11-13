/*
* Given the root node of a binary search tree, return the sum of values of all nodes with value between L and R (inclusive).

The binary search tree is guaranteed to have unique values.



Example 1:

Input: root = [10,5,15,3,7,null,18], L = 7, R = 15
Output: 32
Example 2:

Input: root = [10,5,15,3,7,13,18,1,null,6], L = 6, R = 10
Output: 23

    20181111
    938
    medium
    O(n)
    O(h)
* */
package leetcode.Tree.BST;

import leetcode.Tree.TreeNode;

public class RangeSumOfBST {
    public int rangeSumBST(TreeNode root, int L, int R) {
        if (root == null) {
            return 0;
        }

        int sum = rangeSumBST(root.left, L, R) + rangeSumBST(root.right, L, R);
        return L <= root.val && root.val <= R ? sum + root.val : sum;
    }
}
