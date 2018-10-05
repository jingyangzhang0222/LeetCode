/*
* You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

Example:

Input: s = "++++"
Output:
[
  "--++",
  "+--+",
  "++--"
]
Note: If there is no valid move, return an empty list [].

    20181004
    293
    easy
    O(n)
    O(1)
* */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class FlipGame {
    public List<String> generatePossibleNextMoves(String s) {
        char[] text = s.toCharArray();
        List<String> res = new ArrayList<>();
        for (int i = 0; i < text.length; i++) {
            if (i > 0 && text[i] == '+' && text[i - 1] == '+') {
                text[i - 1] = '-';
                text[i] = '-';
                res.add(new String(text));
                text[i] = '+';
                text[i - 1] = '+';
            }
        }
        return res;
    }
}
