package leetcode.Tree.BST;

import leetcode.Tree.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class ClosestBinarySearchTreeValueII {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> res = new LinkedList<>();
        helper(root, target, k, res);
        return res;
    }

    private void helper(TreeNode root, double target, int k, LinkedList<Integer> res) {
        if (root == null) return;
        helper(root.left, target, k, res);
        if (res.size() == k) {
            if (Math.abs(target - root.val) < Math.abs(target - res.peek())) {
                res.remove();
            } else {
                return;
            }
        }
        res.add(root.val);
        helper(root.right, target, k, res);
    }
}
