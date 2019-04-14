/*
* Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard like the image below.






Example:

Input: ["Hello", "Alaska", "Dad", "Peace"]
Output: ["Alaska", "Dad"]


Note:

You may use one character in the keyboard more than once.
You may assume the input string will only contain letters of alphabet.

    20190411
    500
    easy
    O(n)
    O(n)
* */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class KeyboardRow {
    private static String[] lowerRows, upperRows;
    private static int[] lowerMap, upperMap;

    private KeyboardRow() {
        lowerRows = new String[]{"asdfghjkl", "zxcvbnm"};
        upperRows = new String[]{"ASDFGHJKL", "ZXCVBNM"};
        lowerMap = new int[26];
        upperMap = new int[26];
        for (int i = 0; i <= 1; i++ ) {
            for (int j = 0; j < lowerRows[i].length(); j++) {
                lowerMap[lowerRows[i].charAt(j) - 'a'] = i + 1;
                upperMap[upperRows[i].charAt(j) - 'A'] = i + 1;
            }
        }
    }

    public String[] findWords(String[] words) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            int firstRowNumber = Character.isUpperCase(word.charAt(0)) ?
                    upperMap[word.charAt(0) - 'A'] :
                    lowerMap[word.charAt(0) - 'a'];
            boolean valid = true;
            for (char c : word.toCharArray()) {
                int rowNumber = Character.isUpperCase(c) ? upperMap[c - 'A'] : lowerMap[c - 'a'];
                if (rowNumber != firstRowNumber) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                res.add(word);
            }
        }

        return res.toArray(new String[0]);
    }
}
