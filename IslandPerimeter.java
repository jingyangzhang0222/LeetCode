/*
* You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water.

Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).

The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.



Example:

Input:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Output: 16

Explanation: The perimeter is the 16 yellow stripes in the image below:

    20181112
    463
    easy
    O(m * n)
    O(1)
* */
package leetcode;

public class IslandPerimeter {
    private static final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int islandPerimeter(int[][] grid) {
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                for (int[] dir : dirs) {
                    int dx = i + dir[0];
                    int dy = j + dir[1];
                    if (invalid(grid, dx, dy) || grid[dx][dy] == 0) {
                        cnt++;
                    }
                }
            }
        }
        return cnt;
    }

    private boolean invalid(int[][] grid, int x, int y) {
        return x < 0 || y < 0 || x >= grid.length || y >= grid[0].length;
    }
}
