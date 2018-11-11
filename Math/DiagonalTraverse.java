/*
* Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.

Example:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]

Output:  [1,2,4,7,5,3,6,8,9]

Explanation:

Note:

The total number of elements of the given matrix will not exceed 10,000.

    20181109
    498
    medium
    O(m * n)
    O(1)
* */
package leetcode.Math;

public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int[] res = new int[matrix.length * matrix[0].length];
        int[] point = new int[]{0, 0};

        boolean upRight = true;

        for (int i = 0; i < res.length; i++) {
            int row = point[0], col = point[1];
            res[i] = matrix[row][col];
            if (upRight) {
                if (valid(row - 1, col + 1, matrix)) {
                    // upRight valid
                    point[0] = row - 1;
                    point[1] = col + 1;
                } else if (valid(row, col + 1, matrix)) {
                    // right
                    point[0] = row;
                    point[1] = col + 1;
                    upRight = !upRight;
                } else if (valid(row + 1, col, matrix)) {
                    // down
                    point[0] = row + 1;
                    point[1] = col;
                    upRight = !upRight;
                }
            } else {
                if (valid(row + 1, col - 1, matrix)) {
                    point[0] = row + 1;
                    point[1] = col - 1;
                } else if (valid(row + 1, col, matrix)) {
                    point[0] = row + 1;
                    point[1] = col;
                    upRight = !upRight;
                } else if (valid(row, col + 1, matrix)) {
                    point[0] = row;
                    point[1] = col + 1;
                    upRight = !upRight;
                }
            }
        }
        return res;
    }

    private boolean valid(int x, int y, int[][] matrix) {
        return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length;
    }
}
