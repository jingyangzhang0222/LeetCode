/*
* Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
Example:
X..X
...X
...X
In the above board there are 2 battleships.
Invalid Example:
...X
XXXX
...X
This is an invalid board that you will not receive - as battleships will always have a cell separating between them.
Follow up:
Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?

    20181004
    419
    medium
    O(mn)
    O(1)
* */
package leetcode.Google;

public class BattleshipsInABoard {
    public int countBattleships(char[][] b) {
        if (b == null || b.length == 0 || b[0].length == 0) {
            return 0;
        }
        //        .........
        // case1: .XXXXXXX.
        //        .........

        //        .........
        // case2: ......X..
        //        ......X..
        //        ......X..
        //        .........

        //        ...........
        // case3: .....X.....
        //        ...........
        int cnt = 0;
        final int row = b.length;
        final int col = b[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (b[i][j] == 'X') {
                    boolean leftIsNotX = isOutOfBoundary(i, j - 1, row, col) || b[i][j - 1] != 'X';
                    boolean upIsNotX = isOutOfBoundary(i - 1, j, row, col) || b[i - 1][j] != 'X';
                    if (leftIsNotX && upIsNotX) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    private boolean isOutOfBoundary(int x, int y, int row, int col) {
        return x < 0 || x >= row || y < 0 || y >= col;
    }
}
