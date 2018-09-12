/*
*
Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

Note:

The length of both num1 and num2 is < 5100.
Both num1 and num2 contains only digits 0-9.
Both num1 and num2 does not contain any leading zero.
You must not use any built-in BigInteger library or convert the inputs to integer directly.

    20180911
    415
    easy
    O(m + n)
    O(1)
* */
package leetcode.String;

public class AddStrings {
    public String addStrings(String num1, String num2) {
        char[] res = new char[Math.max(num1.length(), num2.length())];
        int index = res.length - 1;
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        int carry = 0;
        while (index1 >= 0 || index2 >= 0) {
            int n1 = index1 >= 0 ? num1.charAt(index1) - '0' : 0;
            int n2 = index2 >= 0 ? num2.charAt(index2) - '0' : 0;
            int dig = (n1 + n2 + carry) % 10;
            carry = (n1 + n2 + carry) / 10;
            res[index] = (char)(dig + '0');
            index--;
            index1--;
            index2--;
        }

        return carry == 1 ? "1" + new String(res) : new String(res);
    }
}
