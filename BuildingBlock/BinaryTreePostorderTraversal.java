package leetcode.BuildingBlock;

import leetcode.Tree.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class BinaryTreePostorderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        //root.right.left = new TreeNode(1);
        System.out.println(new BinaryTreePostorderTraversal().postorderTraversal(root));
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        TreeNode prevPolled = null;
        Deque<TreeNode> s = new ArrayDeque<>();
        s.offer(root);

        while (!s.isEmpty()) {
            TreeNode cur = s.peekLast();
            TreeNode lastChild = cur.right != null ? cur.right : cur.left;

            if ((cur.left == null && cur.right == null) || prevPolled == lastChild) {
                s.pollLast();
                res.add(cur.val);
                prevPolled = cur;
            } else {
                if (cur.right != null) {
                    s.offerLast(cur.right);
                }
                if (cur.left != null) {
                    s.offerLast(cur.left);
                }
            }
        }

        return res;
    }
}
