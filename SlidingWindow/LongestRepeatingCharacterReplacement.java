/*
* Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.

Note:
Both the string's length and k will not exceed 104.

Example 1:

Input:
s = "ABAB", k = 2

Output:
4

Explanation:
Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input:
s = "AABABBA", k = 1

Output:
4

Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.

    20190402
    424
    medium
    O(26 * n)
    O(1)
* */
package leetcode.SlidingWindow;

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        int globalMax = 0;
        for (int i = 0; i < 26; i++) {
            int max = 0;
            char c = (char)(i + 'A');
            int left = 0;
            int diffCount = 0;
            for (int right = 0; right < s.length(); right++) {
                if (s.charAt(right) != c) {
                    diffCount++;
                }
                while (diffCount > k) {
                    if (s.charAt(left++) != c) {
                        diffCount--;
                    }
                }
                max = Math.max(max, right - left + 1);
            }
            globalMax = Math.max(globalMax, max);
        }
        return globalMax;
    }
}
