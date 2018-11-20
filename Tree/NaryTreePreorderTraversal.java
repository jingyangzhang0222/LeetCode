/*
* Given an n-ary tree, return the preorder traversal of its nodes' values.

For example, given a 3-ary tree:







Return its preorder traversal as: [1,3,5,6,2,4].



Note:

Recursive solution is trivial, could you do it iteratively?

    20181119
    589
    easy
    O(n)
    O(n)
* */
package leetcode.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class NaryTreePreorderTraversal {
    public List<Integer> preorder(Node root) {
        Deque<Node> s = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        s.offerLast(root);
        while (!s.isEmpty()) {
            Node node = s.pollLast();
            res.add(node.val);
            if (node.children == null) continue;
            for (int i = node.children.size() - 1; i >= 0; i--) {
                s.offerLast(node.children.get(i));
            }
        }
        return res;
    }

    private class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}
