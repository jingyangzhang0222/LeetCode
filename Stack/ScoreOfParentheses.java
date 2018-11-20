/*
* Given a balanced parentheses string S, compute the score of the string based on the following rule:

() has score 1
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.


Example 1:

Input: "()"
Output: 1
Example 2:

Input: "(())"
Output: 2
Example 3:

Input: "()()"
Output: 2
Example 4:

Input: "(()(()))"
Output: 6


Note:

S is a balanced parentheses string, containing only ( and ).
2 <= S.length <= 50


    20181118
    856
    medium
    O(n)
    O(n)
* */
package leetcode.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class ScoreOfParentheses {
    public static void main(String[] args) {
        System.out.println(new ScoreOfParentheses().scoreOfParentheses("(()(()))"));
    }

    public int scoreOfParentheses(String S) {
        // 0, 3
        // ( ( ) ( ( ) ) )
        Deque<Integer> s = new ArrayDeque<>();
        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '(') {
                if (i == 0 || S.charAt(i - 1) == '(') {
                    // new level
                    s.offerLast(0);
                }
            } else {
                if (S.charAt(i - 1) == '(') {
                    // same level
                    s.offerLast(s.pollLast() + 1);
                } else {
                    s.offerLast(s.pollLast() * 2 + s.pollLast());
                }
            }
        }
        return s.pollLast();
    }
}
