/*
* You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Given input matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
Example 2:

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
],

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]

    20180629
    48
    medium
    O(n ^ 2)
    O(1)
* */
package leetcode;

public class RotateImage {
    public static void main(String[] args) {
        int[][] m = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        RotateImage test = new RotateImage();
        test.rotate(m);
    }

    public void rotate(int[][] matrix) {
        int size = matrix.length;
        int n = size;
        while (size >= 2) {
            int lo = n / 2 - size / 2;
            int hi = lo + size - 1;
            int left = lo, up = lo;
            int right = hi, down = hi;

            for (int i = lo; i < hi; i++) {
                int offset = i - lo;///////////////////////////////////////////
                int tmp = matrix[up][lo + offset];

                matrix[up][lo + offset] = matrix[hi - offset][left];
                matrix[hi - offset][left] = matrix[down][hi - offset];
                matrix[down][hi - offset] = matrix[lo + offset][right];
                matrix[lo + offset][right] = tmp;
            }

            size -= 2;
        }
    }
}