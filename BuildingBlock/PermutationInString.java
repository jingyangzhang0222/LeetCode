/*
* Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of the first string's permutations is the substring of the second string.
Example 1:
Input:s1 = "ab" s2 = "eidbaooo"
Output:True
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:
Input:s1= "ab" s2 = "eidboaoo"
Output: False
Note:
The input strings only contain lower case letters.
The length of both given strings is in range [1, 10,000].

    20181105
    567
    medium
    O(n)
    O(1)
* */
package leetcode.BuildingBlock;

public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        int[] map = new int[26];
        int type = 0;
        for (char c : s1.toCharArray()) {
            if (map[c - 'a']++ == 0) {
                type++;
            }
        }

        int matched = 0;
        for (int f = 0; f < s2.length(); f++) {
            if (--map[s2.charAt(f) - 'a'] == 0) {
                matched++;
            }
            if (matched == type) {
                return true;
            }
            if (f >= s1.length() - 1 && map[s2.charAt(f - s1.length() + 1) - 'a']++ == 0) {
                matched--;
            }
        }

        return false;
    }
}
