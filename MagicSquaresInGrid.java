package leetcode;

public class MagicSquaresInGrid {
    private static final int[][] neis = new int[][]{{-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}};
    public int numMagicSquaresInside(int[][] grid) {
        int count = 0;
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) {
                if (grid[i][j] != 5) {
                    continue;
                }

                if (grid[i - 1][j] + grid[i + 1][j] != 10) {
                    continue;
                }
                if (grid[i][j - 1] + grid[i][j + 1] != 10) {
                    continue;
                }
                if (grid[i - 1][j - 1] + grid[i + 1][j + 1] != 10) {
                    continue;
                }
                if (grid[i - 1][j + 1] + grid[i + 1][j - 1] != 10) {
                    continue;
                }

                boolean[] unique = new boolean[9];
                boolean itemsUnique = true;
                for (int[] nei : neis) {
                    if (grid[i + nei[0]][j + nei[1]] < 1 ||
                            grid[i + nei[0]][j + nei[1]] > 9 ||
                            unique[grid[i + nei[0]][j + nei[1]] - 1]) {
                        itemsUnique = false;
                        break;
                    }
                    unique[grid[i + nei[0]][j + nei[1]] - 1] = true;
                }
                if (itemsUnique) count++;
            }
        }

        return count;
    }
}
