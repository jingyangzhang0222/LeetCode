/*
* Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

Input: [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.
Note:

You can assume that you can always reach the last index.

    20181022
    45
    hard
    O(n)
    O(10
* */
package leetcode.BFS;

public class JumpGameII {
    public int jump(int[] nums) {
        int left = 0, right = 0, step = 0;
        while (right < nums.length - 1) {
            step++;
            int rightMostDiscovered = right;
            for (int i = left; i <= right; i++) {
                rightMostDiscovered = Math.max(rightMostDiscovered, i + nums[i]);
            }
            left = right;
            right = rightMostDiscovered;
        }
        return step;
    }
}
