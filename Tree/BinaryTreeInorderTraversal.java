package leetcode.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreeInorderTraversal {
    public List<Integer> inOrder(TreeNode root) {
        List<Integer> sol = new ArrayList<>();
        if (root == null) {
            return sol;
        }

        Deque<TreeNode> s = new ArrayDeque<>();
        pushLeft(root, s);
        while (!s.isEmpty()) {
            TreeNode cur = s.pollLast();
            sol.add(cur.val);
            cur = cur.right;
            pushLeft(cur, s);
        }
        return sol;
    }
    private void pushLeft(TreeNode root, Deque<TreeNode> s) {
        while (root != null) {
            s.offerLast(root);
            root = root.left;
        }
    }
}
