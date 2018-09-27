/*
* A sequence X_1, X_2, ..., X_n is fibonacci-like if:

n >= 3
X_i + X_{i+1} = X_{i+2} for all i + 2 <= n
Given a strictly increasing array A of positive integers forming a sequence, find the length of the longest fibonacci-like subsequence of A.  If one does not exist, return 0.

(Recall that a subsequence is derived from another sequence A by deleting any number of elements (including none) from A, without changing the order of the remaining elements.  For example, [3, 5, 8] is a subsequence of [3, 4, 5, 6, 7, 8].)



Example 1:

Input: [1,2,3,4,5,6,7,8]
Output: 5
Explanation:
The longest subsequence that is fibonacci-like: [1,2,3,5,8].
Example 2:

Input: [1,3,7,11,12,14,18]
Output: 3
Explanation:
The longest subsequence that is fibonacci-like:
[1,11,12], [3,11,14] or [7,11,18].


Note:

3 <= A.length <= 1000
1 <= A[0] < A[1] < ... < A[A.length - 1] <= 10^9
(The time limit has been reduced by 50% for submissions in Java, C, and C++.)

    20180923
    873
    medium
    O(n ^ 2)
    O(n ^ 2)
* */
package leetcode.Excellent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LengthOfLongestFibonacciSubsequence {
    public int lenLongestFibSubseq(int[] nums) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            indexMap.put(nums[i], i);
        }

        int[][] dp = new int[nums.length][nums.length];
        int maxCnt = 2;
        for (int k = 1; k < nums.length; k++) {
            for (int j = 0; j < k; j++) {
                int third = nums[k];
                int second = nums[j];

                int i = indexMap.getOrDefault(third - second, -1);
                dp[j][k] = (third - second < second && i >= 0) ? dp[i][j] + 1 : 2;
                maxCnt = Math.max(maxCnt, dp[j][k]);
            }
        }

        return maxCnt >= 3 ? maxCnt : 0;
    }

    public int lenLongestFibSubseqBruteForce(int[] nums) {
        //brute force
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int maxCnt = 2;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int cnt = 2;
                int first = nums[i];
                int second = nums[j];
                while (set.contains(first + second)) {
                    cnt++;
                    int tmp = first;
                    first = second;
                    second = tmp + second;
                }

                maxCnt = Math.max(maxCnt, cnt);
            }
        }

        return maxCnt >= 3 ? maxCnt : 0;
    }
}
