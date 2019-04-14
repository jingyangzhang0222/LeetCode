/*
* Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.

    20190317
    395
    medium
    O(26n)
    O(26)
* */
package leetcode.SlidingWindow;

import java.util.Arrays;

public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        int[] count = new int[26];
        int max = 0;
        for (int typeBound = 1; typeBound <= 26; typeBound++) {
            Arrays.fill(count, 0);
            int left = 0, right = 0;
            int typeCount = 0;
            int validCount = 0;
            while (right < s.length()) {
                if (typeCount <= typeBound) {
                    char c = s.charAt(right);
                    count[c - 'a']++;
                    if (count[c - 'a'] == 1) {
                        typeCount++;
                    }
                    if (count[c - 'a'] == k) {
                        validCount++;
                    }
                    right++;
                } else {
                    char c = s.charAt(left);
                    count[c - 'a']--;
                    if (count[c - 'a'] == 0) {
                        typeCount--;
                    }
                    if (count[c - 'a'] == k - 1) {
                        validCount--;
                    }
                    left++;
                }

                if (typeCount == typeBound && typeCount == validCount) {
                    max = Math.max(max, right - left);
                }
            }
        }

        return max;
    }
}
