/*
* Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.

    20181009
    130
    medium
    O(mn)
    O(mn)
* */
package leetcode;

public class SurroundedRegions {
    private static final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }
        for (int i = 0; i < board.length; i++) {
            dfs(board, i, 0);
            dfs(board, i, board[0].length - 1);
        }
        for (int j = 0; j < board[0].length; j++) {
            dfs(board, 0, j);
            dfs(board, board.length - 1, j);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = board[i][j] == '1' ? 'O' : 'X';
            }
        }
    }

    private void dfs(char[][] board, int x, int y) {
        if (board[x][y] == 'X' || board[x][y] == '1') {
            return;
        }

        board[x][y] = '1';
        for (int[] dir : dirs) {
            int dx = x + dir[0];
            int dy = y + dir[1];
            if (dx < 0 || dy < 0 || dx >= board.length || dy >= board[0].length) {
                continue;
            }
            dfs(board, dx, dy);
        }
    }
}
