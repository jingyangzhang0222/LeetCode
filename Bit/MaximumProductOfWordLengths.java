/*
* Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0.

Example 1:

Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
Output: 16
Explanation: The two words can be "abcw", "xtfn".
Example 2:

Input: ["a","ab","abc","d","cd","bcd","abcd"]
Output: 4
Explanation: The two words can be "ab", "cd".
Example 3:

Input: ["a","aa","aaa","aaaa"]
Output: 0
Explanation: No such pair of words.

    20190409
    318
    medium
    O(# of chars + (# of words) ^ 2)
    O(# o words)
* */
package leetcode.Bit;

public class MaximumProductOfWordLengths {
    public int maxProduct(String[] words) {
        int[] signature = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                signature[i] |= 1 << (words[i].charAt(j) - 'a');
            }
        }
        int max = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                int lenthProduct = words[i].length() * words[j].length();
                if ((signature[i] & signature[j]) == 0 && lenthProduct > max) {
                    max = lenthProduct;
                }
            }
        }

        return max;
    }
}
