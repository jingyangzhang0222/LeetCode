/*
* Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

Example :

Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

Output: 4

    220181001
    250
    medium
    O(n)
    o(logn)
* */
package leetcode.Tree;

public class CountUnivalueSubtrees {
    public int countUnivalSubtrees(TreeNode root) {
        int[] cnt = new int[1];
        dfs(root, cnt);
        return cnt[0];
    }

    private boolean dfs(TreeNode root, int[] cnt) {
        if (root == null) {
            return true;
        }

        boolean leftValid = dfs(root.left, cnt);
        boolean rightValid = dfs(root.right, cnt);

        boolean valid = (root.left == null || (root.left.val == root.val && leftValid)) &&
                        (root.right == null || (root.right.val == root.val && rightValid));

        cnt[0] += valid ? 1 : 0;
        return valid;
    }
}
