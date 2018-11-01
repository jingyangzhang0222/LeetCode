/*
* Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

Example 1:
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
Example 2:
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
Example 3:
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).
Note:
The pairs (i, j) and (j, i) count as the same pair.
The length of the array won't exceed 10,000.
All the integers in the given input belong to the range: [-1e7, 1e7].

    20181019
    532
    easy
    O(n)
    O(1)
* */
package leetcode.Hash;

import java.util.HashMap;
import java.util.Map;

public class KDiffPairsInAnArray {
    public int findPairs(int[] nums, int k) {
        if (k < 0) {
            return 0;
        }
        Map<Integer, boolean[]> map = new HashMap<>();
        int cnt = 0;
        for (int num : nums) {
            boolean exists = map.containsKey(num);
            boolean existsSmaller = map.containsKey(num - k);
            boolean existsLarger = k != 0 && map.containsKey(num + k);
            boolean smallerCounted = exists && map.get(num)[0];
            boolean largerCounted = exists && map.get(num)[1];

            boolean[] res = new boolean[2];
            res[0] = smallerCounted;
            res[1] = largerCounted;
            if (existsSmaller && !smallerCounted) {
                cnt++;
                res[0] = true;
                map.get(num - k)[1] = true;
            }
            if (existsLarger && !largerCounted) {
                cnt++;
                res[1] = true;
                map.get(num + k)[0] = true;
            }

            map.put(num, res);
        }
        return cnt;
    }
}
