/*
* Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]

    20181109
    301
    hard
    O(?)
    O()
*
* */
package leetcode.NonGraphDFS;

import java.util.ArrayList;
import java.util.List;

public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        new RemoveInvalidParentheses().removeInvalidParentheses("())");
    }

    public List<String> removeInvalidParentheses(String s) {
        List<String> sol = new ArrayList<>();
        char[] t = s.toCharArray();
        int left = 0, right = 0, reR = 0;
        for (char c : t) {
            if (c == '(') {
                left++;
            } else if (c == ')') {
                right++;
                if (right > left) {
                    reR++;
                    right--;
                }
            }
        }
        int reL = left - right;
        dfs(sol, t, reL, reR, 0);
        return sol;
    }

    private void dfs(List<String> sol, char[] t, int reL, int reR, int start) {
        if (reL == 0 && reR == 0) {
            if (valid(t)) {
                StringBuilder sb = new StringBuilder();
                for (char c : t) {
                    if (c != ' ') {
                        sb.append(c);
                    }
                }
                sol.add(sb.toString());
            }
            return;
        }
        // goal: delete one char
        //
        for (int i = start; i < t.length; i++) {
            if ((t[i] != '(' && t[i] != ')') || (i != start && t[i] == t[i - 1])) {
                continue;
            }

            if (reR > 0 && t[i] == ')') {
                t[i] = ' ';
                dfs(sol, t, reL, reR - 1, i + 1);
                t[i] = ')';
            } else if (reL > 0 && t[i] == '(') {
                t[i] = ' ';
                dfs(sol, t, reL - 1, reR, i + 1);
                t[i] = '(';
            }
        }
    }

    private boolean valid(char[] t) {
        int left = 0, right = 0;
        for (int i = 0; i < t.length; i++) {
            if (t[i] == '(') {
                left++;
            } else if (t[i] == ')') {
                right++;
            }

            if (right > left) {
                return false;
            }
        }
        return left == right;
    }
}
