/*
* Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.



Example:

Input:
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation:
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"),
and that "hit" isn't the answer even though it occurs more because it is banned.


Note:

1 <= paragraph.length <= 1000.
1 <= banned.length <= 100.
1 <= banned[i].length <= 10.
The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
There are no hyphens or hyphenated words.
Words only consist of letters, never apostrophes or other punctuation symbols.

    20181115
    819
    easy
    O(n)
    O(n)
* */
package leetcode.Hash;

import java.util.HashMap;
import java.util.Map;

public class MostCommonWord {
    public String mostCommonWord(String p, String[] banned) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : banned) {
            map.put(word, -1);
        }

        int max = 0;
        String res = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= p.length(); i++) {
            char c = i == p.length() ? ' ' : p.charAt(i);
            if (Character.isLowerCase(c)) {
                sb.append(c);
                continue;
            } else if (Character.isUpperCase(c)) {
                sb.append((char)(c + 32));
                continue;
            }
            if (sb.length() == 0) continue;
            String newWord = sb.toString();
            sb.setLength(0);
            int fre = map.getOrDefault(newWord, 0);
            if (fre == -1) continue;
            map.put(newWord, ++fre);
            if (fre > max) {
                max = fre;
                res = newWord;
            }
        }

        return res;
    }
}
