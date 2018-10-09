/*
* Given a binary tree where every node has a unique value, and a target key k, find the value of the nearest leaf node to target k in the tree.

Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.

In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.

Example 1:

Input:
root = [1, 3, 2], k = 1
Diagram of binary tree:
          1
         / \
        3   2

Output: 2 (or 3)

Explanation: Either 2 or 3 is the nearest leaf node to the target of 1.
Example 2:

Input:
root = [1], k = 1
Output: 1

Explanation: The nearest leaf node is the root node itself.
Example 3:

Input:
root = [1,2,3,4,null,null,null,5,null,6], k = 2
Diagram of binary tree:
             1
            / \
           2   3
          /
         4
        /
       5
      /
     6

Output: 3
Explanation: The leaf node with value 3 (and not the leaf node with value 6) is nearest to the node with value 2.
Note:
root represents a binary tree with at least 1 node and at most 1000 nodes.
Every node has a unique node.val in range [1, 1000].
There exists some node in the given binary tree for which node.val == k.

    20181006
    742
    medium
    O(n)
    O(n)
* */
package leetcode.Tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClosestLeafInABinaryTree {
    private TreeNode target;
    private int minDis = Integer.MAX_VALUE;
    private int res;

    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        find(root, k, map);
        dfs(target, visited, map, 0);
        return res;
    }

    private void dfs(TreeNode root, Set<Integer> visited, Map<TreeNode, TreeNode> map, int dis) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (dis < minDis) {
                minDis = dis;
                res = root.val;
            }
            return;
        }
        visited.add(root.val);
        TreeNode[] neighbors = new TreeNode[]{root.left, root.right, map.getOrDefault(root, null)};
        for (TreeNode neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited, map, dis + 1);
            }
        }
    }

    private void find(TreeNode root, int k, Map<TreeNode, TreeNode> map) {
        if (root == null) {
            return;
        }

        if (root.val == k) {
            target = root;
            return;
        }
        if (root.left != null) {
            map.put(root.left, root);
        }
        if (root.right != null) {
            map.put(root.right, root);
        }
        find(root.left, k, map);
        find(root.right, k, map);
    }
}
