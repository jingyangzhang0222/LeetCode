/*
* Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever the vertical line touches some nodes, we report the values of the nodes in order from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the value of the node that is reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.  Every report will have a list of values of nodes.



Example 1:



Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation:
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).
Example 2:



Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
The node with value 5 and the node with value 6 have the same position according to the given scheme.
However, in the report "[1,5,6]", the node value of 5 comes first since 5 is smaller than 6.


Note:

The tree will have between 1 and 1000 nodes.
Each node's value will be between 0 and 1000.


    20190307
    987
    medium
    O(nlogn)
    O(n)
* */
package leetcode.Tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class VerticalOrderTraversalOfABinaryTree {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(new MyComparator());
        int[] min = new int[1];
        dfs(root, min, 0, 0, pq);

        int preX = min[0] - 1;
        List<Integer> list = null;
        while (!pq.isEmpty()) {
            if (pq.peek()[0] > preX) {
                list = new ArrayList<>();
                res.add(list);
            }
            int[] info = pq.poll();
            list.add(info[2]);
            preX = info[0];
        }
        return res;
    }

    private void dfs(TreeNode root, int[] min, int x, int y, PriorityQueue<int[]> pq) {
        if (root == null) {
            return;
        }

        pq.offer(new int[]{x, y, root.val});
        if (x < min[0]) {
            min[0] = x;
        }
        dfs(root.left, min, x - 1, y - 1, pq);
        dfs(root.right, min, x + 1, y - 1, pq);
    }

    class MyComparator implements Comparator<int[]> {
        @Override
        public int compare(int[] i1, int[] i2) {
            if (i1[0] != i2[0]) {
                return i1[0] < i2[0] ? -1 : 1;
            }
            if (i1[1] != i2[1]) {
                return i1[1] < i2[1] ? 1 : -1;
            }
            if (i1[2] != i2[2]) {
                return i1[2] < i2[2] ? -1 : 1;
            }
            return 0;
        }
    }
}
