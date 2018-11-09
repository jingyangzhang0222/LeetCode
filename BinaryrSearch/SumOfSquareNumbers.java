/*
* Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.

Example 1:
Input: 5
Output: True
Explanation: 1 * 1 + 2 * 2 = 5
Example 2:
Input: 3
Output: False

    20181106
    633
    easy
    O(logn * n ^ 0.5)
    O(1)
* */
package leetcode.BinaryrSearch;

public class SumOfSquareNumbers {
    public boolean judgeSquareSum(int n) {
        for (long i = 0; i * i <= n; i++) {
            if (isSquare(n - (int)(i * i))) {
                return true;
            }
        }
        return false;
    }

    private boolean isSquare(int n) {
        long left = 0, right = n;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (mid * mid == n) {
                return true;
            }
            if (mid * mid > n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return false;
    }
}
