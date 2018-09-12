/*
* A full binary tree is a binary tree where each node has exactly 0 or 2 children.

Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.

Each node of each tree in the answer must have node.val = 0.

You may return the final list of trees in any order.



Example 1:

Input: 7
Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
Explanation:



Note:

1 <= N <= 20

    20180911
    894
    medium
    O()
    O()
* */
package leetcode.Tree;

import java.util.ArrayList;
import java.util.List;

public class AllPossibleFullBinaryTrees {
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> res = new ArrayList<>();
        if (N % 2 == 0) {
            return res;
        }

        if (N == 1) {
            res.add(new TreeNode(0));
            return res;
        }

        for (int leftSize = 1; leftSize <= N - 1; leftSize += 2) {
            int rightSize = N - leftSize - 1;
            List<TreeNode> leftTrees = allPossibleFBT(leftSize);
            List<TreeNode> rightTrees = allPossibleFBT(rightSize);
            for (TreeNode leftSubTreeRoot : leftTrees) {
                for (TreeNode rightSubTreeRoot : rightTrees) {
                    TreeNode root = new TreeNode(0);
                    root.left = leftSubTreeRoot;
                    root.right = rightSubTreeRoot;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
