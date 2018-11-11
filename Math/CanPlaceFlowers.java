/*
* Suppose you have a long flowerbed in which some of the plots are planted and some are not. However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.

Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty), and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.

Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: True
Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: False
Note:
The input array won't violate no-adjacent-flowers rule.
The input array size is in the range of [1, 20000].
n is a non-negative integer which won't exceed the input array size.

    20181110
    605
    easy
    O(n)
    O(1)
* */
package leetcode.Math;

public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int s = -1;
        // (1) (0) 0 0 1 0 0 1 0 0 1 0 0 (0) (1)
        // (0) 0 1
        // (1) (0) 0 0 0 0 (0) (1)
        for (int f = 0; f <= flowerbed.length; f++) {
            if (f == flowerbed.length || flowerbed[f] == 1) {
                // 0 -> 0
                // 1 -> 0
                // 2 -> 0
                // 3 -> 1
                // 4 -> 1
                // 5 -> 2
                // 6 -> 2
                if (f == flowerbed.length) f++;
                n -= (f - s - 1) / 2;
                if (n <= 0) return true;
                s = f + 1;
            }
        }
        return false;
    }
}
