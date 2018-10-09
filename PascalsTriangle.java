/*
* Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 5
Output:
[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

    20181005
    118
    easy
    O(n ^ 2)
    O(1)
* */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    public List<List<Integer>> generate(int n) {
        List<List<Integer>> sol = new ArrayList<>();
        for (int rowNumber = 0; rowNumber < n; rowNumber++) {
            int size = rowNumber + 1;
            List<Integer> row = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                if (i == 0 || i == size - 1) {
                    row.add(1);
                } else {
                    row.add(sol.get(rowNumber - 1).get(i - 1) + sol.get(rowNumber - 1).get(i));
                }
            }
            sol.add(new ArrayList(row));
        }
        return sol;
    }
}
