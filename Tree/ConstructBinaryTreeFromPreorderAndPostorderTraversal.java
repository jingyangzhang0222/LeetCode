package leetcode.Tree;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        // pre:  self, left,  right
        // post: left, right, self
        // the last element in post-order's right sub tree must be the first in pre-order's right sub tree
        // the first element in pre-order's left sub tree must be the last in post-order's left sub tree
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < pre.length; i++) {
            map1.put(pre[i], i);
            map2.put(post[i], i);
        }
        return helper(pre, 0, pre.length - 1, post, 0, post.length - 1, map1, map2);
    }

    private TreeNode helper(int[] pre, int preLeft, int preRight,
                            int[] post, int postLeft, int postRight,
                            Map<Integer, Integer> map1, Map<Integer, Integer> map2) {
        if (preLeft > preRight) {
            return null;
        } else if (preLeft == preRight) {
            return new TreeNode(pre[preLeft]);
        }

        TreeNode root = new TreeNode(pre[preLeft]);

        int subtree1PreLeft = preLeft + 1;
        int subtree1PreRight = map1.get(post[postRight - 1]) - 1;
        int subtree2PreLeft = subtree1PreRight + 1;
        int subtree2PreRight = preRight;

        int subtree1PostLeft = postLeft;
        int subtree1PostRight = map2.get(pre[preLeft + 1]);
        int subtree2PostLeft = subtree1PostRight + 1;
        int subtree2PostRight = postRight - 1;

        root.left = helper(pre, subtree1PreLeft, subtree1PreRight,
                           post, subtree1PostLeft, subtree1PostRight,
                           map1, map2);
        root.right = helper(pre, subtree2PreLeft, subtree2PreRight,
                            post, subtree2PostLeft, subtree2PostRight,
                            map1, map2);
        return root;
    }
}
