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
        TreeNode prev = null;
        Deque<TreeNode> s = new ArrayDeque<>();
        s.offer(root);

        while (!s.isEmpty()) {
            TreeNode cur = s.peek();
            if (prev == null || cur == prev.left || cur == prev.right) {
                //going down form parent
                if (cur.left != null) {
                    //go down to left
                    s.offerFirst(cur.left);
                }else if (cur.right != null) {
                    //go down to right
                    s.offerFirst(cur.right);
                }else {
                    res.add(cur.val);
                    s.pollFirst();
                }
            }else if (prev == cur.left) {
                //go up from left
                if (cur.right != null) {
                    //go down
                    s.offerFirst(cur.right);
                }else {
                    res.add(cur.val);
                    s.pollFirst();
                }
            }else {
                //go up from right
                res.add(cur.val);
                s.pollFirst();
            }
            prev = cur;
        }

        return res;
    }
}
