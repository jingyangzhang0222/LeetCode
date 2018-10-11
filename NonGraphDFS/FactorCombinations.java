/*
* Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:

You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Example 1:

Input: 1
Output: []
Example 2:

Input: 37
Output:[]
Example 3:

Input: 12
Output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
Example 4:

Input: 32
Output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]

    20181010
    254
    medium
    O()
    O()
* */
package leetcode.NonGraphDFS;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> sol = new ArrayList<>();
        dfs(sol, new ArrayList<Integer>(), 2, n);
        return sol;
    }

    private void dfs(List<List<Integer>> sol, List<Integer> subsol, int start, int n) {
        if (n == 1) {
            if (subsol.size() >= 2) {
                sol.add(new ArrayList(subsol));
            }
            return;
        }

        if (n >= 2 && subsol.size() != 0) {
            subsol.add(n);
            dfs(sol, subsol, n, 1);
            subsol.remove(subsol.size() - 1);
        }

        for (int i = start; i * i <= n; i++) {
            if (n % i == 0) {
                subsol.add(i);
                dfs(sol, subsol, i, n / i);
                subsol.remove(subsol.size() - 1);
            }
        }
    }
}
