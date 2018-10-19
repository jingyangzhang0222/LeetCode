/*
* Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]

    20181017
    131
    medium
    O()
    O(n ^ 2)
* */
package leetcode.Palindrome;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        boolean[][] check = new boolean[s.length()][s.length()];
        for (int size = 1; size <= s.length(); size++) {
            for (int left = 0; left + size - 1 < s.length(); left++) {
                int right = left + size - 1;
                if (left == right) {
                    check[left][right] = true;
                } else if (left == right - 1) {
                    check[left][right] = s.charAt(left) == s.charAt(right);
                } else {
                    check[left][right] = s.charAt(left) == s.charAt(right) && check[left + 1][right - 1];
                }
            }
        }
        List<List<String>> sol = new ArrayList<>();
        dfs(sol, new ArrayList<String>(), s, 0, check);
        return sol;
    }

    private void dfs(List<List<String>> sol, List<String> subsol, String s, int start, boolean[][] check) {
        if (start == s.length()) {
            sol.add(new ArrayList<String>(subsol));
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = start; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if (!check[start][i]) {
                continue;
            }

            subsol.add(sb.toString());
            dfs(sol, subsol, s, i + 1, check);
            subsol.remove(subsol.size() - 1);
        }
    }
}
