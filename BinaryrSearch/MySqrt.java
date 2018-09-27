/*
* Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since
             the decimal part is truncated, 2 is returned.

    20180926
    69
    easy
    O(log(Integer.MAX_VALUE))
    O(1)
* */
package leetcode.BinaryrSearch;

public class MySqrt {
    public int mySqrt(int x) {
        int lo = 1;
        int hi = Math.min(46340, x);
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (mid * mid <= x && (mid + 1) * (mid + 1) > x) {
                return mid;
            } else if (x / mid < mid) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return hi;
    }
}
