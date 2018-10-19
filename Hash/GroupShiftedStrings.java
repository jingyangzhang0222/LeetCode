/*
* Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

Example:

Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
Output:
[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]

    20181014
    249
    medium
    O(# of chars)
    O(# of chars)
* */
package leetcode.Hash;

import java.util.*;

public class GroupShiftedStrings {
    public static void main(String[] args) {
        Set<int[]> set = new HashSet<>();
        set.add(new int[]{1, 1});
        set.add(new int[]{1, 1});
        System.out.println(new int[]{1, 1}.hashCode());
    }

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String word : strings) {
            String key = "";
            for (int i = 1; i < word.length(); i++) {
                char prev = word.charAt(i - 1);
                char curr = word.charAt(i);

                int diff = (int)curr - (int)prev;
                if (diff < 0) diff = 26 + diff;
                key += diff;
            }
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(word);
        }
        List<List<String>> sol = new ArrayList<>();

        for (String key : map.keySet()) {
            sol.add(map.get(key));
        }
        return sol;
    }
}
