/*
* Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is the smaller value among its two sub-nodes.

Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:
Input:
    2
   / \
  2   5
     / \
    5   7

Output: 5
Explanation: The smallest value is 2, the second smallest value is 5.
Example 2:
Input:
    2
   / \
  2   2

Output: -1
Explanation: The smallest value is 2, but there isn't any second smallest value.

    20180723
    671
    easy
    O(n)
    O(h)
* */
package leetcode.Tree;

public class SecondMinimumNodeInABinaryTree {
    public int findSecondMinimumValue(TreeNode root) {
        if ((root == null) || (root.left == null && root.right == null)) {
            return -1;
        }
        int globalMin = root.val;
        int[] second = new int[]{globalMin};
        helper(root, globalMin, second);
        return second[0] == globalMin ? -1 : second[0];
    }

    private void helper(TreeNode root, int globalMin, int[] second) {
        if (root == null) {
            return;
        }

        // not null
        if (root.val > globalMin) {
            second[0] = second[0] == globalMin ? root.val : Math.min(second[0], root.val);
            return;
        } else {
            helper(root.left, globalMin, second);
            helper(root.right, globalMin, second);
        }
    }
}
