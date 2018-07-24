/*
*
* Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]

    20180723
    103
    medium
    O(n)
    O(n)
* */
package leetcode.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> sol = new ArrayList<>();
        if (root == null) return sol;
        int dep = 1;
        Deque<TreeNode> deq = new ArrayDeque<>();
        deq.offerFirst(root);
        while (!deq.isEmpty()) {
            int size = deq.size();
            List<Integer> subsol = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = dep % 2 == 1 ? deq.pollFirst() : deq.pollLast();
                subsol.add(node.val);
                if (dep % 2 == 1) {
                    if (node.left != null) {
                        deq.offerLast(node.left);
                    }
                    if (node.right != null) {
                        deq.offerLast(node.right);
                    }
                } else {
                    if (node.right != null) {
                        deq.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        deq.offerFirst(node.left);
                    }
                }
            }
            sol.add(new ArrayList(subsol));
            dep++;
        }
        return sol;
    }
}
