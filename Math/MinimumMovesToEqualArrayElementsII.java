package leetcode.Math;

import java.util.Arrays;

public class MinimumMovesToEqualArrayElementsII {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int median = nums[nums.length / 2];
        int res = 0;
        for (int num : nums) {
            res += Math.abs(median - num);
        }
        return res;
    }
}
