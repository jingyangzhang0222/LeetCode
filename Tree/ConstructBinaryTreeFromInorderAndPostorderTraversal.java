/*
* Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

inorder = [9,3,15,20,7]
postorder = [9,15,7,20,3]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7

    20180724
    106
    medium
    O(n)
    O(n)
* */
package leetcode.Tree;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] in, int[] post) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < in.length; i++) {
            indexMap.put(in[i], i);
        }
        return helper(in, 0, in.length - 1,
                post, 0, post.length - 1,
                indexMap);
    }

    private TreeNode helper(int[] in, int inLeft, int inRight,
                            int[] post, int postLeft, int postRight,
                            Map<Integer, Integer> indexMap) {
        // base case
        if (inLeft > inRight) {
            return null;
        }

        TreeNode root = new TreeNode(post[postRight]);

        int rootIndex = indexMap.get(root.val);
        int leftSize = rootIndex - inLeft;

        root.left = helper(in, inLeft, rootIndex - 1,
                post, postLeft, postLeft + leftSize - 1,
                indexMap);

        root.right = helper(in, rootIndex + 1, inRight,
                post, postLeft + leftSize, postRight - 1,
                indexMap);

        return root;
    }
}
