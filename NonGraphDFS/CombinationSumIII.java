/*
* Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

All numbers will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]

    20180905
    216
    medium
    O(2 ^ 9)
    O(9)
* */
package leetcode.NonGraphDFS;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> sol = new ArrayList<>();
        dfsHelper(sol, new ArrayList<>(), n, 1, k);
        return sol;
    }

    private void dfsHelper(List<List<Integer>> sol, List<Integer> subsol, int target, int num, int k) {
        //base case
        if (target <= 0) {
            if (target == 0 && subsol.size() == k) {
                sol.add(new ArrayList(subsol));
            }
            return;
        }

        if (num > 9 || subsol.size() > k) {
            return;
        }

        // do not add
        dfsHelper(sol, subsol, target, num + 1, k);

        //  add
        subsol.add(num);
        dfsHelper(sol, subsol, target - num, num + 1, k);
        subsol.remove(subsol.size() - 1);
    }
}
