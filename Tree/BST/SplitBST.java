/*
* Given a Binary Search Tree (BST) with root node root, and a target value V, split the tree into two subtrees where one subtree has nodes that are all smaller or equal to the target value, while the other subtree has all nodes that are greater than the target value.  It's not necessarily the case that the tree contains a node with value V.

Additionally, most of the structure of the original tree should remain.  Formally, for any child C with parent P in the original tree, if they are both in the same subtree after the split, then node C should still have the parent P.

You should output the root TreeNode of both subtrees after splitting, in any order.

Example 1:

Input: root = [4,2,6,1,3,5,7], V = 2
Output: [[2,1],[4,3,6,null,null,5,7]]
Explanation:
Note that root, output[0], and output[1] are TreeNode objects, not arrays.

The given tree [4,2,6,1,3,5,7] is represented by the following diagram:

          4
        /   \
      2      6
     / \    / \
    1   3  5   7

while the diagrams for the outputs are:

          4
        /   \
      3      6      and    2
            / \           /
           5   7         1
Note:

The size of the BST will not exceed 50.
The BST is always valid and each node's value is different.

    20181111
    776
    medium
    O(n)
    O(h)
* */
package leetcode.Tree.BST;

import leetcode.Tree.TreeNode;

public class SplitBST {
    public TreeNode[] splitBST(TreeNode root, int V) {
        if (root == null) {
            return new TreeNode[2];
        }
        // left[0]: split subtree that is smaller than V in original left subtree
        // left[1]: split subtree that is larger than V in original left subtree
        // right[0]: split subtree that is smaller than V in original right subtree
        // right[1]: split subtree that is larger than V in original right subtree

        TreeNode[] left = splitBST(root.left, V);
        TreeNode[] right = splitBST(root.right, V);

        if (root.val <= V) {
            // left[1] must be null
            root.left = left[0];
            root.right = right[0];
            return new TreeNode[]{root, right[1]};
        } else {
            // right[0] must be null
            root.left = left[1];
            root.right = right[1];
            return new TreeNode[]{left[0], root};
        }
    }
}
