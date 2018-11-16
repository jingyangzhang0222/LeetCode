/*
* Given a sorted array of integers nums and integer values a, b and c.
Apply a quadratic function of the form f(x) = ax2 + bx + c to each element x in the array.

The returned array must be in sorted order.

Expected time complexity: O(n)

Example 1:

Input: nums = [-4,-2,2,4], a = 1, b = 3, c = 5
Output: [3,9,15,33]
Example 2:

Input: nums = [-4,-2,2,4], a = -1, b = 3, c = 5
Output: [-23,-5,1,7]

    20181115
    360
    meidum
    o(n)
    O(1)
* */
package leetcode.Math;

public class SortTransformedArray {
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] res = new int[nums.length];
        if (a == 0) {
            for (int i = 0; i < nums.length; i++) {
                int j = b >= 0 ? i : nums.length - 1 - i;
                res[j] = b * nums[i] + c;
            }
            return res;
        }

        double axis = -b / (a * 2.0);

        int left = 0, right = nums.length - 1;
        int index = a > 0 ? nums.length - 1 : 0;
        while (left <= right) {
            int num;
            if (nums[left] == nums[right] || Math.abs((double)nums[left] - axis) >
                                             Math.abs((double)nums[right] - axis)) {
                num = nums[left];
                left++;
            } else {
                num = nums[right];
                right--;
            }

            res[index] = a * num * num + b * num + c;
            index += a > 0 ? -1 : 1;
        }
        return res;
    }
}
