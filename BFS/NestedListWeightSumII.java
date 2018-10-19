/*
* Given a nested list of integers, return the sum of all integers in the list weighted by their depth.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Different from the previous question where weight is increasing from root to leaf, now the weight is defined from bottom up. i.e., the leaf level integers have weight 1, and the root level integers have the largest weight.

Example 1:

Input: [[1,1],2,[1,1]]
Output: 8
Explanation: Four 1's at depth 1, one 2 at depth 2.
Example 2:

Input: [1,[4,[6]]]
Output: 17
Explanation: One 1 at depth 3, one 4 at depth 2, and one 6 at depth 1; 1*3 + 4*2 + 6*1 = 17.


    20181016
    364
    medium
    O(n)
    O(n)
* */
package leetcode.BFS;

import leetcode.NestedInteger;
import leetcode.NonGraphDFS.NestedListWeightSum;

import java.util.ArrayList;
import java.util.List;

public class NestedListWeightSumII {
    private int maxDepth = 0;

    public int depthSumInverseBFS(List<NestedInteger> nestedList) {
        int unweighted = 0, weighted = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextLevel = new ArrayList<>();
            for (NestedInteger ni : nestedList) {
                if (ni.isInteger())
                    unweighted += ni.getInteger();
                else
                    nextLevel.addAll(ni.getList());
            }
            weighted += unweighted;
            nestedList = nextLevel;
        }
        return weighted;
    }

    public int depthSumInverse(List<NestedInteger> nestedList) {

        for (NestedInteger ni : nestedList) {
            getDepth(ni, 1);
        }

        int sum = 0;
        for (NestedInteger ni : nestedList) {
            sum += helper(ni, 1);
        }

        return sum;
    }

    private int helper(NestedInteger ni, int degree) {
        if (ni.isInteger()) {
            return ni.getInteger() * (maxDepth - degree + 1);
        }

        int sum = 0;
        for (NestedInteger child : ni.getList()) {
            sum += helper(child, degree + 1);
        }
        return sum;
    }

    private void getDepth(NestedInteger ni, int degree) {
        if (ni.isInteger()) {
            maxDepth = Math.max(maxDepth, degree);
            return;
        }

        for (NestedInteger child : ni.getList()) {
            getDepth(child, degree + 1);
        }
    }
}
