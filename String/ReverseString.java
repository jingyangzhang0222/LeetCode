/*
* Write a function that takes a string as input and returns the string reversed.

Example 1:

Input: "hello"
Output: "olleh"
Example 2:

Input: "A man, a plan, a canal: Panama"
Output: "amanaP :lanac a ,nalp a ,nam A"

    20180810
    344
    easy
    O(n)
    O(1)
* */
package leetcode.String;

public class ReverseString {
    public String reverseString(String s) {
        if (s.length() == 0) {
            return s;
        }
        char[] text = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char tmp = text[left];
            text[left++] = text[right];
            text[right--] = tmp;
        }
        return new String(text);
    }
}
