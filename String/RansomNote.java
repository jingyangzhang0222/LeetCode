/*
* Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true

    20181018
    383
    easy
    O(n + m)
    O(1)
* */
package leetcode.String;

public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        // 1: does not contain char that is not in magzine
        // 2: for chars in the magzine, the count...
        int[] map = new int[128];
        for (int i = 0; i < magazine.length(); i++) {
            map[magazine.charAt(i)]++;
        }
        for (int i = 0; i < ransomNote.length(); i++) {
            if (--map[ransomNote.charAt(i)] < 0) {
                return false;
            }
        }
        return true;
    }
}
