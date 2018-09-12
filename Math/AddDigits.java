/*
* Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

Example:

Input: 38
Output: 2
Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2.
             Since 2 has only one digit, return it.
Follow up:
Could you do it without any loop/recursion in O(1) runtime?

    20180911
    258
    easy
    O(?)
    O(1)
* */
package leetcode.Math;

public class AddDigits {
    public int addDigits(int num) {
        while (num >= 10) {
            num = transform(num);
        }
        return num;
    }

    private int transform(int num) {
        int res = 0;
        while (num > 0) {
            res += (num % 10);
            num /= 10;
        }
        return res;
    }
}
