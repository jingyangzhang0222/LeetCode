/*
*
*Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:

Input: "hello"
Output: "holle"
Example 2:

Input: "leetcode"
Output: "leotcede"
Note:
The vowels does not include the letter "y".

    20180908
    345
    easy
    O(n)
    O(1)
* */
package leetcode.String;

public class ReverseVowelsAfAString {
    public String reverseVowels(String s) {
        int left = 0;
        int right = s.length() - 1;
        char[] text = s.toCharArray();
        while (left < right) {
            boolean l = isVowel(text[left]);
            boolean r = isVowel(text[right]);
            if (!l && !r) {
                left++;
                right--;
            } else if (l && r) {
                swap(text, left++, right--);
            } else if (l) {
                right--;
            } else {
                left++;
            }
        }
        return new String(text);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
               c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    private void swap(char[] text, int left, int right) {
        char tmp = text[left];
        text[left] = text[right];
        text[right] = tmp;
    }
}
