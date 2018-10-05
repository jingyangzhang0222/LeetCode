/*
* You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
             because they are adjacent houses.
Example 2:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.

    20180930
    213
    medium
    O(n)
    O(1)
* */
package leetcode.DP;

public class HouseRobberII {
    public int rob(int[] nums) {
        if (nums.length <= 1) {
            return nums.length == 1 ? nums[0] : 0;
        }

        int firstCur = 0;
        int firstNotCur = nums[0];
        int notFirstCur = nums[1];
        int notFirstNotCur = 0;
        for (int i = 3; i <= nums.length; i++) {
            int index = i - 1;
            // take first
            int firstPrev = firstCur;
            int firstNotPrev = firstNotCur;
            firstCur = firstNotPrev + nums[index];
            firstNotCur = Math.max(firstNotPrev, firstPrev);

            // not take first
            int notFirstPrev = notFirstCur;
            int notFirstNotPrev = notFirstNotCur;
            notFirstCur = notFirstNotPrev + nums[index];
            notFirstNotCur = Math.max(notFirstPrev, notFirstNotPrev);
        }

        return Math.max(firstNotCur, Math.max(notFirstCur, notFirstNotCur));
    }
}
