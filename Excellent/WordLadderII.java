package leetcode.Excellent;

import java.util.*;

public class WordLadderII {
    public static void main(String[] args) {

    }
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> prevMap = new HashMap<>();
        Set<String> visited = new HashSet<>();
        Set<String> words = new HashSet<>(wordList);
        Queue<String> q = new ArrayDeque<>();

        int shortest = Integer.MAX_VALUE;
        q.offer(beginWord);
        visited.add(beginWord);
        int len = 1;

        while (!q.isEmpty()) {
            if (len > shortest) {
                break;
            }
            int size = q.size();
            for (int j = 0; j < size; j++) {
                String word = q.poll();
                char[] wordCharArray = word.toCharArray();
                for (int i = 0; i < wordCharArray.length; i++) {
                    char ori = wordCharArray[i];
                    for (int k = 0; k < 26; k++) {
                        char changed = (char) (k + 'a');
                        if (changed != ori) {
                            wordCharArray[i] = changed;
                            String otherWord = new String(wordCharArray);
                            if (otherWord.equals(endWord)) {
                                shortest = len;
                                if (prevMap.containsKey(otherWord)) {
                                    prevMap.get(otherWord).add(word);
                                } else {
                                    List<String> list = new ArrayList<>();
                                    list.add(word);
                                    prevMap.put(otherWord, list);
                                }
                            } else if (words.contains(otherWord) && visited.add(otherWord)) {
                                if (prevMap.containsKey(otherWord)) {
                                    prevMap.get(otherWord).add(word);
                                } else {
                                    List<String> list = new ArrayList<>();
                                    list.add(word);
                                    prevMap.put(otherWord, list);
                                }
                                q.offer(otherWord);
                            }
                        }
                    }
                }
            }
            len++;
        }

        List<List<String>> sol = new ArrayList<>();
        if (shortest == Integer.MAX_VALUE) {
            return sol;
        } else {
            dfs(sol, new ArrayList<String>(), endWord, beginWord, prevMap);
            return sol;
        }
    }

    private void dfs(List<List<String>> sol, List<String> subsol, String word, String beginWord, Map<String, List<String>> prevMap) {
        // base case
        if (word.equals(beginWord)) {
            List<String> solution = new ArrayList<>(subsol);
            Collections.reverse(solution);
            sol.add(new ArrayList(solution));
            return;
        }

        for (String prevWord : prevMap.get(word)) {
            subsol.add(prevWord);
            dfs(sol, subsol, prevWord, beginWord, prevMap);
            subsol.remove(subsol.size() - 1);
        }
    }
}
