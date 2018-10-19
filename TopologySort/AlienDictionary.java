/*
* There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
]

Output: ""

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

    20181012
    269
    hard
    O(# of chars)
    O(1)
* */
package leetcode.TopologySort;

import java.util.*;

public class AlienDictionary {
    public static void main(String[] args) {
        String[] words = new String[]{
                "z",
                "x",
                "c",
                "ca",
                "cab"
        };
        AlienDictionary test = new AlienDictionary();
        System.out.println(test.alienOrderV2(words));
    }

    public String alienOrderV2(String[] words) {
        int[] inDegree = new int[26];
        Arrays.fill(inDegree, -1);
        boolean[][] edges = new boolean[26][26];
        int cnt = 0;

        for (int i = 0; i < words.length; i++) {
            String preWord = i > 0 ? words[i - 1] : "";
            String curWord = words[i];

            boolean soFarPreWordIsValidPrefix = true;
            for (int j = 0; j < curWord.length(); j++) {
                char curChar = curWord.charAt(j);
                char preChar = j < preWord.length() ? preWord.charAt(j) : curChar;

                if (inDegree[curChar - 'a'] == -1) {
                    inDegree[curChar - 'a'] = 0;
                    cnt++;
                }
                if (soFarPreWordIsValidPrefix && preChar != curChar) {
                    if (!edges[preChar - 'a'][curChar - 'a']) {
                        inDegree[curChar - 'a']++;
                    }
                    edges[preChar - 'a'][curChar - 'a'] = true;
                }

                if (soFarPreWordIsValidPrefix && preChar != curChar) {
                    soFarPreWordIsValidPrefix = false;
                }
            }
        }


        StringBuilder res = new StringBuilder();
        Queue<Character> q = new ArrayDeque<>();
        for (int i = 0; i < 26; i++) {
            if (inDegree[i] == 0) {
                q.offer((char) (i + 'a'));
            }
        }

        while (!q.isEmpty()) {
            char c = q.poll();
            res.append(c);

            for (int i = 0; i < 26; i++) {
                if (edges[c - 'a'][i]) {
                    edges[c - 'a'][i] = false;
                    if (--inDegree[i] == 0) {
                        q.offer((char) (i + 'a'));
                    }
                }
            }
        }

        return res.length() == cnt ? res.toString() : "";
    }

    public String alienOrderV1(String[] words) {
        Map<String, Character> prefix = new HashMap<>();
        int[] inDegree = new int[26];
        Arrays.fill(inDegree, -1);
        boolean[][] edges = new boolean[26][26];
        int cnt = 0;

        for (String word : words) {
            StringBuilder sb = new StringBuilder();

            for (char c : word.toCharArray()) {
                if (inDegree[c - 'a'] == -1) {
                    inDegree[c - 'a'] = 0;
                    cnt++;
                }

                String thisPrefix = sb.toString();
                if (prefix.containsKey(thisPrefix) && prefix.get(thisPrefix) != c) {
                    if (!edges[(char) prefix.get(thisPrefix) - 'a'][c - 'a']) {
                        inDegree[c - 'a']++;
                    }
                    edges[(char) prefix.get(thisPrefix) - 'a'][c - 'a'] = true;
                }
                prefix.put(thisPrefix, c);
                sb.append(c);
            }
        }

        StringBuilder res = new StringBuilder();
        Queue<Character> q = new ArrayDeque<>();
        for (int i = 0; i < 26; i++) {
            if (inDegree[i] == 0) {
                q.offer((char) (i + 'a'));
            }
        }

        while (!q.isEmpty()) {
            char c = q.poll();
            res.append(c);

            for (int i = 0; i < 26; i++) {
                if (edges[c - 'a'][i]) {
                    edges[c - 'a'][i] = false;
                    if (--inDegree[i] == 0) {
                        q.offer((char) (i + 'a'));
                    }
                }
            }
        }

        return res.length() == cnt ? res.toString() : "";
    }
}
