/*
* Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the given points.

Example 1:

Input: [[1,1],[-1,1]]
Output: true
Example 2:

Input: [[1,1],[-1,-1]]
Output: false
Follow up:
Could you do better than O(n2) ?

    20181010
    356
    medium
    O(n)
    O(n)
* */
package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LineReflection {
    public boolean isReflected(int[][] points) {
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        Set<Point> set = new HashSet<>();
        for (int[] point : points) {
            minX = Math.min(point[0], minX);
            maxX = Math.max(maxX, point[0]);
            set.add(new Point(point[0], point[1]));
        }

        int doubleLineX = maxX + minX;
        for (int[] point : points) {
            Point reflected = new Point(doubleLineX - point[0], point[1]);
            if (!set.contains(reflected)) {
                return false;
            }
        }
        return true;
    }

    class Point{
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return 13 * x + 23 * y;
        }

        @Override
        public boolean equals(Object p2) {
            Point p3 = (Point) p2;
            return (p3.x == x) && (p3.y == y);
        }
    }
}