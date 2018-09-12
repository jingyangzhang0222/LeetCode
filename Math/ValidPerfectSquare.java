/*
* Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Output: true
Example 2:

Input: 14
Output: false

    20180906
    367
    easy
    O(logn)
    O(1)
* */
package leetcode.Math;

public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        int lo = 1;
        int hi = num;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid > num) {
                hi = (int)mid - 1;
            } else {
                lo = (int)mid + 1;
            }
        }
        // lo > hi
        return false;
    }
}
