package leetcode.DP;

import java.util.*;

public class WordBreakII {
    public static void main(String[] args) {
        List<String> wordDict = new ArrayList<>();
        wordDict.add("a");
        wordDict.add("aa");
        wordDict.add("aaa");
        wordDict.add("aaaa");
        wordDict.add("aaaaa");

        wordDict.add("aaaaaa");
        wordDict.add("aaaaaaa");
        wordDict.add("aaaaaaaa");
        wordDict.add("aaaaaaaaa");
        wordDict.add("aaaaaaaaaa");

        WordBreakII test = new WordBreakII();
        List<String> res = test.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", wordDict);
        for (String s : res) {
            System.out.println(s);
        }
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        Map<String, List<String>> map = new HashMap<>();

        for (int length = 1; length <= s.length(); length++) {
            String whole = (length < s.length()) ? (s.substring(0, length) + ' ') : (s.substring(0, length));
            String lookup = s.substring(0, length);
            List<String> list = new ArrayList<>();
            map.put(lookup, list);

            if (set.contains(lookup)) {
                list.add(whole);
            }

            for (int shorterLength = 1; shorterLength < length; shorterLength++) {
                String second = (length < s.length()) ? (s.substring(shorterLength, length) + ' ') : s.substring(shorterLength, length);
                String secondLookUp = s.substring(shorterLength, length);
                String first = s.substring(0, shorterLength);
                List<String> partitions = map.get(first);

                if (set.contains(secondLookUp) && partitions.size() > 0) {
                    for (String partition : partitions) {
                        list.add(partition + second);
                    }
                }
            }
        }
        return map.get(s);
    }
}
