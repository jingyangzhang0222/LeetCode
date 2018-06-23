/*
* Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node. Otherwise, the NOT null node will be used as the node of new tree.

Example 1:
Input:
	Tree 1                     Tree 2
          1                         2
         / \                       / \
        3   2                     1   3
       /                           \   \
      5                             4   7
Output:
Merged tree:
	     3
	    / \
	   4   5
	  / \   \
	 5   4   7

	 20180622
	 617
	 easy
	 deal with null pointer
	 O(max(m, n))
	 O(max(h1, h2))
* */
package leetcode.Tree;

public class MergeTwoBinaryTrees {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // base case
        if (t1 == null && t2 == null) {
            return null;
        }

        int val1 = t1 == null ? 0 : t1.val;
        int val2 = t2 == null ? 0 : t2.val;

        TreeNode newRoot = new TreeNode(val1 + val2);

        TreeNode t1Left = t1 == null ? null : t1.left;
        TreeNode t1Right = t1 == null ? null : t1.right;
        TreeNode t2Left = t2 == null ? null : t2.left;
        TreeNode t2Right = t2 == null ? null : t2.right;

        newRoot.left = mergeTrees(t1Left, t2Left);
        newRoot.right = mergeTrees(t1Right, t2Right);

        return newRoot;
    }
}
