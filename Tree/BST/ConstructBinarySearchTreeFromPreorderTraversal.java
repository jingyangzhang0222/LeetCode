/*
* Return the root node of a binary search tree that matches the given preorder traversal.

(Recall that a binary search tree is a binary tree where for every node, any descendant of node.left has a value < node.val, and any descendant of node.right has a value > node.val.  Also recall that a preorder traversal displays the value of the node first, then traverses node.left, then traverses node.right.)



Example 1:

Input: [8,5,1,7,10,12]
Output: [8,5,10,1,7,null,12]



Note:

1 <= preorder.length <= 100
The values of preorder are distinct.


    20190409
    1008
    medium
    recursion: O(nlogn), O(logn(height))
    stack: O(n), O(n)
* */
package leetcode.Tree.BST;

import leetcode.Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class ConstructBinarySearchTreeFromPreorderTraversal {
    public TreeNode bstFromPreorder(int[] preorder) {
        //O(nlogn)
        return build(preorder, 0, preorder.length - 1);
    }

    public TreeNode bstFromPreorder2(int[] preorder) {
        if (preorder.length == 0) {
            return null;
        }

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode root = null;
        for (int val : preorder) {
            if (!stack.isEmpty() && stack.peekLast().val > val) {
                stack.peekLast().left = new TreeNode(val);
                stack.offerLast(stack.peekLast().left);
            } else {
                TreeNode prevPolled = null;
                TreeNode newNode = new TreeNode(val);
                while (!stack.isEmpty() && stack.peekLast().val < val) {
                    prevPolled = stack.pollLast();
                }
                if (prevPolled != null) {
                    prevPolled.right = newNode;
                } else {
                    root = newNode;
                }
                stack.offerLast(newNode);
            }
        }
        return root;
    }

    private TreeNode build(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            return new TreeNode(preorder[left]);
        }

        TreeNode root = new TreeNode(preorder[left]);
        int i = ++left, j = right;
        // 3, 1
        //      i
        //    j
        // 3, 4
        //    i
        // j
        // find the largest element < root
        while (i <= j) {
            int mid = i + (j - i) / 2;
            if (preorder[mid] < root.val) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        // i > j
        root.left = build(preorder, left, j);
        root.right = build(preorder, i, right);
        return root;
    }
}
