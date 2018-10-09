/*
* Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.

Example 1:
Input:
    5
   / \
  10 10
    /  \
   2   3

Output: True
Explanation:
    5
   /
  10

Sum: 15

   10
  /  \
 2    3

Sum: 15
Example 2:
Input:
    1
   / \
  2  10
    /  \
   2   20

Output: False
Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
Note:
The range of tree node value is in the range of [-100000, 100000].
1 <= n <= 10000

    20181006
    663
    medium
    O(n)
    O(n)
* */
package leetcode.Tree;

import java.util.HashSet;
import java.util.Set;

public class EqualTreePartition {
    Set<Long> set;
    private long sum = 0;
    private int nodeCnt;

    public boolean checkEqualTree(TreeNode root) {
        set = new HashSet<>();
        dfs(root);
        if (sum % 2 != 0) {
            return false;
        }

        return nodeCnt >= 2 && set.contains(sum / 2);
    }

    private long dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        nodeCnt++;

        long leftSum = dfs(root.left);
        long rightSum = dfs(root.right);

        sum += (long) root.val;
        if (root.left != null) {
            set.add(leftSum);
        }
        if (root.right != null) {
            set.add(rightSum);
        }

        return (long) root.val + leftSum + rightSum;
    }
}
