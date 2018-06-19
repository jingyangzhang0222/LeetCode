/*
* The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.

    20180618
    51
    hard
    DFS
    O(n!)
    O(n)

    18 / 25 min
    bug: typo × 1
* */
package leetcode.NonGraphDFS;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        // DFS
        List<List<Integer>> sol = new ArrayList<>();
        dfsHelper(n, 0, sol, new ArrayList<Integer>());
        List<List<String>> result = new ArrayList<>();
        for (List<Integer> subsol : sol) {
            result.add(convertToString(n, subsol));
        }
        return result;
    }

    private void dfsHelper(int n, int row, List<List<Integer>> sol, List<Integer> subsol) {
        //base case
        if (row == n) {
            sol.add(new ArrayList(subsol));
            return;
        }

        for (int i = 0; i < n; i++) {
            subsol.add(i);
            if (isValid(subsol)) {
                dfsHelper(n, row + 1, sol, subsol);
            }
            subsol.remove(subsol.size() - 1);
        }
    }

    private boolean isValid(List<Integer> subsol) {
        int curRow = subsol.size() - 1;
        int curCol = subsol.get(subsol.size() - 1);
        for (int row = 0; row < subsol.size() - 1; row++) {
            int col = subsol.get(row);
            if (col == curCol || Math.abs(curCol - col) == curRow - row) {
                return false;
            }
        }
        return true;
    }

    private List<String> convertToString(int n, List<Integer> subsol) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int col = subsol.get(i);
            for (int j = 0; j < n; j++) {
                if (j == col) {
                    sb.append('Q');
                } else {
                    sb.append('.');
                }
            }
            res.add(sb.toString());
        }
        return res;
    }
}
