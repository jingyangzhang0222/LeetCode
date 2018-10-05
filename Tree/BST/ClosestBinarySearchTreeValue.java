/*
* Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
Example:

Input: root = [4,2,5,1,3], target = 3.714286

    4
   / \
  2   5
 / \
1   3

Output: 4

    20181001
    270
    easy
    O(logn)
    O(logn) -> O(1)
* */
package leetcode.Tree.BST;

import leetcode.Tree.TreeNode;

public class ClosestBinarySearchTreeValue {
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        double diff = Math.abs(root.val- (double)target);
        if (diff <= 0.5) {
            return root.val;
        }

        // root != null && |val - target| > 0.5
        if (target < (double)root.val) {
            int closestLeft = closestValue(root.left, target);
            if (closestLeft == Integer.MAX_VALUE) {
                return root.val;
            } else {
                return Math.abs((double)closestLeft - target) < diff ? closestLeft : root.val;
            }

        } else {
            int closestRight = closestValue(root.right, target);
            if (closestRight == Integer.MAX_VALUE) {
                return root.val;
            } else {
                return Math.abs((double)closestRight - target) < diff ? closestRight : root.val;
            }
        }
    }
}
