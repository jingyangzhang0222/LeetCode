/*
* Given a square array of integers A, we want the minimum sum of a falling path through A.

A falling path starts at any element in the first row, and chooses one element from each row.  The next row's choice must be in a column that is different from the previous row's column by at most one.



Example 1:

Input: [[1,2,3],[4,5,6],[7,8,9]]
Output: 12
Explanation:
The possible falling paths are:
[1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
[2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
[3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
The falling path with the smallest sum is [1,4,7], so the answer is 12.



Note:

1 <= A.length == A[0].length <= 100
-100 <= A[i][j] <= 100

    20190406
    931
    medium
    O(mn)
    O(1)
* */
package leetcode.DP;

public class MinimumFallingPathSum {
    public int minFallingPathSum(int[][] A) {
        // 1 2 3
        // 4 5 6
        // 7 8 9
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                int left = Math.max(0, j - 1);
                int right = Math.min(A[0].length - 1, j + 1);
                int gridMin = Integer.MAX_VALUE;
                for (int k = left; k <= right; k++) {
                    int prevVal = i == 0 ? 0 : A[i - 1][k];
                    gridMin = Math.min(gridMin, A[i][j] + prevVal);
                }
                A[i][j] = gridMin;
                if (i == A.length - 1) {
                    min = Math.min(min, gridMin);
                }
            }
        }
        return min;
    }
}
