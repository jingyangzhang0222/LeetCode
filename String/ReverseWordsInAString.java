/*
* Given an input string, reverse the string word by word.

Example:

Input: "the sky is blue",
Output: "blue is sky the".
Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
Follow up: For C programmers, try to solve it in-place in O(1) space.

    20181018
    151
    medium
    O(n)
    O(1)
* */
package leetcode.String;

public class ReverseWordsInAString {
    public  static void main(String[] args) {
        System.out.print(new ReverseWordsInAString().reverseWords(" 1 "));
    }
    public String reverseWords(String S) {
        char[] text = S.toCharArray();
        int s = 0;

        // reverse only words
        for (int f = 0; f < text.length; f++) {
            boolean isStartOfAWord = text[f] != ' ' && (f == 0 || text[f - 1] == ' ');
            boolean isEndOfAWord = text[f] != ' ' && (f == text.length -1 || text[f + 1] == ' ');
            if (isStartOfAWord) {
                s = f;
            } else if (isEndOfAWord) {
                reverse(text, s, f);
            }
        }

        //reverse the whole sentence
        reverse(text, 0, text.length - 1);

        // clean spaces
        s = 0;
        for (int f = 0; f < text.length; f++) {
            if (text[f] != ' ') {
                // 1. avoid trailing zeros, add a space, then a char only when encounter a new word
                // 2. avoid leading zeros, when s == 0, don't add a space, only char
                // 3. redundant zeroes between words: ' ' + a char
                // (4) s cannot be out of boundary
                if (s == 0 || text[f - 1] != ' ') {
                    text[s++] = text[f];
                } else {
                    // s != 0 && text[f - 1] == ' '
                    // first word has been added, don't worry about leading zeros
                    // this is the start of a new word, ' ' + a char
                    text[s++] = ' ';
                    text[s++] = text[f];
                }
            }
        }

        return new String(text, 0, s);
    }

    private void reverse(char[] text, int s, int f) {
        while (s < f) {
            char tmp = text[s];
            text[s++] = text[f];
            text[f--] = tmp;
        }
    }
}
