/*
* Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example:

Input: [1,2,3]
    1
   / \
  2   3
Output: 25
Explanation:
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.
Therefore, sum = 12 + 13 = 25.
Example 2:

Input: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.

    20810723
    129
    medium
    O(n)
    O(h)
* */
package leetcode.Tree;

public class SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        int[] sum = new int[1];
        helper(root, 0, sum);
        return sum[0];
    }
    private void helper(TreeNode root, int prevVal, int[] sum) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            sum[0] += 10 * prevVal + root.val;
            return;
        }

        helper(root.left, 10 * prevVal + root.val, sum);
        helper(root.right, 10 * prevVal + root.val, sum);
    }
}
