/*
* Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.



Example 1:

Input: A = "ab", B = "ba"
Output: true
Example 2:

Input: A = "ab", B = "ab"
Output: false
Example 3:

Input: A = "aa", B = "aa"
Output: true
Example 4:

Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true
Example 5:

Input: A = "", B = "aa"
Output: false


Note:

0 <= A.length <= 20000
0 <= B.length <= 20000
A and B consist only of lowercase letters.

    20181125
    859
    easy
    O(n)
    O(1)
* */
package leetcode.String;

public class BuddyStrings {
    public boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }

        // ? ? ? b ? a ?
        // ? ? ? a ? b ?
        int[] mapA = new int[26];
        int[] mapB = new int[26];
        boolean firstStringContainsDuplicate = false;
        int diffCount = 0;
        for (int i = 0; i < A.length(); i++) {
            if (++mapA[A.charAt(i) - 'a'] >= 2) {
                firstStringContainsDuplicate = true;
            }
            mapB[B.charAt(i) - 'a']++;
            if (A.charAt(i) != B.charAt(i)) {
                diffCount++;
            }

            if (diffCount > 2) {
                return false;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (mapA[i] != mapB[i]) {
                return false;
            }
        }
        if (diffCount == 1) return false;
        if (diffCount == 2) return true;
        return firstStringContainsDuplicate;
    }
}
