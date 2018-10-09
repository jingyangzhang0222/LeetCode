/*
* Given a binary tree, return all duplicate subtrees. For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1:

        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:

      2
     /
    4
and

    4
Therefore, you need to return above trees' root in the form of a list.


    20181005
    652
    medium
    O(n ^ 2)
    O(n)
* */
package leetcode.Google;

import leetcode.Tree.TreeNode;

import java.util.*;

public class FindDuplicateSubtrees {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        // encode
        Map<String, TreeNode> map = new HashMap<>();
        Set<TreeNode> res = new HashSet<>();
        dfs(root, res, map);
        List<TreeNode> sol = new ArrayList<>();
        for (TreeNode node : res) {
            sol.add(node);
        }
        return sol;
    }

    private String dfs(TreeNode root, Set<TreeNode> res, Map<String, TreeNode> map) {
        if (root == null) {
            return "n";
        }

        String leftPostOrder = dfs(root.left, res, map);
        String rightPostOrder = dfs(root.right, res, map);

        String encoded = leftPostOrder + "," + rightPostOrder + "," + String.valueOf(root.val);
        if (map.containsKey(encoded)) {
            res.add(map.get(encoded));
        } else {
            map.put(encoded, root);
        }

        return encoded;
    }
}
