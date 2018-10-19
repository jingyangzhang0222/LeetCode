/*
* Reverse bits of a given 32 bits unsigned integer.

Example:

Input: 43261596
Output: 964176192
Explanation: 43261596 represented in binary as 00000010100101000001111010011100,
             return 964176192 represented in binary as 00111001011110000010100101000000.
Follow up:
If this function is called many times, how would you optimize it?

    20181014
    190
    easy
    O(1)
    O(1)
* */
package leetcode.Bit;

public class ReverseBits {
    public int reverseBits(int n) {
        // bit setter:   n |= (1 << k)
        // bit resetter: n &= (~(1 << k))

        for (int i = 0; i < 16; i++) {
            // 0, 31
            // 1, 30, ...
            int leftBit = (n >> (31 - i)) & 1;
            int rightBit = (n >> i) & 1;
            if (leftBit == 1) {
                n |= 1 << i;
            } else {
                n &= (~(1 << i));
            }

            if (rightBit == 1) {
                n |= 1 << (31 - i);
            } else {
                n &= (~(1 << (31 - i)));
            }
        }

        return n;
    }
}
