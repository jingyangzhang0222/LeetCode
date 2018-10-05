/*
* Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:

Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

    20180929
    120
    medium
    O(# of nums)
    O(# of row)
* */
package leetcode.DP;

import java.util.List;

public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] minArray = new int[0];
        int globalMin = Integer.MAX_VALUE;
        for (List<Integer> row : triangle) {
            int[] prevMinArray = minArray;
            minArray = new int[row.size()];
            int curIndex = 0;
            for (int num : row) {
                int prevIndex1 = curIndex - 1;
                int prevIndex2 = curIndex;
                int candidate1 = prevIndex1 < 0 ? Integer.MAX_VALUE :
                        num + prevMinArray[prevIndex1];
                int candidate2 = prevIndex2 >= prevMinArray.length ? Integer.MAX_VALUE :
                        num + prevMinArray[prevIndex2];
                minArray[curIndex] = candidate1 == Integer.MAX_VALUE && candidate2 == Integer.MAX_VALUE ? num : Math.min(candidate1, candidate2);
                if (row.size() == triangle.size()) {
                    globalMin = Math.min(globalMin, minArray[curIndex]);
                }
                curIndex++;
            }
        }
        return globalMin;
    }
}
