/*
* A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

Example:

Input:  n = 2
Output: ["11","69","88","96"]

    20181018
    247
    medium
    O(5 ^ n)
    O(n)
* */
package leetcode.NonGraphDFS;

import java.util.ArrayList;
import java.util.List;

public class StrobogrammaticNumberII {
    public List<String> findStrobogrammatic(int n) {
        List<String> sol = new ArrayList<>();
        char[][] pool = new char[][]{{'0', '0'}, {'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};
        char[] subsol = new char[n];
        dfs(sol, pool, subsol, 0);
        return sol;
    }

    private void dfs(List<String> sol, char[][] pool, char[] subsol, int index) {
        // 0 1 2 3        4
        //     b
        // 0 1 2 3 4      5
        //       b
        if (index == (subsol.length + 1) / 2) {
            sol.add(new String(subsol));
            return;
        }
        // no trailing zeroes (except there is only one space)
        int start = (index == 0 && subsol.length - 1 - index != 0) ? 1 : 0;
        // length is odd, the mid char must be 0, 1 or 8
        int end = index == subsol.length - 1 - index ? 2 : 4;
        for (int i = start; i <= end; i++) {
            subsol[index] = pool[i][0];
            subsol[subsol.length - 1 - index] = pool[i][1];
            dfs(sol, pool, subsol, index + 1);
        }
    }
}
