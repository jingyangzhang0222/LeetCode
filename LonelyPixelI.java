/*
* Given a picture consisting of black and white pixels, find the number of black lonely pixels.

The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.

A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any other black pixels.

Example:
Input:
[['W', 'W', 'B'],
 ['W', 'B', 'W'],
 ['B', 'W', 'W']]

Output: 3
Explanation: All the three 'B's are black lonely pixels.
Note:
The range of width and height of the input 2D array is [1,500].


    20181010
    531
    medium
    O(m * n)
    O(m*n) -> O(m + n) -> O(1)???
* */
package leetcode;

public class LonelyPixelI {
    public int findLonelyPixel(char[][] p) {
        if (p.length == 0 || p[0].length == 0) {
            return 0;
        }
        int[][][] lookUp = new int[p.length][p[0].length][4];
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[0].length; j++) {
                int m = p.length - 1 - i;
                int n = p[0].length - 1 - j;
                if (i - 1 >= 0) {
                    lookUp[i][j][0] = p[i - 1][j] == 'B' ? lookUp[i - 1][j][0] + 1 : lookUp[i - 1][j][0];
                }
                if (j - 1 >= 0) {
                    lookUp[i][j][1] = p[i][j - 1] == 'B' ? lookUp[i][j - 1][1] + 1 : lookUp[i][j - 1][1];
                }
                if (m + 1 < p.length) {
                    lookUp[m][n][2] = p[m + 1][n] == 'B' ? lookUp[m + 1][n][2] + 1 : lookUp[m + 1][n][2];
                }
                if (n + 1 < p[0].length) {
                    lookUp[m][n][3] = p[m][n + 1] == 'B' ? lookUp[m][n + 1][3] + 1 : lookUp[m][n + 1][3];
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[0].length; j++) {
                if (p[i][j] == 'B' &&
                        lookUp[i][j][0] == 0 &&
                        lookUp[i][j][1] == 0 &&
                        lookUp[i][j][2] == 0 &&
                        lookUp[i][j][3] == 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
