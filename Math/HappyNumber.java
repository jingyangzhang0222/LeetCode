/*
* Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example:

Input: 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

    20180910
    202
    easy
    O(?)
    O(1)
* */
package leetcode.Math;

public class HappyNumber {
    public boolean isHappy(int n) {
        int x = n, y = n;
        while (x != 1) {
            x = transform(x);
            y = transform(transform(y));
            if (x == 1 || y == 1) {
                return true;
            }
            if (x == y) {
                return false;
            }
        }
        return true;
    }

    private int transform(int n) {
        int res = 0;
        while (n != 0) {
            res += (n % 10) * (n % 10);
            n /= 10;
        }
        return res;
    }
}
