/*
* Given a binary tree

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.
Example:

Given the following binary tree,

     1
   /  \
  2    3
 / \    \
4   5    7
After calling your function, the tree should look like:

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \    \
4-> 5 -> 7 -> NULL

    20180806
    117
    medium
    O(n)
    O(1)
* */
package leetcode.Tree;

public class PopulatingNextRightPointersInEachNodeII {
    public void connect(TreeLinkNode root) {
        TreeLinkNode cur = root;
        while (cur != null) {
            TreeLinkNode firstValidChild = null;
            TreeLinkNode prevChild = null;
            while (cur != null) {
                if (cur.left != null) {
                    if (prevChild != null) {
                        prevChild.next = cur.left;
                    }
                    if (firstValidChild == null) {
                        firstValidChild = cur.left;
                    }
                    prevChild = cur.left;
                }
                if (cur.right != null) {
                    if (prevChild != null) {
                        prevChild.next = cur.right;
                    }
                    if (firstValidChild == null) {
                        firstValidChild = cur.right;
                    }
                    prevChild = cur.right;
                }
                cur = cur.next;
            }
            cur = firstValidChild;
        }
    }

    private static class TreeLinkNode {
        public TreeLinkNode left, right, next;
        int val;

        TreeLinkNode(int x) {
            val = x;
        }
    }
}
