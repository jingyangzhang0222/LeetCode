/*
* Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
    ...
Example 1:

Input: 1
Output: "A"
Example 2:

Input: 28
Output: "AB"
Example 3:

Input: 701
Output: "ZY"

    20180629
    168
    easy
    O(log26_n)
    O(1)
* */
package leetcode.Math;

public class ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int bit = n % 26 == 0 ? 26 : n % 26;
            sb.append((char)(bit - 1 + 'A'));
            n = (n - 1) / 26;
        }
        return sb.reverse().toString();
    }
}
