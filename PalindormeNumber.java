/*
9
easy
*/

package leetcode;

public class PalindormeNumber {
    public boolean isPalindrome(int x) {
        if (x < 0 || (x != 0 && x % 10 == 0)) return false;
        //compare half of the integer
        //1 2 3 4 3 2 1 -> 1 2 3 4, 1 2 3
        //1 2 3 3 2 1 -> 1 2 3, 1 2 3
        int tmp = 0;
        while (x > tmp) {
            tmp = 10 * tmp + x % 10;
            x /= 10;
        }
        if (x == tmp) return true;
        tmp /= 10;
        return x == tmp;
    }
}
