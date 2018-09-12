/*
* Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.

Example:

Input: 2
Output: 91
Explanation: The answer should be the total numbers in the range of 0 ≤ x < 100,
             excluding 11,22,33,44,55,66,77,88,99

    20180908
    357
    medium
    O(1 + 4 + 27 + ... + 10 ^ 10) -> O(1)
    O(10) -> O(1)
* */
package leetcode.NonGraphDFS;

import java.util.Arrays;

public class CountNumbersWithUniqueDigits {
    public static void main(String[] args) {
        CountNumbersWithUniqueDigits test = new CountNumbersWithUniqueDigits();
        System.out.println(test.countNumbersWithUniqueDigitsDP(2));
    }

    public int countNumbersWithUniqueDigitsDP(int n) {
        if (n == 0) {
            return 1;
        }

        int cnt = 10;
        int numberOfRemainingAvailable = 9;
        int previousValid = 9;
        for (int i = 2; i <= n && i <= 10; i++) {
            int currentValid = previousValid * numberOfRemainingAvailable;
            cnt += currentValid;
            previousValid = currentValid;
            numberOfRemainingAvailable--;
        }

        return cnt;
    }

    public int countNumbersWithUniqueDigits(int n) {
        int cnt = 0;
        for (int i = 1; i <= n && i <= 10; i++) {
            cnt += countUniqueWithDigitNumberOf(i);
        }
        return cnt;
    }

    private int countUniqueWithDigitNumberOf(int n) {
        boolean[] canAdd = new boolean[10];
        Arrays.fill(canAdd, true);
        int[] cnt = new int[1];
        dfs(1, n, canAdd, cnt);
        return cnt[0];
    }

    private void dfs(int index, int upperBound, boolean[] canAdd, int[] cnt) {
        //base case
        if (index > upperBound) {
            cnt[0]++;
            return;
        }
        // when can we add zero?
        // [0, 1), which upperBound == 1
        // or, upperBound != 1, and index != 1
        boolean canAddZero = (upperBound == 1) || (upperBound != 1 && index != 1);

        if (canAddZero && canAdd[0]) {
            canAdd[0] = false;
            dfs(index + 1, upperBound, canAdd, cnt);
            canAdd[0] = true;
        }

        for (int i = 1; i <= 9; i++) {
            if (canAdd[i]) {
                canAdd[i] = false;
                dfs(index + 1, upperBound, canAdd, cnt);
                canAdd[i] = true;
            }
        }
    }
}
