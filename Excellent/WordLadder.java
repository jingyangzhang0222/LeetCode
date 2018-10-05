package leetcode.Excellent;

import java.util.*;

public class WordLadder {
    public int ladderLength25Transforms(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        Queue<String> q = new ArrayDeque<>();
        q.offer(beginWord);
        visited.add(beginWord);
        int len = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            len++;
            for (int j = 0; j < size; j++) {
                char[] word = q.poll().toCharArray();
                for (int i = 0; i < word.length; i++) {
                    char ori = word[i];
                    for (int k = 0; k < 26; k++) {
                        char changed = (char) (k + 'a');
                        if (changed != ori) {
                            word[i] = changed;
                            String otherWord = new String(word);
                            if (words.contains(otherWord) && visited.add(otherWord)) {
                                if (otherWord.equals(endWord)) {
                                    return len;
                                }
                                q.offer(otherWord);
                            }
                        }
                    }
                    word[i] = ori;
                }
            }
        }
        return 0;
    }

    public int ladderLengthCheckOneByOne(String beginWord, String endWord, List<String> wordList) {
        Set<String> visited = new HashSet<>();
        Queue<String> q = new ArrayDeque<>();
        q.offer(beginWord);
        visited.add(beginWord);
        int len = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            len++;
            for (int i = 0; i < size; i++) {
                String word = q.poll();
                for (String otherWord : wordList) {
                    if (!visited.contains(otherWord) && isDiffByOne(word, otherWord)) {
                        // always mark as visited when generated
                        if (otherWord.equals(endWord)) {
                            return len;
                        }
                        visited.add(otherWord);
                        q.offer(otherWord);
                    }
                }
            }
        }
        return 0;
    }

    private boolean isDiffByOne(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        int diffCnt = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diffCnt++;
            }
            if (diffCnt > 1) {
                return false;
            }
        }
        return diffCnt == 1;
    }
}
