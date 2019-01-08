/*
* Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.

Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.

Example 1:
Input: [1, 2, 2, 3, 1]
Output: 2
Explanation:
The input array has a degree of 2 because both elements 1 and 2 appear twice.
Of the subarrays that have the same degree:
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
The shortest length is 2. So return 2.
Example 2:
Input: [1,2,2,3,1,4,2]
Output: 6
Note:

nums.length will be between 1 and 50,000.
nums[i] will be an integer between 0 and 49,999.


    20190108
    697
    easy
    O(n)
    O(n)
* */
package leetcode;

import java.util.HashMap;
import java.util.Map;

public class DegreeOfAnArray {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        int degree = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], new int[]{i, i, 1});
            } else {
                int[] info = map.get(nums[i]);
                info[1] = i;
                info[2]++;
            }

            degree = Math.max(degree, map.get(nums[i])[2]);
        }

        int minRange = 50001;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] info = entry.getValue();
            if (info[2] == degree) {
                minRange = Math.min(minRange, info[1] - info[0] + 1);
            }
        }

        return minRange;
    }
}
