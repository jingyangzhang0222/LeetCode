/*
* Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.

    20181023
    41
    hard
    O(n)
    O(1)
* */
package leetcode.Google;

public class FirstMissingPositive {
    public static void main(String[] args) {
        System.out.print(new FirstMissingPositive().firstMissingPositive(new int[]{3,4,-1,1}));
    }

    public int firstMissingPositive(int[] nums) {
        // [1,2,0] -> [2,1,0] -> [0,1,2]
        // [3,4,-1,1] -> [1,4,-1,3]
        int i = 0;
        while (i < nums.length) {
            if (nums[i] >= 0 && nums[i] < nums.length && nums[nums[i]] != nums[i]) {
                swap(nums, i, nums[i]);
                // now nums[*X] == *X, (0 < *X < n)
            } else {
                i++;
            }
        }

        int res = 1;
        for (int j = 1; j < nums.length; j++) {
            int num = nums[j];
            if (0 < num && num < nums.length) {
                if (num > res) {
                    return res;
                }
                if (num == res) {
                    res++;
                }
            }
        }

        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
