/*
* Write a function to generate the generalized abbreviations of a word.

Note: The order of the output does not matter.

Example:

Input: "word"
Output:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]


    20181013
    320
    medium
    O(n!)
    O(n)
* */
package leetcode.NonGraphDFS;

import java.util.ArrayList;
import java.util.List;

public class GeneralizedAbbreviation {
    public List<String> generateAbbreviations(String word) {
        List<String> sol = new ArrayList<>();
        dfs(word, sol, 0, false, new StringBuilder());
        return sol;
    }

    private void dfs(String word, List<String> sol, int index, boolean prevIsNumber, StringBuilder sb) {
        if (index == word.length()) {
            sol.add(sb.toString());
            return;
        }

        for (int i = word.length() - index; i >= 1; i--) {
            if (!prevIsNumber) {
                int prevLength = sb.length();
                sb.append(i);
                dfs(word, sol, index + i, true, sb);
                sb.delete(prevLength, sb.length());////////////////////
            }
        }

        sb.append(word.charAt(index));
        dfs(word, sol, index + 1, false, sb);
        sb.deleteCharAt(sb.length() - 1);
    }
}
