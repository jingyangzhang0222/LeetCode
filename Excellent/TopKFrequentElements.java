/*
* Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

    20180908
    347
    medium
    O(n)
    O(# of distinct elements)
* */
package leetcode.Excellent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // [1,222,333,444,55555], k = 2
        // [{1, 1}, {2, 3}, {3, 3}, {4, 3}, {5, 5}]
        // 1       3        5
        // [1]  [2,3,4]    [5]
        // [5, 2, 3, 4]
        Map<Integer, Integer> freMap = new HashMap<>();
        int maxFre = 1;
        for (int num : nums) {
            if (!freMap.containsKey(num)) {
                freMap.put(num, 0);
            }
            freMap.put(num, freMap.get(num) + 1);
            maxFre = Math.max(freMap.get(num), maxFre);
        }

        // fre -> List of elements
        Map<Integer, List<Integer>> buckets = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : freMap.entrySet()) {
            int val = entry.getKey();
            int fre = entry.getValue();
            if (!buckets.containsKey(fre)) {
                buckets.put(fre, new ArrayList<Integer>());
            }
            buckets.get(fre).add(val);
        }

        List<Integer> res = new ArrayList<>();
        int cnt = 0;
        for (int fre = maxFre; fre >= 1; fre--) {
            if (buckets.containsKey(fre)) {
                for (Integer val : buckets.get(fre)) {
                    res.add(val);
                    cnt++;
                }
            }
            if (cnt == k) {
                break;
            }
        }
        return res;
    }
}
