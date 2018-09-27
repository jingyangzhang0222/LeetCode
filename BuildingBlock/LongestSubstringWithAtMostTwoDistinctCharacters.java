/*
* Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.
Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.

    20180924
    159
    hard
    O(n)
    O(1)
* */
package leetcode.BuildingBlock;

public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String t) {
        int max = 0, s = 0, kindCnt = 0;
        int[] map = new int[58];
        for (int f = 0; f < t.length(); f++) {
            if (map[t.charAt(f) - 'A']++ == 0) {
                kindCnt++;
            }
            while (kindCnt > 2) {
                if (--map[t.charAt(s++) - 'A'] == 0) {
                    kindCnt--;
                }
            }
            max = Math.max(max, f - s + 1);
        }
        return max;
    }
}
