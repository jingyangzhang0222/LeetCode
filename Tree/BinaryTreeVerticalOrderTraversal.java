/*
* Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples 1:

Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]
Examples 2:

Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]
Examples 3:

Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]

    20181013
    314
    medium
    O(n)
    O(n)
* */
package leetcode.Tree;

import java.util.*;

public class BinaryTreeVerticalOrderTraversal {
    private int maxIndex = -1;
    private int minIndex = 1;

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> sol = new ArrayList<>();
        if (root == null) {
            return sol;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<TreeNode, Integer> getIndex = new HashMap<>();
        getIndex.put(root, 0);
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int nodeIndex = getIndex.get(node);
            minIndex = Math.min(minIndex, nodeIndex);
            maxIndex = Math.max(maxIndex, nodeIndex);
            if (!map.containsKey(nodeIndex)) {
                map.put(nodeIndex, new ArrayList<Integer>());
            }
            map.get(nodeIndex).add(node.val);

            if (node.left != null) {
                getIndex.put(node.left, nodeIndex - 1);
                q.offer(node.left);
            }

            if (node.right != null) {
                getIndex.put(node.right, nodeIndex + 1);
                q.offer(node.right);
            }
        }

        for (int i = minIndex; i <= maxIndex; i++) {
            sol.add(map.get(i));
        }
        return sol;
    }
}
