/*
* Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:

Input: a = "11", b = "1"
Output: "100"
Example 2:

Input: a = "1010", b = "1011"
Output: "10101"

    20180715
    67
    easy
    O(max(m, n))
    O(?)
* */
package leetcode.Math;

public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int aIdx = a.length() - 1;
        int bIdx = b.length() - 1;
        int carry = 0;
        while (aIdx >= 0 || bIdx >= 0 || carry != 0) {
            int aBit = aIdx >= 0 ? a.charAt(aIdx) - '0' : 0;
            int bBit = bIdx >= 0 ? b.charAt(bIdx) - '0' : 0;
            int num = aBit + bBit + carry;
            int bit = num % 2;
            carry = num > 1 ? 1 : 0;
            sb.append(bit);
            aIdx--;
            bIdx--;
        }
        return sb.reverse().toString();
    }
}
