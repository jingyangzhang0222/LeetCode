/*
* Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.

Example:

Input: [1,2,3,4,5]

    1
   / \
  2   3
 / \
4   5

Output: return the root of the binary tree [4,5,2,#,#,3,1]

   4
  / \
 5   2
    / \
   3   1
Clarification:

Confused what [4,5,2,#,#,3,1] means? Read more below on how binary tree is serialized on OJ.

The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

Here's an example:

   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as [1,2,3,#,#,4,#,#,5].

    20181005
    156
    medium
    O(n)
    O(h)
* */
package leetcode.Tree;

public class BinaryTreeUpsideDown {
    TreeNode newRoot;
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        dfs(root, null, null);
        return newRoot;
    }

    private void dfs(TreeNode root, TreeNode prev, TreeNode prevRight) {
        if (root == null) {
            return;
        } else if (root.left == null && root.right == null) {
            newRoot = root;
        }

        dfs(root.left, root, root.right);
        root.left = prevRight;
        root.right = prev;
    }
}
