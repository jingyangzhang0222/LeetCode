package leetcode.DP;

public class BombEnemy {
    public static void main(String[] args) {
        BombEnemy test = new BombEnemy();
        System.out.println(test.maxKilledEnemies(new char[][]{{'0','E','0','0'},{'E','0','W','E'},{'0','E','0','0'}}));
    }
    public int maxKilledEnemies(char[][] grid) {
        // naive: (m * n) * (m + n)
        final int row = grid.length;
        final int col = grid[0].length;
        int[][] lookUp = new int[row][col],
                lookLeft = new int[row][col],
                lookDown = new int[row][col],
                lookRight = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int x = row - 1 - i;
                int y = col - 1 - j;
                // i↑ j↑ -> dir: right, down
                // x↓ y↓ -> dir: left, up

                int eUp = getNumber(grid, lookUp, i - 1, j);
                int eDown = getNumber(grid, lookDown, x + 1, y);
                int eLeft = getNumber(grid, lookLeft, i, j - 1);
                int eRight = getNumber(grid, lookRight, x, y + 1);

                lookUp[i][j] = setNumber(grid, eUp, i, j);
                lookLeft[i][j] = setNumber(grid, eLeft, i, j);
                lookDown[i][j] = setNumber(grid, eDown, x, y);
                lookRight[i][j] = setNumber(grid, eRight, x, y);
            }
        }

        int max = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '0') {
                    max = Math.max(max, lookUp[i][j] + lookDown[i][j] + lookLeft[i][j] + lookRight[i][j]);
                }
            }
        }
        return max;
    }

    private int setNumber(char[][] grid, int prev, int m, int n) {
        if (grid[m][n] == 'W') {
            return 0;
        }

        return grid[m][n] == 'E' ? prev + 1 : prev;
    }

    private int getNumber(char[][] grid, int[][] look, int m, int n) {
        if (m < 0 || n < 0 || m >= grid.length || n >= grid[0].length) {
            return 0;
        }

        if (grid[m][n] == 'W') {
            return 0;
        }

        return look[m][n];
    }
}
