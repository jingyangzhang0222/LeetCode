/*
* Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.

Example:

Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
Note:

1 is typically treated as an ugly number.
n does not exceed 1690.

    20180919
    264
    medium
    O(n)
    O(1)
* */
package leetcode.Queue;

public class UglyNumberII {
    public static void main(String[] args) {
        UglyNumberII test = new UglyNumberII();
        System.out.println(test.nthUglyNumber(10));
    }
    public int nthUglyNumber(int n) {
        int[] nums = new int[n];
        nums[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        for (int i = 1; i < nums.length; i++) {
            nums[i] = Math.min(Math.min(nums[index2] * 2, nums[index3] * 3), nums[index5] * 5);
            if (nums[index2] * 2 == nums[i]) {
                index2++;
            }
            if (nums[index3] * 3 == nums[i]) {
                index3++;
            }
            if (nums[index5] * 5 == nums[i]) {
                index5++;
            }
        }
        return nums[n - 1];
    }
}
