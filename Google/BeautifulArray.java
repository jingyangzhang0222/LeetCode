/*
* For some fixed N, an array A is beautiful if it is a permutation of the integers 1, 2, ..., N, such that:

For every i < j, there is no k with i < k < j such that A[k] * 2 = A[i] + A[j].

Given N, return any beautiful array A.  (It is guaranteed that one exists.)



Example 1:

Input: 4
Output: [2,1,4,3]
Example 2:

Input: 5
Output: [3,1,2,5,4]


Note:

1 <= N <= 1000

    20181113
    932
    medium
    O(nlogn)
    O(logn)
* */
package leetcode.Google;

public class BeautifulArray {
    public int[] beautifulArray(int N) {
        if (N == 0) return new int[0];
        if (N == 1) {
            return new int[]{1};
        }
        if (N == 2) {
            return new int[]{1, 2};
        }

        int[] odd = beautifulArray((N + 1) / 2);
        int[] even = beautifulArray(N / 2);

        int[] res = new int[N];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < N; i++) {
            res[i] = index1 < odd.length ? 2 * odd[index1++] - 1 : even[index2++] * 2;
        }
        return res;
    }
}
