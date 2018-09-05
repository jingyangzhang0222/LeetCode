/*
* Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

Examples:
Input: S = "a1b2"
Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

Input: S = "3z4"
Output: ["3z4", "3Z4"]

Input: S = "12345"
Output: ["12345"]
Note:

S will be a string with length at most 12.
S will consist only of letters or digits.

    20180809
    784
    easy
    O(2^l)
    O(len)
* */
package leetcode.NonGraphDFS;

import java.util.ArrayList;
import java.util.List;

public class LetterCasePermutation {
    public List<String> letterCasePermutation(String S) {
        List<String> sol = new ArrayList<>();
        char[] text = S.toCharArray();
        dfs(sol, text, 0);
        return sol;
    }

    private void dfs(List<String> sol, char[] text, int index) {
        if (index == text.length) {
            sol.add(new String(text));
            return;
        }

        if (Character.isLetter(text[index])) {
            exchange(text, index);
            dfs(sol, text, index + 1);
            exchange(text, index);

            dfs(sol, text, index + 1);
        } else {
            dfs(sol, text, index + 1);
        }
    }

    private void exchange(char[] text, int index) {
        if (Character.isLowerCase(text[index])) {
            text[index] = (char) (text[index] - 32);
        } else {
            text[index] = (char) (text[index] + 32);
        }
    }
}
