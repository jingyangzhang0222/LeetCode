/*
* Given the root node of a binary search tree (BST) and a value to be inserted into the tree, insert the value into the BST. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.

Note that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.

For example,

Given the tree:
        4
       / \
      2   7
     / \
    1   3
And the value to insert: 5
You can return this binary search tree:

         4
       /   \
      2     7
     / \   /
    1   3 5
This tree is also valid:

         5
       /   \
      2     7
     / \
    1   3
         \
          4

    20180725
    701
    medium
    O(h)
    O(1)
* */
package leetcode.BuildingBlock;

import leetcode.Tree.TreeNode;

public class InsertIntoABinarySearchTree {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode pre = null;
        TreeNode cur = root;
        while (cur != null) {
            pre = cur;
            if (cur.val < val) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        if (pre != null) {
            if (pre.val < val) {
                pre.right = new TreeNode(val);
            } else {
                pre.left = new TreeNode(val);
            }
        }
        return root == null ? new TreeNode(val) : root;
    }
}
