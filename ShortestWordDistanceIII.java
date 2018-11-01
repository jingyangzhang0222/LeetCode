/*
* Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

Example:
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Input: word1 = “makes”, word2 = “coding”
Output: 1
Input: word1 = "makes", word2 = "makes"
Output: 3
Note:
You may assume word1 and word2 are both in the list.

    20181031
    245
    medium
    O(# of chars)
    O(1)
* */
package leetcode;

public class ShortestWordDistanceIII {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int index1 = -1, index2 = -1;
        int min = Integer.MAX_VALUE;
        boolean same = word1.equals(word2);
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                if (index2 != -1 && i - index2 < min) {
                    min = i - index2;
                }
                index1 = i;
                if (same) index2 = i;
            } else if (words[i].equals(word2)) {
                if (index1 != -1 && i - index1 < min) {
                    min = i - index1;
                }
                index2 = i;
            }
        }

        return min;
    }
}
