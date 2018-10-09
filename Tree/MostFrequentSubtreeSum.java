/*
* Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.

Examples 1
Input:

  5
 /  \
2   -3
return [2, -3, 4], since all the values happen only once, return all of them in any order.
Examples 2
Input:

  5
 /  \
2   -5
return [2], since 2 happens twice, however -5 only occur once.
Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.

    20181008
    508
    medium
    o(n)
    O(n)
* */
package leetcode.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostFrequentSubtreeSum {
    private int max;
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> sol = new ArrayList<>();
        dfs(root, sol, map);
        return toIntArray(sol.toArray(new Integer[0]));
    }

    private int dfs(TreeNode root, List<Integer> sol, Map<Integer, Integer> map) {
        if (root == null) {
            return 0;
        }

        int leftSum = dfs(root.left, sol, map);
        int rightSum = dfs(root.right, sol, map);

        int sum = leftSum + root.val + rightSum;
        int fre = map.getOrDefault(sum, 0);
        map.put(sum, ++fre);
        if (fre >= max) {
            if (fre > max) {
                sol.clear();
            }
            max = fre;
            sol.add(sum);
        }

        return sum;
    }

    private int[] toIntArray(Integer[] arr) {
        if (arr == null) {
            return null;
        }

        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }
}
