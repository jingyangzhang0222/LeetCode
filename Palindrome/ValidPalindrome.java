/*
* Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Note: For the purpose of this problem, we define empty string as valid palindrome.

Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Example 2:

Input: "race a car"
Output: false

    20181017
    125
    easy
    O(n)
    O(1)
* */
package leetcode.Palindrome;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            boolean leftIsDigit = Character.isDigit(s.charAt(left));
            boolean leftIsLetter = Character.isLetter(s.charAt(left));
            boolean rightIsDigit = Character.isDigit(s.charAt(right));
            boolean rightIsLetter = Character.isLetter(s.charAt(right));
            if (!leftIsDigit && !leftIsLetter) {
                left++;
            } else if (!rightIsDigit && !rightIsLetter) {
                right--;
            } else {
                if (leftIsDigit && rightIsLetter) {
                    return false;
                }
                if (leftIsLetter && rightIsDigit) {
                    return false;
                }
                int leftChar = (int) s.charAt(left);
                int rightChar = (int) s.charAt(right);
                if (leftChar != rightChar && Math.abs(leftChar - rightChar) != 32) {
                    return false;
                }
                left++;
                right--;
            }
        }
        return true;
    }
}
