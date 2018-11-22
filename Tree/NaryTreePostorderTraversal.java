/*
* Given an n-ary tree, return the postorder traversal of its nodes' values.

For example, given a 3-ary tree:







Return its postorder traversal as: [5,6,3,2,4,1].


Note:

Recursive solution is trivial, could you do it iteratively?

    20181120
    590
    easy
    O(n)
    O(n)
* */
package leetcode.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class NaryTreePostorderTraversal {
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<Node> s = new ArrayDeque<>();
        Node prevPolled = null;
        s.offerLast(root);
        while (!s.isEmpty()) {
            Node cur = s.peekLast();
            boolean needToPoll = cur.children.size() == 0 || prevPolled == cur.children.get(cur.children.size() - 1);
            if (needToPoll) {
                s.pollLast();
                res.add(cur.val);
                prevPolled = cur;
            } else {
                for (int i = cur.children.size() - 1; i >= 0; i--) {
                    s.offerLast(cur.children.get(i));
                }
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
