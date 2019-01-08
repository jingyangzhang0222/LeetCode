/*
* Given a non-negative integer, you could swap two digits at most once to get the maximum valued number. Return the maximum valued number you could get.

Example 1:
Input: 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:
Input: 9973
Output: 9973
Explanation: No swap.
Note:
The given number is in the range [0, 108]

    20190108
    670
    medium
    O(n)
    O(1)
* */
package leetcode.Math;

public class MaximumSwap {
    public int maximumSwap(int num) {
        char[] n = String.valueOf(num).toCharArray();
        int[] count = new int[10];
        int[] lastIndex = new int[10];

        for (int i = 0; i < n.length; i++) {
            int dig = n[i] - '0';
            count[dig]++;
            lastIndex[dig] = i;
        }

        int max = 9;
        while (count[max] == 0 && max > 0) {
            max--;
        }

        for (int i = 0; i < n.length; i++) {
            int dig = n[i] - '0';
            if (dig != max) {
                swap(n, i, lastIndex[max]);
                break;
            } else if (--count[max] == 0) {
                while (count[max] == 0 && max > 0) {
                    max--;
                }
            }
        }

        return Integer.parseInt(new String(n));
    }

    private void swap(char[] n, int i, int j) {
        char tmp = n[i];
        n[i] = n[j];
        n[j] = tmp;
    }
}
