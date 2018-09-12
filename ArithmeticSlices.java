/*
* A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequence:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.


Example:

A = [1, 2, 3, 4]

return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.

    20180911
    413
    medium
    O(n)
    O(1)
* */
package leetcode;

public class ArithmeticSlices {
    public static void main(String[] args) {
        ArithmeticSlices test = new ArithmeticSlices();
        System.out.println(test.numberOfArithmeticSlices(new int[]{1, 2, 3, 4, 6, 1, 2, 3, 4, 5, 6, 7}));
    }

    public int numberOfArithmeticSlices(int[] nums) {
        // [1 2 3 4 5]
        // 12345 ..............1
        // 1234 2345...........2
        // 123 234 345.........3
        // 1 + 2 +... + (len - 2)
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int cnt = 0;
        int step = Integer.MAX_VALUE;
        int first = 0;
        int last = 0;
        while (last < nums.length) {
            if (last == first) {
                last++;
            } else if (last == first + 1) {
                step = nums[last] - nums[first];
                last++;
            } else { //last >= first + 2, at least elements
                while (last < nums.length && nums[last] - nums[last - 1] == step) {
                    last++;
                }
                //last == length || nums[last] - nums[last - 1] != step
                int len = last - first;
                cnt += (1 + len - 2) * (len - 2) / 2;
                first = last - 1;//////////////////////////////////////the last ele of previous array can still contribute t next array
                // 1 2 3 4 6 8
                step = Integer.MAX_VALUE;
            }
        }
        return cnt;
    }
}
