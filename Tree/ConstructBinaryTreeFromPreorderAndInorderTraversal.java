/*
* Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7

    20180629
    105
    medium
    O(n)
    O(1)
* */
package leetcode.Tree;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return reconstruct(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode reconstruct(int[] preorder, int preLeft, int preRight,
                                 int[] inorder, int inLeft, int inRight,
                                 Map<Integer, Integer> map) {
        // base case
        if (inLeft >= inRight) {
            return inLeft == inRight ? new TreeNode(inorder[inLeft]) : null;
        }

        TreeNode root = new TreeNode(preorder[preLeft]);

        int index = map.get(root.val);
        int leftSize = index - inLeft;

        root.left = reconstruct(preorder, preLeft + 1, preLeft + leftSize,
                inorder, inLeft, index - 1,
                map);

        root.right = reconstruct(preorder, preLeft + leftSize + 1, preRight,
                inorder, index + 1, inRight,
                map);

        return root;
    }
}
