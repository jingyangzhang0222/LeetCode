/*
* Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Note: The algorithm should run in linear time and in O(1) space.

Example 1:

Input: [3,2,3]
Output: [3]
Example 2:

Input: [1,1,1,3,3,2,2,2]
Output: [1,2]


    20181102
    229
    medium
    O(n)
    O(1)
* */
package leetcode.Excellent;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        Integer[] res = new Integer[2];
        int cnt0 = 0, cnt1 = 0;
        for (int num : nums) {
            if (res[0] != null && res[0] == num) {
                cnt0++;
                continue;
            }
            if (res[1] != null && res[1] == num) {
                cnt1++;
                continue;
            }

            // does not match anyone
            if (res[0] == null) {
                cnt0 = 1;
                res[0] = num;
            } else if (res[1] == null) {
                cnt1 = 1;
                res[1] = num;
            } else {
                // no match, neither is null
                if (--cnt0 == 0) {
                    res[0] = null;
                }
                if (--cnt1 == 0) {
                    res[1] = null;
                }
            }
        }

        cnt0 = 0;
        cnt1 = 0;
        for (int num : nums) {
            if (res[0] != null && num == res[0]) {
                cnt0++;
            }
            if (res[1] != null && num == res[1]) {
                cnt1++;
            }
        }

        List<Integer> sol = new ArrayList<>();
        if (cnt0 > nums.length / 3) {
            sol.add(res[0]);
        }
        if (cnt1 > nums.length / 3) {
            sol.add(res[1]);
        }
        return sol;
    }
}
