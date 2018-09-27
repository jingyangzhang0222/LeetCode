/*
* Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]
Follow up: Recursive solution is trivial, could you do it iteratively?

    20180921
    144
    medium
    O(n)
    O(h)
* */
package leetcode.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> s = new ArrayDeque<>();
        s.offerLast(root);
        while (!s.isEmpty()) {
            TreeNode cur = s.pollLast();
            res.add(cur.val);
            if (cur.right != null) {
                s.offerLast(cur.right);
            }
            if (cur.left != null) {
                s.offerLast(cur.left);
            }
        }
        return res;
    }
}
