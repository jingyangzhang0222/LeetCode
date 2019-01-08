/*
* Given an integer, return its base 7 string representation.

Example 1:
Input: 100
Output: "202"
Example 2:
Input: -7
Output: "-10"
Note: The input will be in range of [-1e7, 1e7].

    20180103
    504
    easy
    O(logn)
    O(1)
* */
package leetcode.BuildingBlock;

public class BaseSeven {
    public String convertToBase7Iterative(int num) {
        StringBuilder sb = new StringBuilder();
        if (num < 0) {
            sb.append('-');
            num = -num;
        }

        int base = 1;
        while (num >= base * 7) {
            base *= 7;
        }

        while (base > 1) {
            int dig = num / base;
            sb.append(dig);
            num -= dig * base;
            base /= 7;
        }

        sb.append(num);
        return sb.toString();
    }

    public String convertToBase7(int num) {
        if (num < 0) {
            return '-' + convertToBase7(-num);
        }
        if (num < 7) {
            return String.valueOf(num);
        }
        return convertToBase7(num / 7) + num % 7;
    }
}
