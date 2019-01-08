/*
* Given an array with n integers, you need to find if there are triplets (i, j, k) which satisfies following conditions:

0 < i, i + 1 < j, j + 1 < k < n - 1
Sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) should be equal.
where we define that subarray (L, R) represents a slice of the original array starting from the element indexed L to the element indexed R.
Example:
Input: [1,2,1,2,1,2,1]
Output: True
Explanation:
i = 1, j = 3, k = 5.
sum(0, i - 1) = sum(0, 0) = 1
sum(i + 1, j - 1) = sum(2, 2) = 1
sum(j + 1, k - 1) = sum(4, 4) = 1
sum(k + 1, n - 1) = sum(6, 6) = 1
Note:
1 <= n <= 2000.
Elements in the given array will be in range [-1,000,000, 1,000,000].

    20190102
    548
    medium
    O(n ^ 2)
    O(n)
* */
package leetcode.Excellent;

import java.util.HashSet;
import java.util.Set;

public class SplitArrayWithEqualSum {
    public boolean splitArray(int[] nums) {
        final int MIN_LENGTH_LIMIT = 7;
        final int FIRST_INDEX = 0;
        if (nums.length < MIN_LENGTH_LIMIT) {
            return false;
        }
        // 1, 2, 1, 2, 1, 2, 1
        // 1, 3, 4, 6, 7, 9, 10
        int[] tmp = new int[nums.length];
        tmp[FIRST_INDEX] = nums[FIRST_INDEX];
        for (int i = 1; i < nums.length; i++) {
            tmp[i] = tmp[i - 1] + nums[i];
        }


        for (int j = 3; j < nums.length - 3; j++) {
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i < j - 1; i++) {
                if (tmp[i - 1] == tmp[j - 1] - tmp[i]) {
                    set.add(tmp[i - 1]);
                }
            }

            for (int k = j + 2; k < nums.length - 1; k++) {
                if (tmp[k - 1] - tmp[j] == tmp[nums.length - 1] - tmp[k] && set.contains(tmp[k - 1] - tmp[j])) {
                    return true;
                }
            }
        }

        return false;
    }
}
