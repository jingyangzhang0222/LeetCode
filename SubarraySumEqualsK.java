/*
* Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

    20180626
    560
    medium
    prefix + HashMap
    O(n)
    O(n)
*
* */
package leetcode;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        int[] prefixSum = new int[nums.length];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum[i] = i == 0 ? nums[i] : nums[i] + prefixSum[i - 1];
            if (map.containsKey(prefixSum[i] - k)) {
                count += map.get(prefixSum[i]- k);
            }
            if (!map.containsKey(prefixSum[i])) {
                map.put(prefixSum[i], 0);
            }
            map.put(prefixSum[i], map.get(prefixSum[i]) + 1);
        }
        return count;
    }
}
