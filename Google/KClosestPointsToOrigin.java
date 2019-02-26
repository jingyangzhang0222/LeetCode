/*
* We have a list of points on the plane.  Find the K closest points to the origin (0, 0).

(Here, the distance between two points on a plane is the Euclidean distance.)

You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)



Example 1:

Input: points = [[1,3],[-2,2]], K = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], K = 2
Output: [[3,3],[-2,4]]
(The answer [[-2,4],[3,3]] would also be accepted.)


Note:

1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000

    20190218
    973
    easy
    O(n)
    O(1)
* */
package leetcode.Google;

import java.util.Arrays;

public class KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int K) {
        quickSelect(points, 0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    private void quickSelect(int[][] points, int left, int right, int K) {
        int partitionPosition = partition(points, left, right);
        if (partitionPosition == K) {
            return;
        } else if (partitionPosition < K) {
            quickSelect(points, partitionPosition + 1, right, K);
            return;
        } else {
            quickSelect(points, left, partitionPosition - 1, K);
            return;
        }
    }

    private int partition(int[][] points, int left, int right) {
        if (left >= right) {
            return right;
        }
        int[] pivotPoint = points[right];
        int start = left, end = right - 1;
        while (start <= end) {
            if (point1IsCloser(points[start], pivotPoint) && point1IsCloser(pivotPoint, points[end])) {
                start++;
                end--;
            } else if (!point1IsCloser(points[start], pivotPoint) && point1IsCloser(pivotPoint, points[end])) {
                end--;
            } else if (point1IsCloser(points[start], pivotPoint) && !point1IsCloser(pivotPoint, points[end])) {
                start++;
            } else {
                swap(points, start, end);
                start++;
                end--;
            }
        }
        // [left, start): < pivotDis
        // (end, right]: >= pivotDis
        // start > end
        swap(points, end + 1, right);
        return end + 1;
    }

    private static boolean point1IsCloser(int[] p1, int[] p2) {
        return p1[0] * p1[0] + p1[1] * p1[1] - p2[0] * p2[0] - p2[1] * p2[1] < 0;
    }

    private static void swap(int[][] points, int start, int end) {
        int tmpX = points[start][0], tmpY = points[start][1];
        points[start][0] = points[end][0];
        points[start][1] = points[end][1];
        points[end][0] = tmpX;
        points[end][1] = tmpY;
    }
}
