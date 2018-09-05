/*
* Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2
Example 2:

Input: " 2-1 + 2 "
Output: 3
Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23
Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.

    20180904
    224
    hard
    O(n)
    O(1)
* */
package leetcode.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculator {
    public int calculate(String e) {
        Deque<Integer> s = new ArrayDeque<>();
        int res = 0;
        int sign = 1;
        int index = 0;
        while (index < e.length()) {
            if (Character.isDigit(e.charAt(index))) {
                int num = 0;
                while (index < e.length() && Character.isDigit(e.charAt(index))) {
                    num = 10 * num + e.charAt(index) - '0';
                    index++;
                }
                res += sign * num;
            } else if (e.charAt(index) == '+') {
                sign = 1;
                index++;
            } else if (e.charAt(index) == '-') {
                sign = -1;
                index++;
            } else if (e.charAt(index) == '(') {
                s.offerLast(res);
                s.offerLast(sign);
                res = 0;
                sign = 1;
                index++;
            } else if (e.charAt(index) == ')') {
                res = res * s.pollLast() + s.pollLast();
                index++;
            } else {
                index++;
            }
        }
        return res;
    }
}
