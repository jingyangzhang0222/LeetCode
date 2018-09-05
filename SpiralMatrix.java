/*
* Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

    20180904
    54
    medium
    O(n) (# of elements)
    O(1)
* */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int up = 0;
        int down = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (up < down && left < right) {
            for (int upCol = left; upCol <= right - 1; upCol++) {
                res.add(matrix[up][upCol]);
            }

            for (int rightRow = up; rightRow <= down - 1; rightRow++) {
                res.add(matrix[rightRow][right]);
            }

            for (int downCol = right; downCol >= left + 1; downCol--) {
                res.add(matrix[down][downCol]);
            }

            for (int leftRow = down; leftRow >= up + 1; leftRow--) {
                res.add(matrix[leftRow][left]);
            }

            up++;
            down--;
            left++;
            right--;
        }

        // up == down || left == right
        if (up == down) {
            for (int upCol = left; upCol <= right; upCol++) {
                res.add(matrix[up][upCol]);
            }
        } else if (left == right) {
            for (int rightRow = up; rightRow <= down; rightRow++) {
                res.add(matrix[rightRow][right]);
            }
        }
        // (up == down && left == right) covered

        return res;
    }
}
