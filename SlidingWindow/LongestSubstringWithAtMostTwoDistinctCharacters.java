/*
* Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.

    20180924
    340
    hard
    O(n)
    O(1)
* */
package leetcode.SlidingWindow;

public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String t, int k) {
        int max = 0, s = 0, kindCnt = 0;
        int[] map = new int[128];
        for (int f = 0; f < t.length(); f++) {
            if (map[t.charAt(f)]++ == 0) {
                kindCnt++;
            }
            while (kindCnt > k) {
                if (--map[t.charAt(s++)] == 0) {
                    kindCnt--;
                }
            }
            max = Math.max(max, f - s + 1);
        }
        return max;
    }
}
