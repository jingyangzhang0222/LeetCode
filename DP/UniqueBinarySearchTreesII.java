/*
* Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.

Example:

Input: 3
Output:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
Explanation:
The above output corresponds to the 5 unique BST's shown below:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

   20180620
   95
   medium
   O(??)
   O(??)
* */
package leetcode.DP;

import leetcode.Tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTreesII {
    public List<TreeNode> generateTreesDP(int n) {
        if (n == 0) return new ArrayList<TreeNode>();
        List<TreeNode>[] dp = new List[n + 1];/////////////

        // base case
        List<TreeNode> dp0 = new ArrayList<>();
        dp0.add(null);
        dp[0] = dp0;

        // induction rule
        for (int size = 1; size <= n; size++) {
            List<TreeNode> listForThisSize = new ArrayList<>();
            for (int rootVal = 1; rootVal <= size; rootVal++) {
                int leftSize = rootVal - 1;
                int rightSize = size - 1 - leftSize;
                List<TreeNode> leftSubTrees = dp[leftSize];
                List<TreeNode> fakeRightSubTrees = dp[rightSize];
                for (TreeNode fakeRightRoot : fakeRightSubTrees) {
                    TreeNode realRightRoot = clone(fakeRightRoot, rootVal);
                    for (TreeNode leftRoot : leftSubTrees) {
                        TreeNode root = new TreeNode(rootVal);
                        root.left = leftRoot;
                        root.right = realRightRoot;
                        listForThisSize.add(root);
                    }
                }
            }
            dp[size] = listForThisSize;
        }
        return dp[n];
    }

    private TreeNode clone(TreeNode root, int offset) {
        if (root == null) {
            return null;
        }
        TreeNode newRoot = new TreeNode(root.val + offset);
        newRoot.left = clone(root.left, offset);
        newRoot.right = clone(root.right, offset);
        return newRoot;
    }

    public List<TreeNode> generateTreesDFS(int n) {
        if (n == 0) return new ArrayList<TreeNode>();
        return helper(1, n);
    }

    private List<TreeNode> helper(int lower, int upper) {
        List<TreeNode> res = new ArrayList<>();
        if (lower > upper) {
            res.add(null);
            return res;
        }
        if (lower == upper) {
            res.add(new TreeNode(lower));
            return res;
        }

        for (int i = lower; i <= upper; i++) {
            int rootVal = i;
            List<TreeNode> leftSubTrees = helper(lower, i - 1);
            List<TreeNode> rightSubTrees = helper(i + 1, upper);
            for (TreeNode leftRoot : leftSubTrees) {
                for (TreeNode rightRoot : rightSubTrees) {
                    TreeNode root = new TreeNode(rootVal);
                    root.left = leftRoot;
                    root.right = rightRoot;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
