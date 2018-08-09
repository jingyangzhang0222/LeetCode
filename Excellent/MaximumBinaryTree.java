/*
* Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:

The root is the maximum number in the array.
The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
Construct the maximum tree by the given array and output the root node of this tree.

Example 1:
Input: [3,2,1,6,0,5]
Output: return the tree root node representing the following tree:

      6
    /   \
   3     5
    \    /
     2  0
       \
        1
Note:
The size of the given array will be in the range [1,1000].

    20180727
    654
    medium
    O(n)
    O(n)
* */
package leetcode.Excellent;

import leetcode.Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        Deque<TreeNode> s = new ArrayDeque<>();
        for (int num : nums) {
            TreeNode node = new TreeNode(num);
            while (!s.isEmpty() && s.peekLast().val < num) {
                node.left = s.pollLast();
            }
            if (!s.isEmpty()) {
                s.peekLast().right = node;
            }
            s.offerLast(node);
        }
        return s.pollFirst();
    }
}
