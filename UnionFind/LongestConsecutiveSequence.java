/*
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.

    20180627
    128
    hard
    Union Find
    O(n)
    O(1)
*/
package leetcode.UnionFind;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int globalMax = 0;
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int curLen = 1;

                int ele = num;
                while (set.contains(++ele)) {
                    curLen++;
                }

                globalMax = Math.max(globalMax, curLen);
            }
        }
        return globalMax;
    }
}