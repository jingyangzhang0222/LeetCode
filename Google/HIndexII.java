/*
* Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

Example:

Input: citations = [0,1,3,5,6]
Output: 3
Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had
             received 0, 1, 3, 5, 6 citations respectively.
             Since the researcher has 3 papers with at least 3 citations each and the remaining
             two with no more than 3 citations each, her h-index is 3.
Note:

If there are several possible values for h, the maximum one is taken as the h-index.

Follow up:

This is a follow up problem to H-Index, where citations is now guaranteed to be sorted in ascending order.
Could you solve it in logarithmic time complexity?

    20181112
    275
    medium
    O(logn)
    O(1)
* */
package leetcode.Google;

public class HIndexII {
    public int hIndex(int[] citations) {
        if (citations.length == 0) {
            return 0;
        }
        int left = 0, right = citations.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            int can = citations[mid];
            int count = citations.length - mid;

            if (can == count) {
                return can;
            } else if (count < can) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int count = citations.length - left;
        if (citations[left] >= count) {
            return count;
        } else {
            return citations[left];
        }
    }
}
