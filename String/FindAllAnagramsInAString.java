/*
* Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

    20180622
    438
    easy
    sliding window
    O(n)
    O(1)
* */
package leetcode.String;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String t, String p) {
        int[] map = new int[26];
        int typeCount = 0;
        for (int i = 0; i < p.length(); i++) {
            if (map[p.charAt(i) - 'a']++ == 0) {
                typeCount++;
            }
        }
        List<Integer> sol = new ArrayList<>();
        int zeroCount = 0;
        int s = 0;
        for (int f = 0; f < t.length(); f++) {
            if (--map[t.charAt(f) - 'a'] == 0) {
                zeroCount++;
            }
            if (zeroCount == typeCount) {
                sol.add(s);
            }
            if (f - s + 1 == p.length() && map[t.charAt(s++) - 'a']++ == 0) {
                zeroCount--;
            }
        }
        return sol;
    }
}
