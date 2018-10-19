/*
* Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.

Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].

Note:

The length of words1 and words2 will not exceed 1000.
The length of pairs will not exceed 2000.
The length of each pairs[i] will be 2.
The length of each words[i] and pairs[i][j] will be in the range [1, 20].

    20181012
    737
    medium
    DFS:
        O(words * pairs)
        O(pairs)
    Union find:
        O(words + pairs)
        O(pairs)
* */
package leetcode.UnionFind;

import java.util.HashMap;
import java.util.Map;

public class SentenceSimilarityII {
    public static void main(String[] args) {
        String[] words1 = new String[]{"I", "have", "enjoyed", "happy", "thanksgiving", "holidays"};
        String[] wrods2 = new String[]{"I", "have", "enjoyed", "happy", "thanksgiving", "holidays"};
        String[][] pairs = new String[][]{{"great", "good"}, {"extraordinary", "good"}, {"well", "good"},
                {"wonderful", "good"}, {"excellent", "good"}, {"fine", "good"}, {"nice", "good"}, {"any", "one"},
                {"some", "one"}, {"unique", "one"}, {"the", "one"}, {"an", "one"}, {"single", "one"}, {"a", "one"},
                {"truck", "car"}, {"wagon", "car"}, {"automobile", "car"}, {"auto", "car"}, {"vehicle", "car"},
                {"entertain", "have"}, {"drink", "have"}, {"eat", "have"}, {"take", "have"}, {"fruits", "meal"},
                {"brunch", "meal"}, {"breakfast", "meal"}, {"food", "meal"}, {"dinner", "meal"}, {"super", "meal"},
                {"lunch", "meal"}, {"possess", "own"}, {"keep", "own"}, {"have", "own"}, {"extremely", "very"},
                {"actually", "very"}, {"really", "very"}, {"super", "very"}};

        SentenceSimilarityII test = new SentenceSimilarityII();
        System.out.println(test.areSentencesSimilarTwo(words1, wrods2, pairs));
    }

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }

        Map<String, Integer> map = new HashMap<>();
        UnionFindSet ufs = new UnionFindSet(pairs.length * 2, true);
        int cnt = 0;
        for (String[] pair : pairs) {
            if (!map.containsKey(pair[0])) {
                map.put(pair[0], cnt++);
            }
            if (!map.containsKey(pair[1])) {
                map.put(pair[1], cnt++);
            }
            ufs.union(map.get(pair[0]), map.get(pair[1]));
        }

        for (int i = 0; i < words1.length; i++) {
            String word1 = words1[i];
            String word2 = words2[i];
            if (word1.equals(word2)) {
                continue;
            }
            if (!map.containsKey(word1) || !map.containsKey(word2)) {
                return false;
            }

            if (ufs.find(map.get(word1)) != ufs.find(map.get(word2))) {
                return false;
            }
        }

        return true;
    }
}
