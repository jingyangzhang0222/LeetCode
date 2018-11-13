/*
* Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.

If there isn't any rectangle, return 0.



Example 1:

Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
Output: 4
Example 2:

Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
Output: 2


Note:

1 <= points.length <= 500
0 <= points[i][0] <= 40000
0 <= points[i][1] <= 40000
All points are distinct.

    20181112
    939
    medium
    O(n ^ 2)
    O(n)
* */
package leetcode.Hash;

import java.util.HashSet;
import java.util.Set;

public class MinimumAreaRectangle {
    public static void main(String[] args) {
        System.out.println(new MinimumAreaRectangle().minAreaRect(new int[][]{{1, 1}, {1, 2}, {2, 1}, {2, 2}}));
    }

    public int minAreaRect(int[][] points) {
        final int HASH_BASE = 400004;
        Set<Integer> set = new HashSet<>();

        int min = Integer.MAX_VALUE;
        for (int[] point : points) {
            int x = point[0], y = point[1];
            for (int p : set) {
                int anotherX = p / HASH_BASE, anotherY = p % HASH_BASE;
                if (anotherX != x && anotherY != y &&
                        set.contains(HASH_BASE * x + anotherY) &&
                        set.contains(HASH_BASE * anotherX + y)) {
                    min = Math.min(min, Math.abs(anotherX - x) * Math.abs(anotherY - y));
                }
            }

            set.add(HASH_BASE * x + y);
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }

    class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return 101 * x + 97 + y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Point)) {
                return false;
            }

            Point p2 = (Point) o;
            return this.x == p2.x && this.y == p2.y;
        }
    }
}
