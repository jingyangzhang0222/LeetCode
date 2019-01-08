/*
* There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

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

Output: 12

Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
             The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.

Example 2:

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: -1

Explanation: There is no way for the ball to stop at the destination.



Note:

There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

    20190103
    505
    medium
    O(mn * log(mn))
    O(mn)
* */
package leetcode.Google;

import java.util.Arrays;
import java.util.PriorityQueue;

public class TheMazeII {
    private static final int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int xSrc = start[0], ySrc = start[1];
        int xDst = destination[0], yDst = destination[1];
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row : distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[xSrc][ySrc] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> i1[2] - i2[2]);
        pq.offer(new int[]{xSrc, ySrc, 0});

        while (!pq.isEmpty()) {
            int[] point = pq.poll();
            int x = point[0], y = point[1];
            int prevTravelledDistance = point[2];

            if (x == xDst && y == yDst) {
                return point[2];
            }

            for (int[] dir : dirs) {
                int dx = x, dy = y;
                int curDistance = 0;
                while (isValidIndex(maze, dx + dir[0], dy + dir[1]) && maze[dx + dir[0]][dy + dir[1]] == 0) {
                    dx += dir[0];
                    dy += dir[1];
                    curDistance++;
                }

                if (prevTravelledDistance + curDistance < distance[dx][dy]) {
                    distance[dx][dy] = prevTravelledDistance + curDistance;
                    pq.offer(new int[]{dx, dy, distance[dx][dy]});
                }
            }
        }

        return -1;
    }

    private boolean isValidIndex(int[][] maze, int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length;
    }
}
