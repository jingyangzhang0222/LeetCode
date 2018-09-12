/*
* According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

Example:

Input:
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
Output:
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
Follow up:

Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?

    20180911
    289
    medium
    O(mn)
    O(1)
* */
package leetcode;

public class GameOfLife {
    public static void main(String[] args) {
        int[][] board = new int[5][20];
        board[0][0] = 1;
        board[0][3] = 1;
        board[1][4] = 1;
        board[2][0] = 1;
        board[2][4] = 1;
        board[3][1] = 1;
        board[3][2] = 1;
        board[3][3] = 1;
        board[3][4] = 1;
        for (int i = 0; i < 20; i++) {
            for (int[] row : board) {
                for (int num : row) {
                    System.out.print(num == 0 ? '_' : 0);
                }
                System.out.println();
            }
            GameOfLife.gameOfLife(board);
            System.out.println();
        }
    }
    private static int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public static void gameOfLife(int[][] board) {
        // 0: 0 -> 0
        // 2: 0 -> 1
        // 3: 1 -> 0
        // 1: 1 -> 1
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int cnt = 0;
                for (int[] dir : dirs) {
                    int dx = i + dir[0];
                    int dy = j + dir[1];
                    if (dx < 0 || dx >= board.length || dy < 0 || dy >= board[i].length) {
                        continue;
                    }

                    if (board[dx][dy] == 1 || board[dx][dy] == 3) {
                        cnt++;
                    }
                }

                if (board[i][j] == 1) {
                    if (cnt < 2 || cnt > 3) {
                        board[i][j] = 3;
                    }
                } else if (cnt == 3) {
                    board[i][j] = 2;
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = (board[i][j] == 0 || board[i][j] == 3) ? 0 : 1;
            }
        }
    }
}
