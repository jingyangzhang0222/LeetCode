/*
* Write a function that takes an unsigned integer and returns the number of '1' bits it has (also known as the Hamming weight).

Example 1:

Input: 11
Output: 3
Explanation: Integer 11 has binary representation 00000000000000000000000000001011
Example 2:

Input: 128
Output: 1
Explanation: Integer 128 has binary representation 00000000000000000000000010000000

    191
    easy
    easy
    O(1)
    O(1)
* */
package leetcode.Bit;

public class NumberOf1Bits {
    public int hammingWeight(int n) {
        int cnt = 0;
        for (int i = 0; i <= 31; i++) {
            cnt += (1 & (n>>i));
        }
        return cnt;
    }
}