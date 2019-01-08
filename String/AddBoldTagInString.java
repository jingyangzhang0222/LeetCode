/*
* Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
Example 1:
Input:
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"
Example 2:
Input:
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"
Note:
The given dict won't contain duplicates, and its length won't exceed 100.
All the strings in input have length in range [1, 1000].

    20190107
    616
    medium
    O()
    O(n)
* */
package leetcode.String;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddBoldTagInString {
    final String left = "<b>";
    final String right = "</b>";

    public static void main(String[] args) {
        System.out.println(new AddBoldTagInString().addBoldTagOn3("aaabbcc", new String[]{"a", "b", "c"}));
    }

    public String addBoldTagOn3(String s, String[] dict) {
        Set<String> set = new HashSet<>();
        for (String str : dict) {
            set.add(str);
        }

        int[] startCount = new int[s.length()];
        int[] endCount = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String candidate = s.substring(i, j);
                if (set.contains(candidate)) {
                    startCount[i]++;
                    endCount[j - 1]++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int stackCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (startCount[i] > 0) {
                if (stackCount == 0 && (i == 0 || endCount[i - 1] == 0)) {
                    sb.append(left);
                }
                stackCount += startCount[i];
            }
            sb.append(s.charAt(i));
            if (endCount[i] > 0) {
                stackCount -= endCount[i];
                if (stackCount == 0 && (i == s.length() - 1 || startCount[i + 1] == 0)) {
                    sb.append(right);
                }
            }
        }

        return sb.toString();
    }

    public String addBoldTag(String s, String[] dict) {
        List<int[]> list = new ArrayList<>();
        generateIntervals(list, dict, s);
        if (list.isEmpty()) return s;

        boolean[] isStart = new boolean[s.length()];
        boolean[] isEnd = new boolean[s.length()];
        mergeIntervalsAndMark(list, isStart, isEnd);

        StringBuilder sb = new StringBuilder();
        mark2Tag(s, sb, isStart, isEnd);
        return sb.toString();
    }

    private void generateIntervals(List<int[]> list, String[] dict, String s) {
        for (String str : dict) {
            int startIndex = 0;
            while (startIndex <= s.length() - str.length()) {
                int nextIndex = s.indexOf(str, startIndex);
                if (nextIndex == -1) {
                    break;
                }
                list.add(new int[]{nextIndex, nextIndex + str.length() - 1});
                startIndex = nextIndex + 1;
            }
        }
    }

    private void mergeIntervalsAndMark(List<int[]> list, boolean[] isStart, boolean[] isEnd) {
        list.sort((i1, i2) -> i1[0] - i2[0]);
        int[] prev = list.get(0);
        isStart[prev[0]] = true;

        for (int[] cur : list) {
            if (cur[0] > prev[1] + 1) {
                isEnd[prev[1]] = true;
                isStart[cur[0]] = true;
            } else {
                cur[0] = prev[0];
                cur[1] = Math.max(prev[1], cur[1]);
            }

            prev = cur;
        }

        isEnd[prev[1]] = true;
    }

    private void mark2Tag(String s, StringBuilder sb, boolean[] isStart, boolean[] isEnd) {
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (isStart[i]) {
                sb.append(left);
            }
            sb.append(s.charAt(i));
            if (isEnd[i]) {
                sb.append(right);
            }
        }
    }
}
