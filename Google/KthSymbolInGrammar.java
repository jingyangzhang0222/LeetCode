/*
* On the first row, we write a 0. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.

Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).

Examples:
Input: N = 1, K = 1
Output: 0

Input: N = 2, K = 1
Output: 0

Input: N = 2, K = 2
Output: 1

Input: N = 4, K = 5
Output: 1

Explanation:
row 1: 0
row 2: 01
row 3: 0110
row 4: 01101001
Note:

N will be an integer in the range [1, 30].
K will be an integer in the range [1, 2^(N-1)].


    20190128
    779
    medium
    O(n)
    O(n)
* */
package leetcode.Google;

public class KthSymbolInGrammar {
    public int kthGrammar(int N, int K) {
        if (K == 1) {
            return 0;
        }

        int lo = 1 << (N - 2);
        if (K <= lo) {
            return kthGrammar(N - 1, K);
        } else {
            return 1 - kthGrammar(N - 1, K - lo);
        }
    }
}
