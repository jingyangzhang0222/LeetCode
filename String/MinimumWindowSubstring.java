/*
* Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

    20180627
    76
    hard
    sliding window
    O(n)
    O(256)
* */
package leetcode.String;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        MinimumWindowSubstring test = new MinimumWindowSubstring();
        System.out.println(test.minWindow(s, t));
    }

    public String minWindow(String s, String t) {
        int[] mapT = new int[256];
        int typeCount = 0;
        for (int i = 0; i < t.length(); i++) {
            if (mapT[t.charAt(i)]++ == 0) {
                typeCount++;
            }
        }
        int slow = 0;
        int count = 0;
        int start = -1;
        int[] mapS = new int[256];
        int globalMin = Integer.MAX_VALUE;
        for (int fast = 0; fast < s.length(); fast++) {
            if (++mapS[s.charAt(fast)] == mapT[s.charAt(fast)]) {
                count++;
            }
            while (count == typeCount) {
                if (fast - slow + 1 < globalMin) {
                    globalMin = fast - slow + 1;
                    start = slow;
                }
                if (--mapS[s.charAt(slow)] < mapT[s.charAt(slow++)]) {
                    count--;
                }
            }
        }
        return globalMin == Integer.MAX_VALUE ? "" : s.substring(start, start + globalMin);
    }
}
