/*
* A group of two or more people wants to meet and minimize the total travel distance. You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example:

Input:

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 6

Explanation: Given three people living at (0,0), (0,4), and (2,2):
             The point (0,2) is an ideal meeting point, as the total travel distance
             of 2+2+2=6 is minimal. So return 6.

    20181031
    296
    hard
    O(mn)
    o(mn)
* */
package leetcode.Excellent;

import java.util.ArrayList;
import java.util.List;

public class BestMeetingPoint {
    public int minTotalDistance(int[][] grid) {
        // 1. BFS from group people
        // 2. median
        //  2.1: sort
        //  2.2: direct

        List<int[]> g = new ArrayList<>();
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();

        int count = grid.length * grid[0].length;
        int index = 0;

        // 3 row, 5 col
        // 3 -> (0, 3), (0, 1)
        // 4 -> (0, 4), (1, 1)
        // 5 -> (1, 0), (2, 1)
        while (index < count) {
            int i = index / grid[0].length;
            int j = index % grid[0].length;
            // -------> 1
            // -------> 2
            //...       ...
            // -------> ...

            // 0 1 .....
            // | | ... |
            // | | ... |
            // | | ... |
            // ↓ ↓ ... ↓
            int x = index % grid.length;
            int y = index / grid.length;
            if (grid[i][j] == 1) {
                g.add(new int[]{i, j});
                // values in rows: ascending
                rows.add(i);
            }
            if (grid[x][y] == 1) {
                // values in cols: also ascending
                cols.add(y);
            }
            index++;
        }

        // 0 1 2 3 4, 5 -> 2
        // 0 1 2 3, 4 ->1
        int medianX = rows.get((rows.size() - 1) / 2);
        int medianY = cols.get((cols.size() - 1) / 2);
        int sum = 0;
        for (int[] point : g) {
            sum += Math.abs(medianX - point[0]) + Math.abs(medianY - point[1]);
        }

        return sum;
    }
}
