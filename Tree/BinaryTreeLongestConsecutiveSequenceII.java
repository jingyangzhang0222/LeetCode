/*
* Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.

Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.

Example 1:
Input:
        1
       / \
      2   3
Output: 2
Explanation: The longest consecutive path is [1, 2] or [2, 1].
Example 2:
Input:
        2
       / \
      1   3
Output: 3
Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
Note: All the values of tree nodes are in the range of [-1e7, 1e7].

    20181008
    549
    medium
    O(n)
    O(h)
* */
package leetcode.Tree;

public class BinaryTreeLongestConsecutiveSequenceII {
    private int longest = 0;
    public int longestConsecutive(TreeNode root) {
        dfs(root);
        return longest;
    }

    private int[] dfs(TreeNode root) {
        if (root == null) {
            return new int[]{0, 0};
        }

        // res[0]: longest increasing sequence int the subtree
        //   0
        //  /
        // 1
        // res[1]: longest decresasing suquence int the subtree
        //  1
        //   \
        //    0

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int candidate1 = (root.left != null && root.left.val == root.val + 1) ? left[0] + 1 : 1;
        int candidate2 = (root.right != null && root.right.val == root.val + 1) ? right[0] + 1 : 1;
        int candidate3 = (root.left != null && root.left.val == root.val - 1) ? left[1] + 1 : 1;
        int candidate4 = (root.right != null && root.right.val == root.val - 1) ? right[1] + 1 : 1;

        int candidate5 = candidate1 + candidate4 - 1;
        int candidate6 = candidate2 + candidate3 - 1;

        int longestIncreasing = Math.max(candidate1, candidate2);
        int longestDecreasing = Math.max(candidate3, candidate4);
        int longestSnake = Math.max(candidate5, candidate6);

        longest = Math.max(longest, longestSnake);

        return new int[]{longestIncreasing, longestDecreasing};
    }
}
