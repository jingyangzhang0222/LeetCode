/*
* Given a binary tree, determine if it is a complete binary tree.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.



Example 1:



Input: [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
Example 2:



Input: [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.

Note:

The tree will have between 1 and 100 nodes.


    20190225
    958
    medium
    O(n)
    O(n)
* */
package leetcode.Tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class CheckCompletenessOfABinaryTree {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        boolean bubbleFound = false;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node.left == null) {
                    bubbleFound = true;
                } else {
                    if (bubbleFound) return false;
                    q.offer(node.left);
                }

                if (node.right == null) {
                    bubbleFound = true;
                } else {
                    if (bubbleFound) return false;
                    q.offer(node.right);
                }
            }
        }

        return true;
    }
}
