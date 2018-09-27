/*
* Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?

    20180917
    242
    easy
    O(m + n)
    O(1)
* */
package leetcode.String;

public class ValidAnagram {
    class Solution {
        public boolean isAnagram(String s, String t) {
            int[] map = new int[256];
            int cnt = 0;
            for (char c : s.toCharArray()) {
                if (map[c]++ == 0) {
                    cnt++;
                }
            }

            for (char c : t.toCharArray()) {
                if (--map[c] == 0) {
                    cnt--;
                }
                if (map[c] < 0) {
                    return false;
                }
            }

            return cnt == 0;
        }
    }
}
