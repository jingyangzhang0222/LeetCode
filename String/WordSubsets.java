/*
* We are given two arrays A and B of words.  Each word is a string of lowercase letters.

Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.  For example, "wrr" is a subset of "warrior", but is not a subset of "world".

Now say a word a from A is universal if for every b in B, b is a subset of a.

Return a list of all universal words in A.  You can return the words in any order.



Example 1:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
Output: ["facebook","google","leetcode"]
Example 2:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["l","e"]
Output: ["apple","google","leetcode"]
Example 3:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","oo"]
Output: ["facebook","google"]
Example 4:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["lo","eo"]
Output: ["google","leetcode"]
Example 5:

Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["ec","oc","ceo"]
Output: ["facebook","leetcode"]


Note:

1 <= A.length, B.length <= 10000
1 <= A[i].length, B[i].length <= 10
A[i] and B[i] consist only of lowercase letters.
All words in A[i] are unique: there isn't i != j with A[i] == A[j].

    20181127
    916
    medium
    O(# of chars)
    O(1)
* */
package leetcode.String;

import java.util.ArrayList;
import java.util.List;

public class WordSubsets {
    public List<String> wordSubsets(String[] A, String[] B) {
        List<String> res = new ArrayList<>();
        int[] map;
        int[] maxCount = new int[26];//////////////////
        for (String b : B) {
            map = new int[26];
            for (char c : b.toCharArray()) {
                map[c - 'a']++;
                if (map[c - 'a'] > maxCount[c - 'a']) {
                    maxCount[c - 'a'] = map[c - 'a'];
                }
            }
        }

        for (String a : A) {
            map = new int[26];
            for (char c : a.toCharArray()) {
                map[c - 'a']++;
            }
            boolean valid = true;
            for (int i = 0; i < 26; i++) {
                if (map[i] < maxCount[i]) {
                    valid = false;
                    break;
                }
            }
            if (valid) res.add(a);
        }

        return res;
    }
}
