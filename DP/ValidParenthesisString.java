/*
* Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

Any left parenthesis '(' must have a corresponding right parenthesis ')'.
Any right parenthesis ')' must have a corresponding left parenthesis '('.
Left parenthesis '(' must go before the corresponding right parenthesis ')'.
'*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
An empty string is also valid.
Example 1:
Input: "()"
Output: True
Example 2:
Input: "(*)"
Output: True
Example 3:
Input: "(*))"
Output: True
Note:
The string size will be in the range [1, 100].

    20190108
    678
    medium
    O(n)
    O(1)
* */
package leetcode.DP;

public class ValidParenthesisString {
    public boolean checkValidString(String s) {
        int max = 0; // max # of left waiting to be matched
        int min = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                max++;
                min++;
            } else if (c == ')') {
                max--;
                min = min == 0 ? 0 : min - 1;
            } else {
                max++;
                min = min == 0 ? 0 : min - 1;
            }

            if (max < 0) {
                return false;
            }
        }

        return min == 0;
    }
}
