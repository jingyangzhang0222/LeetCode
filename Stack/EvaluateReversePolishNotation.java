/*
* Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, /. Each operand may be an integer or another expression.

Note:

Division between two integers should truncate toward zero.
The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
Example 1:

Input: ["2", "1", "+", "3", "*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: ["4", "13", "5", "/", "+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
Output: 22
Explanation:
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22

    20181023
    150
    medium
    O(n)
    O(n)
* */
package leetcode.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Deque<Integer> s = new ArrayDeque<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                s.offerLast(s.pollLast() + s.pollLast());
            } else if (token.equals("*")) {
                s.offerLast(s.pollLast() * s.pollLast());
            } else if (token.equals("-")) {
                s.offerLast(-s.pollLast() + s.pollLast());
            } else if (token.equals("/")) {
                int num2 = s.pollLast();
                int num1 = s.pollLast();
                s.offerLast(num1 / num2);
            } else {
                s.offerLast(atoi(token));
            }
        }
        return s.pollLast();
    }

    private int atoi(String s) {
        boolean positive = true;
        int res = 0;
        for (char c : s.toCharArray()) {
            if (c == '-') {
                positive = false;
                continue;
            }
            res = 10 * res + c - '0';
        }
        return positive ? res : -res;
    }
}
