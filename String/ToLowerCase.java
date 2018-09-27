/*
* Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.



Example 1:

Input: "Hello"
Output: "hello"
Example 2:

Input: "here"
Output: "here"
Example 3:

Input: "LOVELY"
Output: "lovely"

    20180926
    709
    easy
    O(n)
    O(1)
* */
package leetcode.String;

public class ToLowerCase {
    public String toLowerCase(String str) {
        char[] s = str.toCharArray();
        for (int i = 0; i < s.length; i++) {
            s[i] = Character.isUpperCase(s[i]) ? (char)(s[i] + 32) : s[i];
        }
        return new String(s);
    }
}
