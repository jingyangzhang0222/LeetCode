/*
* If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.

For each integer in this list:
The hundreds digit represents the depth D of this node, 1 <= D <= 4.
The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a full binary tree.
The units digit represents the value V of this node, 0 <= V <= 9.
Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. You need to return the sum of all paths from the root towards the leaves.

Example 1:
Input: [113, 215, 221]
Output: 12
Explanation:
The tree that the list represents is:
    3
   / \
  5   1

The path sum is (3 + 5) + (3 + 1) = 12.
Example 2:
Input: [113, 221]
Output: 4
Explanation:
The tree that the list represents is:
    3
     \
      1

The path sum is (3 + 1) = 4.


    20181009
    666
    medium
    O(n)
    O(n)
* */
package leetcode.Tree;

import java.util.HashMap;
import java.util.Map;

public class PathSumIV {
    private int sum = 0;
    public int pathSum(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int key = num / 10;
            int value = num % 10;
            map.put(key, value);
        }

        dfs(map, 11, 0);
        return sum;
    }

    private void dfs(Map<Integer, Integer> map, int key, int prevSum) {
        if (!map.containsKey(key)) {
            return;
        }

        int curSum = prevSum + map.get(key);

        int level = key / 10;
        int pos = key % 10;

        int leftChildKey = (level + 1) * 10 + pos * 2 - 1;
        int rightChildKey = (level + 1) * 10 + pos * 2;
        if (!map.containsKey(leftChildKey) && !map.containsKey(rightChildKey)) {
            // is a leave
            sum += curSum;
            return;
        } else {
            dfs(map, leftChildKey, curSum);
            dfs(map, rightChildKey, curSum);
        }
    }
}
