/*
* Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note:
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1
Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3
Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
How would you optimize the kthSmallest routine?

    20180726
    230
    easy
    O(n)
    O(h)
* */
package leetcode.Tree.BST;

import leetcode.Tree.TreeNode;

public class KthSmallestElementInABST {
    int res;
    int cnt;
    public int kthSmallest(TreeNode root, int k) {
        cnt = k;
        inorder(root);
        return res;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }

        inorder(root.left);
        cnt--;
        if (cnt == 0) {
            res = root.val;
            return;
        }
        inorder(root.right);
    }
}
