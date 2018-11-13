/*
* Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Example:

Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
Output: ["2", "4->49", "51->74", "76->99"]

    20181111
    163
    medium
    O(n)
    O(1)
* */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class MissingRanges {
    public static void main(String[] args) {
        for (String res : new MissingRanges().findMissingRanges(new int[]{}, -2147483648, 2147483647)) {
            System.out.println(res);
        }
    }

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> res = new ArrayList<>();
        Long prevNum = null;
        long expecting = lower;
        for (int i = 0; i <= nums.length; i++) {
            long num = (i == nums.length) ? (long) upper + 1 : (long) (nums[i]);
            if (prevNum != null && num == prevNum) {
                continue;
            }
            if (num == expecting + 1) {
                res.add(String.valueOf(expecting));
            } else if (num > expecting + 1) {
                res.add(String.valueOf(expecting) + "->" + String.valueOf(num - 1));
            }

            expecting = num + 1;
            prevNum = num;
        }
        return res;
    }
}
