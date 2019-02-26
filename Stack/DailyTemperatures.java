/*
* Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.

For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2, 1, 1, 0, 0].

Note: The length of temperatures will be in the range [1, 30000]. Each temperature will be an integer in the range [30, 100].

    20190223
    739
    medium
    O(n)
    O(n)
* */
package leetcode.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] T) {
        // go through the whole array from right to left, and store the index to a stack such that:
        // from the bottom to the top, according element is in descending order
        // when new element(index) comes,
        // 1. pop until empty or the top element is larger, which is, pop the smaller and equal elements
        // 2. calculate the distance, record
        // 3. push

        Deque<Integer> s = new ArrayDeque<>();
        int[] res = new int[T.length];
        for (int i = T.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && T[s.peekLast()] <= T[i]) {
                s.pollLast();
            }

            res[i] = s.isEmpty() ? 0 : s.peekLast() - i;
            s.offerLast(i);
        }

        return res;
    }
}
