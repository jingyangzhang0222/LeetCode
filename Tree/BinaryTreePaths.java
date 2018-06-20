/*
* Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3

    20180619
    257
    easy
    O(n)
    O(n)
* */
package leetcode.Tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> sol = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(root, sol, sb);
        return sol;
    }
    private void helper(TreeNode root, List<String> sol, StringBuilder sb) {
        //base case
        if (root == null) {
            return;
        }

        int len = sb.length();
        if (root.left == null && root.right == null) {
            sb.append(root.val);
            sol.add(sb.toString());
            sb.setLength(len);
            return;
        }
        sb.append(root.val);
        sb.append("->");
        helper(root.left, sol, sb);
        helper(root.right, sol, sb);
        sb.setLength(len);
    }
}
