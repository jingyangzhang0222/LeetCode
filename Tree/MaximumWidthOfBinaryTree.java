/*
*Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:
Input:

           1
         /   \
        3     2
       / \     \
      5   3     9

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:
Input:

          1
         /
        3
       / \
      5   3

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:
Input:

          1
         / \
        3   2
       /
      5

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:
Input:

          1
         / \
        3   2
       /     \
      5       9
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


Note: Answer will in the range of 32-bit signed integer.

    20180723
    662
    medium
    O(n)
    O(n)
* */
package leetcode.Tree;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class MaximumWidthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(5);
        TreeNode n4 = new TreeNode(3);
        TreeNode n5 = new TreeNode(9);
        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.right = n5;

        System.out.println(new MaximumWidthOfBinaryTree().widthOfBinaryTree(root));
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        Map<TreeNode, Integer> indexMap = new HashMap<>();
        indexMap.put(root, 0);
        int max = 1;
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            int maxIndex = Integer.MIN_VALUE;
            int minIndex = Integer.MAX_VALUE;

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                int index = indexMap.get(node);
                minIndex = Math.min(minIndex, index);
                maxIndex = Math.max(maxIndex, index);

                if (node.left != null) {
                    q.offer(node.left);
                    indexMap.put(node.left, 2 * index);
                }
                if (node.right != null) {
                    q.offer(node.right);
                    indexMap.put(node.right, 2 * index + 1);
                }
            }
            max = Math.max(max, maxIndex - minIndex + 1);
        }
        return max;
    }
}
