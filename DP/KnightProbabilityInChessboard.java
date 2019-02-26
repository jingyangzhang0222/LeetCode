/*
* On an NxN chessboard, a knight starts at the r-th row and c-th column and attempts to make exactly K moves. The rows and columns are 0 indexed, so the top-left square is (0, 0), and the bottom-right square is (N-1, N-1).

A chess knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.







Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

The knight continues moving until it has made exactly K moves or has moved off the chessboard. Return the probability that the knight remains on the board after it has stopped moving.



Example:

Input: 3, 2, 0, 0
Output: 0.0625
Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
From each of those positions, there are also two moves that will keep the knight on the board.
The total probability the knight stays on the board is 0.0625.


Note:

N will be between 1 and 25.
K will be between 0 and 100.
The knight always initially starts on the board.

    20190215
    688
    medium
    O(n ^ 2 * k)
    O(n ^ 2)
* */
package leetcode.DP;

public class KnightProbabilityInChessboard {
    private static final int[][] dirs = new int[][]{{-1, -2}, {1, -2}, {-2, -1}, {2, -1},
            {-1, 2}, {1, 2}, {-2, 1}, {2, 1}};

    public double knightProbability(int N, int K, int r, int c) {
        double[][] board = new double[N][N];
        board[r][c] = 1.0;
        for (int i = 0; i < K; i++) {
            double[][] nextStage = new double[N][N];
            update(board, nextStage, N);
            board = nextStage;
        }

        double res = 0;
        for (double[] row : board) {
            for (double grid : row) {
                res += grid;
            }
        }

        return res;
    }

    private void update(double[][] board, double[][] nextStage, int N) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (board[x][y] > 0) {
                    for (int[] dir : dirs) {
                        int dx = x + dir[0], dy = y + dir[1];
                        if (dx < 0 || dx >= N || dy < 0 || dy >= N) {
                            continue;
                        }
                        nextStage[dx][dy] += board[x][y] * 0.125;
                    }
                }
            }
        }
    }
}
