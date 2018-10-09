/*
* Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

Note that the row index starts from 0.


In Pascal's triangle, each number is the sum of the two numbers directly above it.

Example:

Input: 3
Output: [1,3,3,1]
Follow up:

Could you optimize your algorithm to use only O(k) extra space?

    20181005
    119
    easy
    O(n ^ 2)
    O(k)
* */
package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleII {
    public List<Integer> getRow(int n) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for (int size = 2; size <= n + 1; size++) {
            List<Integer> prevRow = row;
            row = new ArrayList<Integer>();
            for (int col = 0; col < size; col++) {
                if (col == 0 || col == size - 1) {
                    row.add(1);
                } else {
                    int num1 = prevRow.get(col - 1);
                    int num2 = prevRow.get(col);
                    row.add(num1 + num2);
                }
            }
        }
        return row;
    }
}
