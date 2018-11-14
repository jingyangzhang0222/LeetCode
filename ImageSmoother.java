/*
* Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.

Example 1:
Input:
[[1,1,1],
 [1,0,1],
 [1,1,1]]
Output:
[[0, 0, 0],
 [0, 0, 0],
 [0, 0, 0]]
Explanation:
For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
For the point (1,1): floor(8/9) = floor(0.88888889) = 0
Note:
The value in the given matrix is in the range of [0, 255].
The length and width of the given matrix are in the range of [1, 150].


    20181113
    661
    easy
    O(m * n)
    O(1)
* */
package leetcode;

public class ImageSmoother {
    private static final int[][] neis = new int[][]{
            {-1, -1}, {0, -1}, {1, -1},
            {-1, 0}, {0, 0}, {1, 0},
            {-1, 1}, {0, 1}, {1, 1}};
    public int[][] imageSmoother(int[][] M) {
        int[][] res = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                int cnt = 0;
                int sum = 0;
                for (int[] nei : neis) {
                    int dx = i + nei[0], dy = j + nei[1];
                    if (dx < 0 || dy < 0 || dx >= M.length || dy >= M[0].length) {
                        continue;
                    }
                    cnt++;
                    sum += M[dx][dy];
                }
                res[i][j] = sum / cnt;
            }
        }
        return res;
    }
}
