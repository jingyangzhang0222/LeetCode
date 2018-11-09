/*
* Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".

Note:
Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.

Example 1:
Given s = "internationalization", abbr = "i12iz4n":

Return true.
Example 2:
Given s = "apple", abbr = "a2e":

Return false.

    20181101
    408
    easy
    O(n)
    O(1)
* */
package leetcode.String;

public class ValidWordAbbreviation {
    public boolean validWordAbbreviation(String word, String abbr) {
        int index1 = 0, index2 = 0;
        // w e e e k
        // 0 1 2 3 4
        // w 3 k
        // 0 1 2
        while (index1 < word.length() && index2 < abbr.length()) {
            if (Character.isDigit(abbr.charAt(index2))) {
                int startIndex = index2;
                int num = 0;
                while (index2 < abbr.length() && Character.isDigit(abbr.charAt(index2))) {
                    if (abbr.charAt(index2) == '0' && index2 == startIndex) {
                        return false;
                    }
                    num = 10 * num + abbr.charAt(index2) - '0';
                    index2++;
                }
                index1 += num;
            } else {
                if (word.charAt(index1) != abbr.charAt(index2)) {
                    return false;
                }
                index1++;
                index2++;
            }
        }

        return index1 == word.length() && index2 == abbr.length();
    }
}
