/*
* You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11

    20181009
    437
    easy
    O(n)
    o(h)
* */
package leetcode.Tree;

import java.util.HashMap;
import java.util.Map;

public class PathSumIII {
    private int cnt;
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        dfs(root, map, 0, sum);
        return cnt;
    }

    private void dfs(TreeNode root, Map<Integer, Integer> map, int prevSum, int target) {
        if (root == null) {
            return;
        }

        int curSum = root.val + prevSum;
        if (map.containsKey(curSum - target)) {
            cnt += map.get(curSum - target);
        }

        map.put(curSum, map.getOrDefault(curSum, 0) + 1);
        dfs(root.left, map, curSum, target);
        dfs(root.right, map, curSum, target);
        map.put(curSum, map.get(curSum) - 1);
    }
}
