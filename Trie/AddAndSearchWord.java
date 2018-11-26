/*
* Design a data structure that supports the following two operations:

void addWord(word)
bool search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

Example:

addWord("bad")
addWord("dad")
addWord("mad")
search("pad") -> false
search("bad") -> true
search(".ad") -> true
search("b..") -> true
Note:
You may assume that all words are consist of lowercase letters a-z.

    20181125
    211
    medium
    add: O(n) search: O()
    O()
* */
package leetcode.Trie;

public class AddAndSearchWord {
    private TrieNode root;
    /** Initialize your data structure here. */
    public AddAndSearchWord() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }
        cur.isEnd = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if ("".equals(word)) return root.isEnd;
        return search(root, word, 0);
    }

    private boolean search(TrieNode cur, String word, int index) {
        char c = word.charAt(index);
        if (index == word.length() - 1) {
            if (word.charAt(index) == '.') {
                for (int child = 0; child < 26; child++) {
                    if (cur.children[child] != null && cur.children[child].isEnd) {
                        return true;
                    }
                }
                return false;
            } else {
                return cur.children[c - 'a'] != null && cur.children[c - 'a'].isEnd;
            }
        } else {
            if (word.charAt(index) == '.') {
                for (int child = 0; child < 26; child++) {
                    if (cur.children[child] != null && search(cur.children[child], word, index + 1)) {
                        return true;
                    }
                }
                return false;
            } else {
                if (cur.children[c - 'a'] == null) {
                    return false;
                } else {
                    return search(cur.children[c - 'a'], word, index + 1);
                }
            }
        }
    }

    private class TrieNode{
        TrieNode[] children;
        boolean isEnd;

        TrieNode() {
            children = new TrieNode[26];
        }
    }
}
