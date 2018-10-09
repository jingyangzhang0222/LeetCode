/*
* Given a Binary Search Tree and a target number, return true if there exist two elements in the BST such that their sum is equal to the given target.

Example 1:
Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 9

Output: True
Example 2:
Input:
    5
   / \
  3   6
 / \   \
2   4   7

Target = 28

Output: False

    20181008
    653
    easy
    O(n)
    O(n)
* */
package leetcode.Sum;

import leetcode.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TwoSumIVInputIsABST {public boolean findTarget(TreeNode root, int target) {
    List<Integer> list = new ArrayList<>();
    inOrder(root, list);
    Integer[] nums = list.toArray(new Integer[0]);
    int i = 0, j = nums.length - 1;
    while (i < j) {
        if (nums[i] + nums[j] == target) {
            return true;
        } else if (nums[i] + nums[j] < target) {
            i++;
        } else {
            j--;
        }
    }
    return false;
}

    private void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }
}
