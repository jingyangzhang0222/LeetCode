/*
* Given a string S of digits, such as S = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].

Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:

0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
F.length >= 3;
and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.

Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.

Example 1:

Input: "123456579"
Output: [123,456,579]
Example 2:

Input: "11235813"
Output: [1,1,2,3,5,8,13]
Example 3:

Input: "112358130"
Output: []
Explanation: The task is impossible.
Example 4:

Input: "0123"
Output: []
Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not valid.
Example 5:

Input: "1101111"
Output: [110, 1, 111]
Explanation: The output [11, 0, 11, 11] would also be accepted.
Note:

1 <= S.length <= 200
S contains only digits.

    20181126
    842
    medium
    O()
    O()
* */
package leetcode.NonGraphDFS;

import java.util.ArrayList;
import java.util.List;

public class SplitArrayIntoFibonacciSequence {
    List<Integer> sol = new ArrayList<>();

    public List<Integer> splitIntoFibonacci(String s) {
        dfs(s, 1, 0, null);
        return sol;
    }

    private boolean dfs(String s, int index, int start, String expected) {
        if (start >= s.length()) {
            return start == s.length() && index > 3;
        }

        if (index <= 2) {
            int prevNum = index == 1 ? 0 : sol.get(0);
            if (s.charAt(start) == '0') {
                sol.add(0);
                if (dfs(s, index + 1, start + 1, String.valueOf(prevNum))) {
                    return true;
                }
                sol.remove(sol.size() - 1);
                return false;
            }

            int curNum = 0;
            for (int i = start; i < s.length(); i++) {
                curNum = 10 * curNum + s.charAt(i) - '0';
                String expecting = String.valueOf(prevNum + curNum);
                sol.add(curNum);
                if (dfs(s, index + 1, i + 1, expecting)) {
                    return true;
                }
                sol.remove(sol.size() - 1);
            }
            return false;
        } else {
            if (s.charAt(start) == '0') {
                if (!"0".equals(expected)) {
                    return false;
                } else {
                    sol.add(0);
                    if (dfs(s, index + 1, start + 1, expected)) {
                        return true;
                    }
                    sol.remove(sol.size() - 1);
                    return false;
                }
            }

            if (s.length() - start < expected.length()) return false;
            for (int i = 0; i < expected.length(); i++) {
                if (expected.charAt(i) != s.charAt(start + i)) {
                    return false;
                }
            }
            int curNum = Integer.parseInt(expected);
            int prevNum = sol.get(sol.size() - 1);

            String expecting = String.valueOf(curNum + prevNum);
            sol.add(curNum);
            if (dfs(s, index + 1, start + expected.length(), expecting)) {
                return true;
            }
            sol.remove(sol.size() - 1);
            return false;
        }
    }
}
