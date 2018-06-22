/*
* Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3

    20180620
    96
    medium
    O(n ^ 2)
    O(n)
* */
package leetcode.DP;

public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        int[] M = new int[n + 1];
        M[0] = 1;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int leftSize = 0; leftSize <= i - 1; leftSize++) {
                int rightSize = i - 1 - leftSize;
                sum += M[leftSize] * M[rightSize];
            }
            M[i] = sum;
        }
        return M[n];
    }
}