/*
* Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and left the other as original.
Example:
Input: s = "abcdefg", k = 2
Output: "bacdfeg"
Restrictions:
The string consists of lower English letters only.
Length of the given string and k will in the range [1, 10000]

    20181110
    541
    easy
    O(n)
    O(1)
* */
package leetcode.String;

public class ReverseStringII {
    public String reverseStr(String str, int k) {
        int s = 0, f = Math.min(2 * k - 1, str.length() - 1);
        char[] text = str.toCharArray();
        while (f < text.length) {
            int charsLeft = f - s + 1;
            if (charsLeft >= 2 * k) {
                reverse(text, s, s + k - 1);
                s += 2 * k;
                f = Math.min(text.length - 1, f + 2 * k);
            } else if (k <= charsLeft && charsLeft < 2 * k) {
                reverse(text, s, s + k - 1);
                break;
            } else {
                reverse(text, s, text.length - 1);
                break;
            }
        }
        return new String(text);
    }

    private void reverse(char[] text, int s, int f) {
        while (s < f) {
            char tmp = text[s];
            text[s] = text[f];
            text[f] = tmp;
            s++;
            f--;
        }
    }
}
