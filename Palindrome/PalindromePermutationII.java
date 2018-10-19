package leetcode.Palindrome;

import java.util.ArrayList;
import java.util.List;

public class PalindromePermutationII {
    public static void main(String[] args) {
        System.out.println(new PalindromePermutationII().generatePalindromes("aab"));
    }
    public List<String> generatePalindromes(String s) {
        int[] map = new int[128];
        int odd = 0;
        for (int i = 0; i < s.length(); i++) {
            if (++map[s.charAt(i)] % 2 == 1) {
                odd++;
            } else {
                odd--;
            }
        }
        List<String> sol = new ArrayList<>();
        if (odd > 1) {
            return sol;
        }
        dfs(sol, map, new char[s.length()], 0);
        return sol;
    }

    private void dfs(List<String> sol, int[] map, char[] s, int index) {
        if (index == (s.length + 1) / 2) {
            sol.add(new String(s));
            return;
        }

        for (int i = 0; i < 128; i++) {
            int charNeeded = index == s.length - 1 - index ? 1 : 2;
            if (map[i] < charNeeded) {
                continue;
            }
            s[index] = (char)i;
            s[s.length - 1 - index] = (char)i;
            map[i] -= charNeeded;
            dfs(sol, map, s, index + 1);
            map[i] += charNeeded;
        }
    }
}
