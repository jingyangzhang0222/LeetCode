/*
* A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

Example 1:

Input:  "69"
Output: true
Example 2:

Input:  "88"
Output: true
Example 3:

Input:  "962"
Output: false

    20181018
    246
    easy
    O(n)
    o(1)
* */
package leetcode.Math;

public class StrobogrammaticNumber {
    public boolean isStrobogrammatic(String num) {
        // 0 0
        // 1 1
        // 8 8
        // 6 9
        int left = 0, right = num.length() - 1;
        while (left <= right) {
            char l = num.charAt(left);
            char r = num.charAt(right);
            left++;
            right--;
            if (l == '0' && r == '0') {
                continue;
            }
            if (l == '1' && r == '1') {
                continue;
            }
            if (l == '8' && r == '8') {
                continue;
            }
            if (l == '6' && r == '9') {
                continue;
            }
            if (l == '9' && r == '6') {
                continue;
            }
            return false;
        }

        return true;
    }
}
