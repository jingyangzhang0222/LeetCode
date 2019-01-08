/*
* Given two strings representing two complex numbers.

You need to return a string representing their multiplication. Note i2 = -1 according to the definition.

Example 1:
Input: "1+1i", "1+1i"
Output: "0+2i"
Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
Example 2:
Input: "1+-1i", "1+-1i"
Output: "0+-2i"
Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
Note:

The input strings will not have extra blank.
The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. And the output should be also in this form.


    20181128
    537
    medium
    O(m + n)
    O(1)
* */
package leetcode.Math;

public class ComplexNumberMultiplication {
    public String complexNumberMultiply(String a, String b) {
        int indexOfPlusInA = a.indexOf("+");
        int indexOfPlusInB = b.indexOf("+");
        int aRe = atoi(a.substring(0, indexOfPlusInA));
        int aIm = atoi(a.substring(indexOfPlusInA + 1, a.length() - 1));
        int bRe = atoi(b.substring(0, indexOfPlusInB));
        int bIm = atoi(b.substring(indexOfPlusInB + 1, b.length() - 1));

        int resRe = aRe * bRe - aIm * bIm;
        int resIm = aRe * bIm + aIm * bRe;
        return String.valueOf(resRe) + "+" + String.valueOf(resIm) + 'i';
    }

    private int atoi(String s) {
        boolean negative = s.charAt(0) == '-';
        int start = negative ? 1 : 0;
        int res = 0;
        for (int i = start; i < s.length(); i++) {
            res = 10 * res + s.charAt(i) - '0';
        }
        return negative ? -res : res;
    }
}
