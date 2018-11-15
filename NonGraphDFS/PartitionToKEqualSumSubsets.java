package leetcode.NonGraphDFS;

public class PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k == 1) {
            return true;
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }

        return dfs(nums, sum / k, 0, sum / k, new boolean[nums.length], k);
    }

    private boolean dfs(int[] nums, int sum, int start, int target, boolean[] used, int k) {
        if (k == 1) {
            return true;
        }

        for (int i = start; i < nums.length; i++) {
            if (!used[i] && nums[i] <= sum) {
                int newStart = sum == nums[i] ? 0 : i + 1;
                int newSum = sum == nums[i] ? target : sum - nums[i];
                int newK = sum == nums[i] ? k - 1 : k;
                used[i] = true;
                if (dfs(nums, newSum, newStart, target, used, newK)) {
                    return true;
                }
                used[i] = false;
            }
        }
        return false;
    }
}
