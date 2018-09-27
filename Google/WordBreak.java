/*
* Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false

    20180925
    139
    medium
    O(n ^ 3)
    O(n)
* */
package leetcode.Google;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public boolean wordBreak(String S, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String s : wordDict) {
            set.add(s);
        }

        // dp[i]: the substring start from index 0, length of i can be broke into ...
        boolean[] dp = new boolean[S.length() + 1];
        for (int len = 1; len <= S.length(); len++) {
            if (set.contains(S.substring(0, len))) {
                dp[len] = true;
                continue;
            }

            for (int leftLen = 1; leftLen <= len - 1; leftLen++) {
                if (dp[leftLen] && set.contains(S.substring(leftLen, len))) {
                    dp[len] = true;
                    break;
                }
            }
        }

        return dp[S.length()];
    }
}
