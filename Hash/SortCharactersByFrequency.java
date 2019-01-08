/*
* Given a string, sort it in decreasing order based on the frequency of characters.

Example 1:

Input:
"tree"

Output:
"eert"

Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.
Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.

    20181127
    451
    medium
    O(n)
    O(1)
* */
package leetcode.Hash;

import java.util.Arrays;

public class SortCharactersByFrequency {
    public String frequencySort(String s) {
        int[][] map = new int[128][2];
        for (char c : s.toCharArray()) {
            map[c][0] = (int)c;
            map[c][1]++;
        }
        Arrays.sort(map, (c1, c2) -> c2[1] - c1[1]);
        char[] res = new char[s.length()];
        int index = 0;
        for (int[] count : map) {
            while (count[1]-- > 0) {
                res[index++] = (char)count[0];
            }
            if (index == res.length) break;
        }
        return new String(res);
    }
}
