package leetcode.BinaryrSearch;

public class lastOccurance {
    public static int last(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        // right - left <= 1
        if (nums[right] == target) {
            return right;
        } else {
            return nums[left] == target ? left : -1;
        }
    }
}
