/*
* Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.

    20181018
    557
    easy
    O(n)
    O(1)
* */
package leetcode.String;

public class ReverseWordsInAStringIII {
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
        //reverse(text, 0, text.length - 1);
        return new String(text);
    }

    private void reverse(char[] text, int s, int f) {
        while (s < f) {
            char tmp = text[s];
            text[s++] = text[f];
            text[f--] = tmp;
        }
    }
}
