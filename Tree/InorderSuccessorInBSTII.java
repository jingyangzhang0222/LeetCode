package leetcode.Tree;

public class InorderSuccessorInBSTII {
    public Node inorderSuccessor(Node x) {
        if (x == null) {
            return x;
        }
        // 1. the minimum element in the right subtree,
        // 2. find in upper level, find the first element such that, res.left is the subtree which x is in
        Node minimumInRight = findInRightSubtree(x.right);
        if (minimumInRight != null) {
            return minimumInRight;
        }
        // right subtree is null;
        while (x.parent != null) {
            if (x == x.parent.left) {
                return x.parent;
            }
            x = x.parent;
        }
        return null;
    }

    private Node findInRightSubtree(Node x) {
        Node pre = null;
        while (x != null) {
            pre = x;
            x = x.left;
        }
        return pre;
    }
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
}
