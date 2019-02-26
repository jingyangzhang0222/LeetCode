/*
* Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions ) so that the resulting parentheses string is valid.

Formally, a parentheses string is valid if and only if:

It is the empty string, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.



    20190223
    921
    medium
    O(n)
    O(1)
* */
package leetcode.Stack;

public class MinimumAddToMakeParenthesesValid {
    public int minAddToMakeValid(String S) {
        int left = 0, invalidRight = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(') {
                left++;
            } else {
                if (left > 0) {
                    left--;
                } else {
                    invalidRight++;
                }
            }
        }

        return left + invalidRight;
    }
}
