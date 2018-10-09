/*
* Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

Example 1:

Input:

   1
    \
     3
    / \
   2   4
        \
         5

Output: 3

Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
Example 2:

Input:

   2
    \
     3
    /
   2
  /
 1

Output: 2

Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.

    20181008
    298
    medium
    O(n)
    O(h)
* */
package leetcode.Tree;

public class BinaryTreeLongestConsecutiveSequence {
    private int longest;
    public int longestConsecutive(TreeNode root) {
        longest = 0;
        dfs(root, null, 0);
        return longest;
    }

    private void dfs(TreeNode root, Integer prev, int prevLength) {
        if (root == null) {
            return;
        }

        int curLength = (prev != null && prev == root.val - 1) ? prevLength + 1 : 1;
        longest = Math.max(longest, curLength);
        dfs(root.left, root.val, curLength);
        dfs(root.right, root.val, curLength);
    }
}
