/*
* Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital if it has more than one letter, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.
Example 1:
Input: "USA"
Output: True
Example 2:
Input: "FlaG"
Output: False
Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.

    20190826
    520
    easy
    O(n)
    O(1)
* */
package leetcode.Google;

public class DetectCapital {
    public boolean detectCapitalUse(String word) {
        boolean allLower = Character.isLowerCase(word.charAt(0));
        boolean allUpper = !allLower && word.length() >= 2 && Character.isUpperCase(word.charAt(1));
        for (int i = 1; i < word.length(); i++) {
            if (Character.isUpperCase(word.charAt(i))) {
                if (allLower || (!allLower && !allUpper)) {
                    return false;
                }
            } else {
                if (allUpper) {
                    return false;
                }
            }
        }
        return true;
    }
}
