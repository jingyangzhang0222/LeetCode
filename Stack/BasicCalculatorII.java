/*
* Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5
Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.

    20181118
    227
    medium
    O(n)
    O(n)
* */
package leetcode.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculatorII {
    public int calculate(String s) {
        Deque<Integer> nums = new ArrayDeque<>();
        Deque<Character> op = new ArrayDeque<>();
        int index = 0;
        while (index < s.length()) {
            char c = s.charAt(index);
            if (c == ' ') {
                index++;
                continue;
            }

            if (Character.isDigit(c)) {
                int num = 0;
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    num = 10 * num + s.charAt(index) - '0';
                    index++;
                }
                if (!op.isEmpty() && (op.peekLast() == '/' || op.peekLast() == '*')) {
                    if (op.pollLast() == '*') {
                        nums.offerLast(nums.pollLast() * num);
                    } else {
                        nums.offerLast(nums.pollLast() / num);
                    }
                } else {
                    nums.offerLast(num);
                }
            } else {
                op.offerLast(c);
                index++;
            }
        }

        while (!op.isEmpty()) {
            if (op.pollFirst() == '+') {
                nums.offerFirst(nums.pollFirst() + nums.pollFirst());
            } else {
                nums.offerFirst(nums.pollFirst() - nums.pollFirst());
            }
        }
        return nums.pollLast();
    }

}
