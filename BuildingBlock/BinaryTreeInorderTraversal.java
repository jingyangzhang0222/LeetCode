/*
* Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?

    20180610
    94
    medium
    O(n)
    O(n)
* */
package leetcode.BuildingBlock;

import leetcode.Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> sol = new ArrayList<>();
        Deque<TreeNode> s = new ArrayDeque<>();

        pushLeft(root, s);
        while (!s.isEmpty()) {
            TreeNode node = s.pollLast();
            sol.add(node.val);
            pushLeft(node.right, s);
        }

        return sol;
    }

    private void pushLeft(TreeNode root, Deque<TreeNode> s) {
        while (root != null) {
            s.offerLast(root);
            root = root.left;
        }
    }

    public List<Integer> inorderTraversalRecursion(TreeNode root) {
        List<Integer> sol = new ArrayList<>();
        inOrder(root, sol);
        return sol;
    }

    private void inOrder(TreeNode root, List<Integer> sol) {
        // base case
        if (root == null) {
            return;
        }

        inOrder(root.left, sol);
        sol.add(root.val);
        inOrder(root.right, sol);
    }
}
