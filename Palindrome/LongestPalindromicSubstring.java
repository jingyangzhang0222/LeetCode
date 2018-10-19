/*
* Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"

    20181018
    5
    medium
    O(n ^ 2) -> ~O(n)
    O(1)
* */
package leetcode.Palindrome;

public class LongestPalindromicSubstring {
    private int start = 0;
    private int end = 0;
    private int maxLength = 0;

    public String longestPalindrome(String s) {
        for (int i = 0; i < s.length(); i++) {
            helper(s, i, i);
            helper(s, i, i + 1);
        }
        return s.substring(start, end);
    }

    private void helper(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (right - left + 1 > maxLength) {
                maxLength = right - left + 1;
                start = left;
                end = right + 1;
            }
            left--;
            right++;
        }
    }
}
