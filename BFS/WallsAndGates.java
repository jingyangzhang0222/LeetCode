/*
* You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example:

Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4

    20181012
    286
    medium
    O(m * n)
    O(m * n)
* */
package leetcode.BFS;

import java.util.ArrayDeque;
import java.util.Queue;

public class WallsAndGates {
    private static final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final int INF = Integer.MAX_VALUE;

    public void wallsAndGatesDFS(int[][] rooms) {
        int m = rooms.length;
        int n = m == 0 ? 0 : rooms[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0, m, n);
                }
            }
        }
    }

    private void dfs(int[][] rooms, int i, int j, int dis, int m, int n) {
        if (i < 0 || j < 0 || i >= m || j >= n || rooms[i][j] < dis) {
            return;
        }
        rooms[i][j] = dis;
        dfs(rooms, i + 1, j, dis + 1, m, n);
        dfs(rooms, i - 1, j, dis + 1, m, n);
        dfs(rooms, i, j + 1, dis + 1, m, n);
        dfs(rooms, i, j - 1, dis + 1, m, n);
    }
    public void wallsAndGatesBFS(int[][] rooms) {
        Queue<Point> q = new ArrayDeque<>();

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    Point gate = new Point(i, j);
                    q.offer(gate);
                }
            }
        }

        bfs(rooms, q);
    }

    private void bfs(int[][] rooms, Queue<Point> q) {
        int dis = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                Point point = q.poll();
                int x = point.x;
                int y = point.y;
                if (rooms[x][y] > 0) {
                    rooms[x][y] = Math.min(rooms[x][y], dis);
                }
                for (int[] dir : dirs) {
                    int dx = x + dir[0];
                    int dy = y + dir[1];
                    Point nei = new Point(dx, dy);
                    if (dx < 0 || dy < 0 ||
                            dx >= rooms.length || dy >= rooms[0].length ||
                            rooms[dx][dy] != INF) {
                        continue;
                    }

                    q.offer(nei);
                }
            }
            dis++;
        }
    }

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return 97 * x + 11 * y;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Point)) {
                return false;
            }

            return (((Point) o).x == this.x) && (((Point) o).y == this.y);
        }
    }
}
