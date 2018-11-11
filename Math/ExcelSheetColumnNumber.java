/*
* Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
    ...
Example 1:

Input: "A"
Output: 1
Example 2:

Input: "AB"
Output: 28
Example 3:

Input: "ZY"
Output: 701

    20181110
    171
    easy
    O(n)
    O(1)
* */
package leetcode.Math;

public class ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        // A -> 1
        // ...
        // Z -> 26
        // AA -> 27
        // ...
        // AZ -> 52
        // BA -> 53
        // ...
        // ZZ -> 26 * 26 + 26 = 27 * 26
        // AAA -> 27 * 26 + 1
        //   1
        //  26
        //26 * 26
        int res = 0;
        int base = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            res += base * (s.charAt(i) - 'A' + 1);
            base *= 26;
        }
        return res;
    }
}
