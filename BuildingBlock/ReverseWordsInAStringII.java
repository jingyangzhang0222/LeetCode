/*
* Given an input string , reverse the string word by word.

Example:

Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
Note:

A word is defined as a sequence of non-space characters.
The input string does not contain leading or trailing spaces.
The words are always separated by a single space.
Follow up: Could you do it in-place without allocating extra space?

    20181111
    186
    medium
    O(n)
    O(1)
* */
package leetcode.BuildingBlock;

public class ReverseWordsInAStringII {
    public void reverseWords(char[] t) {
        int s = 0;
        for (int f = 1; f <= t.length; f++) {
            if (f == t.length || t[f] == ' ') {
                reverse(t, s, f - 1);
                s = f + 1;
            }
        }
        reverse(t, 0, t.length - 1);
    }

    private void reverse(char[] t, int s, int f) {
        while (s < f) {
            char tmp = t[s];
            t[s] = t[f];
            t[f] = tmp;
            s++;
            f--;
        }
    }
}
