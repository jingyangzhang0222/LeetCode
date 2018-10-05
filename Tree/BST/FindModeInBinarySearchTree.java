/*
* Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.


For example:
Given BST [1,null,2,2],

   1
    \
     2
    /
   2


return [2].

Note: If a tree has more than one mode, you can return them in any order.

Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).

    20181003
    501
    easy
    O(n)
    O(1???)
* */
package leetcode.Tree.BST;

import leetcode.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class FindModeInBinarySearchTree {
    TreeNode prev;
    int cnt;
    int maxCnt;

    public int[] findMode(TreeNode root) {
        List<Integer> modes = new ArrayList<>();
        return toIntArray(modes.toArray(new Integer[0]));
    }

    private void inOrder(TreeNode root, List<Integer> modes) {
        if (root == null) {
            return;
        }
        inOrder(root.left, modes);
        if (prev == null || prev.val != root.val) {
            cnt = 1;
        } else {
            cnt++;
        }

        if (cnt >= maxCnt) {
            if (cnt > maxCnt) {
                modes.clear();
            }
            maxCnt = cnt;
            modes.add(root.val);
        }

        prev = root;
        inOrder(root.right, modes);
    }

    private int[] toIntArray(Integer[] array) {
        int[] res = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            res[i] = array[i];
        }
        return res;
    }
}
