/*
* Given a n x n matrix where each of the rows and columns are sorted in ascending order, find the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

Example:

matrix = [
   [ 1,  5,  9],
   [10, 11, 13],
   [12, 13, 15]
],
k = 8,

return 13.
Note:
You may assume k is always valid, 1 ≤ k ≤ n2.

    20180905
    378
    medium
    O(m * n + klog(m * n))
    O(m * n)
* */
package leetcode.kth;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KthSmallestElementInASortedMatrix {
    public int kthSmallestUsingRangeBasedBS(int[][] matrix, int k) {
        int lo = matrix[0][0];
        int hi = matrix[matrix.length - 1][matrix[0].length - 1];
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int count = numberOfLessAndEqualInMatrix(matrix, mid);
            if (count < k) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return lo;
    }

    private int numberOfLessAndEqualInMatrix(int[][] matrix, int target) {
        int count = 0;
        int row = matrix.length - 1, col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] > target) {
                row--;
            } else {
                col++;
                count += row + 1;
            }
        }

        return count;
    }

    public int kthSmallestUsingHeap(int[][] matrix, int k) {
        List<Integer> list = new ArrayList<>();
        for (int[] row : matrix) {
            for (int num : row) {
                list.add(num);
            }
        }
        PriorityQueue<Integer> pq = new PriorityQueue(list);
        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }
        return pq.poll();
    }
}
