/*
* Given a n-ary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

For example, given a 3-ary tree:





We should return its max depth, which is 3.

Note:

The depth of the tree is at most 1000.
The total number of nodes is at most 5000.

    20180724
    559
    easy
    O(n)
    O(h)
* */
package leetcode.Tree;

import java.util.List;

public class MaximumDepthOfNaryTree {
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        int max = 0;
        for (Node child : root.children) {
            max = Math.max(max, maxDepth(child));
        }

        return ++max;
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int val, List<Node> children) {
            this.val = val;
            this.children = children;
        }
    }
}
