package leetcode.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestWordInDictionaryThroughDeleting {
    public static void main(String[] args) {
        String s = "abpcplea";
        List<String> d = new ArrayList<>();
        d.add("ale");
        d.add("apple");
        System.out.print(new LongestWordInDictionaryThroughDeleting().findLongestWord(s, d));
    }
    public String findLongestWord(String s, List<String> d) {
        int max = 0;
        String res = "";
        int[] map = new int[26];
        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }

        for (String t : d) {
            int[] dummy = Arrays.copyOf(map, 26);
            boolean valid = true;
            for (char c : t.toCharArray()) {
                if (--map[c - 'a'] < 0) {
                    valid = false;
                    break;
                }
            }

            if (valid && t.length() > max) {
                max = t.length();
                res = t;
            }
        }

        return res;
    }
}
