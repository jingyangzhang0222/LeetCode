/*
* We have two integer sequences A and B of the same non-zero length.

We are allowed to swap elements A[i] and B[i].  Note that both elements are in the same index position in their respective sequences.

At the end of some number of swaps, A and B are both strictly increasing.  (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... < A[A.length - 1].)

Given A and B, return the minimum number of swaps to make both sequences strictly increasing.  It is guaranteed that the given input always makes it possible.

Example:
Input: A = [1,3,5,4], B = [1,2,3,7]
Output: 1
Explanation:
Swap A[3] and B[3].  Then the sequences are:
A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
which are both strictly increasing.
Note:

A, B are arrays with the same length, and that length will be in the range [1, 1000].
A[i], B[i] are integer values in the range [0, 2000].

    20181120
    801
    medium
    O(n)
    O(1)
* */
package leetcode.DP;

public class MinimumSwapsToMakeSequencesIncreasing {
    public int minSwap(int[] A, int[] B) {
        int notSwap = 0, swap = 1;

        for (int i = 1; i < A.length; i++) {
            int pNotSwap = notSwap;
            int pSwap = swap;
            swap = 1001;
            notSwap = 1001;
            if (pNotSwap != 1001) {
                if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
                    notSwap = Math.min(notSwap, pNotSwap);
                }
                if (B[i] > A[i - 1] && A[i] > B[i - 1]) {
                    swap = Math.min(swap, pNotSwap + 1);
                }
            }

            if (pSwap != 1001) {
                if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
                    notSwap = Math.min(notSwap, pSwap);
                }
                if (B[i] > B[i - 1] && A[i] > A[i - 1]) {
                    swap = Math.min(swap, pSwap + 1);
                }
            }
        }

        return Math.min(swap, notSwap);
    }
}
