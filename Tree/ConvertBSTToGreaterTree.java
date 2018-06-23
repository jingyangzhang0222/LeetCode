/*
* Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13

    20180622
    538
    easy
    O(n)
    O(h)
* */
package leetcode.Tree;

public class ConvertBSTToGreaterTree {
    public TreeNode convertBST(TreeNode root) {
        reversedInorder(root, new int[1]);
        return root;
    }
    private void reversedInorder(TreeNode root, int[] sum) {
        // base case
        if (root == null) {
            return;
        }
        reversedInorder(root.right, sum);
        sum[0] += root.val;
        root.val = sum[0];
        reversedInorder(root.left, sum);
    }
}