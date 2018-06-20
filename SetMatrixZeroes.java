/*
* Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:

Input:
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output:
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
Example 2:

Input:
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output:
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
Follow up:

A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?

    20180619
    73
    medium
    2d Array
    O(m + n)
    O(1)
* */
package leetcode;

public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        boolean firstRowContainsZero = false;
        boolean firstColContainsZero = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i != 0 && j != 0) matrix[0][j] = 0;
                    if (i != 0 && j != 0) matrix[i][0] = 0;
                    if (i == 0) firstRowContainsZero = true;
                    if (j == 0) firstColContainsZero = true;
                }
            }
        }

        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                if (i == 0 && firstRowContainsZero) {
                    matrix[i][j] = 0;
                } else if (j == 0 && firstColContainsZero) {
                    matrix[i][j] = 0;
                } else if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
