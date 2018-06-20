/*
* Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

Input: nums =
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:

Input: nums =
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

    20180619
    329
    hard
    O(m * n)
    O(m * n)

    bug: 1
    typo: 1
* */
package leetcode.NonGraphDFS;

public class LongestIncreasingPathInAMatrix {
    private static final int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public static void main(String[] args) {
        LongestIncreasingPathInAMatrix test = new LongestIncreasingPathInAMatrix();
        int[][] matrix = {{13, 5,  13, 9},
                          {5,  0,  2,  9},
                          {10, 13, 11, 10},
                          {0,  0,  13, 13}};
        System.out.println(test.longestIncreasingPath(matrix));
    }

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int[][] LIP = new int[matrix.length][matrix[0].length];
        int globalMax = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (LIP[i][j] == 0) {
                    dfsHelper(matrix, LIP, i, j);
                }
                globalMax = Math.max(globalMax, LIP[i][j]);
            }
        }
        return globalMax;
    }
    private int dfsHelper(int[][] matrix, int[][] LIP, int x, int y) {///////////////no visited[][]
        //base case
        if (LIP[x][y] != 0) {
            return LIP[x][y];
        }

        int max = 1;
        for (int[] dir : dirs) {
            int dx = x + dir[0];
            int dy = y + dir[1];
            if (dx < 0 || dx >= matrix.length || dy < 0 || dy >= matrix[0].length) {
                continue;
            }

            if (matrix[dx][dy] > matrix[x][y]) {
                max = Math.max(max, 1 + dfsHelper(matrix, LIP, dx, dy));
            }
        }
        LIP[x][y] = max;
        return max;
    }
}
