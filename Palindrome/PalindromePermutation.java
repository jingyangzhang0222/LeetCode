/*
* Given a string, determine if a permutation of the string could form a palindrome.

Example 1:

Input: "code"
Output: false
Example 2:

Input: "aab"
Output: true
Example 3:

Input: "carerac"
Output: true

    20181004
    266
    easy
    O(n)
    O(1)
* */
package leetcode.Palindrome;

public class PalindromePermutation {
    public boolean canPermutePalindrome(String s) {
        int[] map = new int[256];
        int odd = 0;
        for (int i = 0; i < s.length(); i++) {
            if (++map[s.charAt(i)] % 2 == 1) {
                odd++;
            } else {
                odd--;
            }
        }
        return odd <= 1;
    }
}
