/*
* Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.

Since the answer may be large, return the answer modulo 10^9 + 7.



Example 1:

Input: [3,1,2,4]
Output: 17
Explanation: Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.


Note:

1 <= A.length <= 30000
1 <= A[i] <= 30000

    20181118
    907
    medium
    O(n)
    O(n)
* */
package leetcode.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class SumOfSubarrayMinimums {
    public static void main(String[] args) {
        System.out.println(new SumOfSubarrayMinimums().sumSubarrayMins(new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}));
    }

    public int sumSubarrayMins(int[] A) {
        final int M = 1000000007;
        long sum = 0;
        Deque<Integer> left = new ArrayDeque<>(), right = new ArrayDeque<>();
        int[][] check = new int[A.length][2];
        for (int i = 0; i < A.length; i++) {
            int j = A.length - 1 - i;
            while (!left.isEmpty() && A[left.peekLast()] >= A[i]) {
                left.pollLast();
            }
            check[i][0] = left.isEmpty() ? -1 : left.peekLast();
            left.offerLast(i);
            while (!right.isEmpty() && A[right.peekFirst()] > A[j]) {
                right.pollFirst();
            }
            check[j][1] = right.isEmpty() ? A.length : right.peekFirst();
            right.offerFirst(j);
        }

        for (int i = 0; i < check.length; i++) {
            int leftLength = i - check[i][0];
            int rightLength = check[i][1] - i;
            sum = (sum + leftLength * rightLength * A[i]) % M;
        }

        return (int) sum;
    }
}
