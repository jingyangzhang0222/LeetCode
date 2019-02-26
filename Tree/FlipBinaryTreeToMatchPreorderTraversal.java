/*
* Given a binary tree with N nodes, each node has a different value from {1, ..., N}.

A node in this binary tree can be flipped by swapping the left child and the right child of that node.

Consider the sequence of N values reported by a preorder traversal starting from the root.  Call such a sequence of N values the voyage of the tree.

(Recall that a preorder traversal of a node means we report the current node's value, then preorder-traverse the left child, then preorder-traverse the right child.)

Our goal is to flip the least number of nodes in the tree so that the voyage of the tree matches the voyage we are given.

If we can do so, then return a list of the values of all nodes flipped.  You may return the answer in any order.

If we cannot do so, then return the list [-1].



Example 1:



Input: root = [1,2], voyage = [2,1]
Output: [-1]
Example 2:



Input: root = [1,2,3], voyage = [1,3,2]
Output: [1]
Example 3:



Input: root = [1,2,3], voyage = [1,2,3]
Output: []


Note:

1 <= N <= 100

    20190225
    971
    medium
    O(n)
    O(n)
* */
package leetcode.Tree;

import java.util.ArrayList;
import java.util.List;

public class FlipBinaryTreeToMatchPreorderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        List<Integer> res = new FlipBinaryTreeToMatchPreorderTraversal().flipMatchVoyage(root, new int[]{1, 3, 2});
        for (int num : res) {
            System.out.println(num);
        }
    }

    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        List<Integer> res = new ArrayList<>();
        if (preOrder(root, voyage, res, 0) == voyage.length) {
            return res;
        } else {
            res.clear();
            res.add(-1);
            return res;
        }
    }

    private int preOrder(TreeNode root, int[] voyage, List<Integer> res, int index) {
        // offset is 0: node is null, size is 0
        // offset == -1: no way to match
        // offset > 0: there is a way to match, critical node added to the list, index offset get√
        if (root == null) {
            return 0;
        }

        if (root.val != voyage[index]) {
            return -1;
        }

        // left, then right
        int leftRes1 = preOrder(root.left, voyage, res, index + 1);// offset get√
        if (leftRes1 >= 0) {
            int rightRes1 = preOrder(root.right, voyage, res, index + leftRes1 + 1);
            if (rightRes1 >= 0) {
                return leftRes1 + rightRes1 + 1;// index offset, return to prev call stack
            }
        }

        //right, then left
        int rightRes2 = preOrder(root.right, voyage, res, index + 1);
        if (rightRes2 >= 0) {
            int leftRes2 = preOrder(root.left, voyage, res, index + rightRes2 + 1);
            if (leftRes2 >= 0) {
                res.add(root.val);
                return rightRes2 + leftRes2 + 1;
            }
        }

        return -1;
    }
}
