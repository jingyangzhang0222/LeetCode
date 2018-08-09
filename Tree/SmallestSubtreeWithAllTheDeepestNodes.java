package leetcode.Tree;

public class SmallestSubtreeWithAllTheDeepestNodes {
    private int maxDep = 0;
    private int maxNodeCnt = 0;

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        TreeNode[] res = new TreeNode[1];
        dfs(root, res, 1);
        return res[0];
    }

    private int[] dfs(TreeNode root, TreeNode[] res, int dep) {
        // index 0: maxDep in this subtree
        // index 1: number of nodes with maxDep
        if (root == null) {
            return new int[]{-1, 0};
        }

        if (root.left == null && root.right == null) {
            if (dep > maxDep) {
                maxDep = dep;
                maxNodeCnt = 1;
                res[0] = root;
            } else if (dep == maxDep) {
                maxNodeCnt++;
            }
            return new int[]{dep, 1};
        }

        int[] left = dfs(root.left, res, dep + 1);
        int[] right = dfs(root.right, res, dep + 1);

        int maxSubTreeDep = Math.max(left[0], right[0]);
        int maxDepNodeCnt;
        if (left[0] == right[0]) {
            maxDepNodeCnt = left[1] + right[1];
        } else if (left[0] > right[0]) {
            maxDepNodeCnt = left[1];
        } else {
            maxDepNodeCnt = right[1];
        }

        if (left[0] == maxDep && right[0] == maxDep && left[1] + right[1] == maxNodeCnt) {
            res[0] = root;
        }

        return new int[]{maxSubTreeDep, maxDepNodeCnt};
    }
}