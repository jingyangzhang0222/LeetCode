/*
* There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.



Example 1:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true

Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false

Explanation: There is no way for the ball to stop at the destination.



Note:

There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

    20181101
    490
    medium
    O(mn * max(m, n))
    O(mn)
* */
package leetcode.Google;

import java.util.ArrayDeque;
import java.util.Queue;

public class TheMaze {
    private static final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean hasPath(int[][] maze, int[] start, int[] end) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] stopped = new boolean[maze.length][maze[0].length];
        q.offer(new int[]{start[0], start[1]});
        stopped[start[0]][start[1]] = true;

        while (!q.isEmpty()) {
            int[] point = q.poll();
            int iniX = point[0], iniY = point[1];
            if (iniX == end[0] && iniY == end[1]) {
                return true;
            }

            for (int[] dir : dirs) {
                int x = iniX, y = iniY;
                while (isValidIndex(maze, x + dir[0], y + dir[1]) && maze[x + dir[0]][y + dir[1]] == 0) {
                    x += dir[0];
                    y += dir[1];
                }

                // stopped
                if (!stopped[x][y]) {
                    // this is a new "station"
                    stopped[x][y] = true;
                    q.offer(new int[]{x, y});
                }
            }
        }

        return false;
    }

    private boolean isValidIndex(int[][] maze, int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length;
    }
}
