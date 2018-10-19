/*
* A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
Explanation:

Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
Follow up:

Can you do it in time complexity O(k log mn), where k is the length of the positions?

    20181011
    305
    hard
    O(k)
    O(k)
* */
package leetcode.UnionFind;

import java.util.*;

public class NumberOfIslandsII {
    private static final int[][] dirs = new int[][]{{0, 1}, {-1, 0}, {1, 0}, {0, -1}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> sol = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        UnionFindSet ufs = new UnionFindSet(positions.length, false);
        int cnt = 0;

        for (int i = 0; i < positions.length; i++) {
            int[] p = positions[i];
            map.put(p[0] + "," + p[1], i);
            cnt++;
            Set<Integer> set = new HashSet<>();
            set.add(i);
            for (int[] dir : dirs) {
                int dx = p[0] + dir[0];
                int dy = p[1] + dir[1];
                if (map.containsKey(dx + "," + dy)) {
                    set.add(ufs.find(map.get(dx + "," + dy)));
                    ufs.union(i, map.get(dx + "," + dy));
                }
            }

            cnt -= set.size() - 1;
            sol.add(cnt);
        }

        return sol;
    }
}
