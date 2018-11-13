/*
* Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
Empty cells are indicated by the character '.'.


A sudoku puzzle...


...and its solution numbers marked in red.

Note:

The given board contain only digits 1-9 and the character '.'.
You may assume that the given Sudoku puzzle will have a single unique solution.
The given board size is always 9x9.

    20181112
    37
    hard
    O(1)
    O(1)
* */
package leetcode.Google;

public class SudokuSolver {
    private boolean[][] rows = new boolean[9][10];
    private boolean[][] cols = new boolean[9][10];
    private boolean[][] boxes = new boolean[9][10];

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int box = i / 3 * 3 + j / 3;
                    rows[i][board[i][j] - '0'] = true;
                    cols[j][board[i][j] - '0'] = true;
                    boxes[box][board[i][j] - '0'] = true;
                }
            }
        }

        fill(board, 0, 0);
    }

    private boolean fill(char[][] board, int i, int j) {
        if (i == 9) {
            return true;
        }
        int di = j == 8 ? i + 1 : i;
        int dj = j == 8 ? 0 : j + 1;
        if (board[i][j] != '.') {
            return fill(board, di, dj);
        }

        int box = i / 3 * 3 + j / 3;
        for (int candidate = 1; candidate <= 9; candidate++) {
            if (rows[i][candidate] || cols[j][candidate] || boxes[box][candidate]) {
                continue;
            }
            board[i][j] = (char)(candidate + '0');
            rows[i][candidate] = true;
            cols[j][candidate] = true;
            boxes[box][candidate] = true;
            if (fill(board, di, dj)) return true;
            rows[i][candidate] = false;
            cols[j][candidate] = false;
            boxes[box][candidate] = false;
            board[i][j] = '.';

        }

        return false;
    }
}
