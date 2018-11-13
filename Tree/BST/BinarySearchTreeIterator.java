/*
* Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

    20181112
    173
    medium
    hasNext(): O(1), next(): O(h)
    O(h)
* */
package leetcode.Tree.BST;

import leetcode.Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinarySearchTreeIterator {
    private Deque<TreeNode> s;

    public BinarySearchTreeIterator(TreeNode root) {
        s = new ArrayDeque<>();
        pushLeft(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !s.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = s.pollLast();
        pushLeft(node.right);
        return node.val;
    }

    private void pushLeft(TreeNode node) {
        while (node != null) {
            s.offerLast(node);
            node = node.left;
        }
    }
}
