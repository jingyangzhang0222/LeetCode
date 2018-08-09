/*
* Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its bottom-up level order traversal as:
[
  [15,7],
  [9,20],
  [3]
]

    20180806
    107
    easy
    O(n)
    O(n)
* */
package leetcode.Tree;

import java.util.*;

public class BinaryTreeLevelOrderTraversalII {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Deque<List> s = new ArrayDeque<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        if (root != null) q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> subsol = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                subsol.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            s.offerLast(new ArrayList(subsol));
        }
        List<List<Integer>> sol = new ArrayList<>();
        while (!s.isEmpty()) {
            sol.add(s.pollLast());
        }
        return sol;
    }
}
