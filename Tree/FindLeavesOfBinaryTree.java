/*
* Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.



Example:

Input: [1,2,3,4,5]

          1
         / \
        2   3
       / \
      4   5

Output: [[4,5,3],[2],[1]]


Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         /
        2


2. Now removing the leaf [2] would result in this tree:

          1


3. Now removing the leaf [1] would result in the empty tree:

          []


    20181005
    366
    medium
    O(n)
    O(h)
* */
package leetcode.Tree;

import java.util.ArrayList;
import java.util.List;

public class FindLeavesOfBinaryTree {
    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> sol = new ArrayList<>();
        dfs(root, sol);
        return sol;
    }

    private int dfs(TreeNode root, List<List<Integer>> sol) {
        if (root == null) {
            return -1;
        }

        int leftDepth = dfs(root.left, sol);
        int rightDepth = dfs(root.right, sol);

        int index = Math.max(leftDepth, rightDepth) + 1;
        if (index >= sol.size()) {
            sol.add(new ArrayList<>());
        }
        sol.get(index).add(root.val);

        return index;
    }
}
