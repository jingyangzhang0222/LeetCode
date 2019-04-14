/*
* Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12
Note:
You may assume that the matrix does not change.
There are many calls to sumRegion function.
You may assume that row1 ≤ row2 and col1 ≤ col2.


    20190324
    304
    medium
    O(1)
    O(mn)
* */
package leetcode.Design;

public class RangeSumQuery2DImmutable {
    private int[][] matrix;
    public RangeSumQuery2DImmutable(int[][] matrix) {
        // 3 3  4  8  10
        // 5 11 14 16 17
        // 1 3  3  4  9
        // 4 5  5  6  13
        // 1 1  4  4  9

        // 3 3  4  8  10
        // 8 14 18 24 27
        // 9 17 21 28 36
        //
        this.matrix = matrix;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                matrix[i][j] += matrix[i][j - 1];
            }
        }

        for (int j = 0; j < matrix[0].length; j++) {
            for (int i = 1; i < matrix.length; i++) {
                matrix[i][j] += matrix[i - 1][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int sum1 = row1 == 0 ? 0 : matrix[row1 - 1][col2];
        int sum2 = col1 == 0 ? 0 : matrix[row2][col1 - 1];
        int sum3 = row1 == 0 || col1 == 0 ? 0 : matrix[row1 - 1][col1 - 1];
        return matrix[row2][col2] - sum1 - sum2 + sum3;
    }
}
