/*
* Given an n-ary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example, given a 3-ary tree:





We should return its level order traversal:





[
     [1],
     [3,2,4],
     [5,6]
]


Note:

The depth of the tree is at most 1000.
The total number of nodes is at most 5000.

    20180723
    429
    easy
    O(n)
    O(n)
* */
package leetcode.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class NaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> sol = new ArrayList<>();
        if (root == null) {
            return sol;
        }
        Queue<Node> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> subsol = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = q.poll();
                subsol.add(node.val);
                for (Node child : node.children) {
                    q.offer(child);
                }
            }
            sol.add(new ArrayList(subsol));
        }
        return sol;
    }

    static class Node {
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
