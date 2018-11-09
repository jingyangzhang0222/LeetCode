/*
* Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.

If there is no answer, return the empty string.
Example 1:
Input:
words = ["w","wo","wor","worl", "world"]
Output: "world"
Explanation:
The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
Example 2:
Input:
words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
Output: "apple"
Explanation:
Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
Note:

All the strings in the input will only contain lowercase letters.
The length of words will be in the range [1, 1000].
The length of words[i] will be in the range [1, 30].

    20181106
    720
    easy
    O()
    O()
* */
package leetcode.Trie;

public class LongestWordInDictionary {
    private TrieNode root;
    private String res = "";

    public String longestWord(String[] words) {
        root = new TrieNode();

        for (String word : words) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
                if (i == word.length() - 1) {
                    cur.isTail = true;
                }
            }
        }

        TrieNode cur = root;
        dfs(cur, new StringBuilder());
        return res;
    }

    private void dfs(TrieNode cur, StringBuilder sb) {
        if (cur == null) {
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (cur.children[i] != null && cur.children[i].isTail) {
                sb.append((char)(i + 'a'));
                if (sb.length() > res.length()) {
                    res = sb.toString();
                }
                dfs(cur.children[i], sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    static class TrieNode{
        TrieNode[] children;
        boolean isTail;

        TrieNode() {
            children = new TrieNode[26];
        }
    }
}
