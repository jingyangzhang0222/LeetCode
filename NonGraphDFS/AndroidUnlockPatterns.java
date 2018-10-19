/*
* Given an Android 3x3 key lock screen and two integers m and n, where 1 ≤ m ≤ n ≤ 9, count the total number of unlock patterns of the Android lock screen, which consist of minimum of m keys and maximum n keys.



Rules for a valid pattern:

Each pattern must connect at least m keys and at most n keys.
All the keys must be distinct.
If the line connecting two consecutive keys in the pattern passes through any other keys, the other keys must have previously selected in the pattern. No jumps through non selected key is allowed.
The order of keys used matters.





Explanation:

| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |
Invalid move: 4 - 1 - 3 - 6
Line 1 - 3 passes through key 2 which had not been selected in the pattern.

Invalid move: 4 - 1 - 9 - 2
Line 1 - 9 passes through key 5 which had not been selected in the pattern.

Valid move: 2 - 4 - 1 - 3 - 6
Line 1 - 3 is valid because it passes through key 2, which had been selected in the pattern

Valid move: 6 - 5 - 4 - 1 - 9 - 2
Line 1 - 9 is valid because it passes through key 5, which had been selected in the pattern.



Example:

Input: m = 1, n = 1
Output: 9


    20181016
    351
    medium
    O(9!)
    O(9)
* */
package leetcode.NonGraphDFS;

public class AndroidUnlockPatterns {
    private int cnt = 0;

    public int numberOfPatterns(int m, int n) {
        boolean[] grids = new boolean[9];
        dfs(grids, m, n, 1, new int[]{-1, -1});
        return cnt;
    }

    private void dfs(boolean[] grids, int m, int n, int step, int[] prev) {
        if (step > m && step <= n + 1) {
            cnt++;
        }
        if (step == n + 1) {
            return;
        }
        for (int i = 0; i < 9; i++) {
            if (grids[i]) {
                continue;
            }
            int row = i / 3;
            int col = i % 3;
            boolean valid = true;
            if (prev[0] != -1 && (Math.abs(prev[0] - row) == 2 || Math.abs(prev[1] - col) == 2)) {
                if (Math.abs(prev[0] - row) != 1 && Math.abs(prev[1] - col) != 1) {
                    int midRow = (row + prev[0]) / 2;
                    int midCol = (col + prev[1]) / 2;
                    if (!grids[3 * midRow + midCol]) {
                        valid = false;
                    }
                }
            }
            if (valid) {
                grids[i] = true;
                dfs(grids, m, n, step + 1, new int[]{row, col});
                grids[i] = false;
            }
        }
    }
}
