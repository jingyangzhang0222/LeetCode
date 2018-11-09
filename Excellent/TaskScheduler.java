/*
*
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.



Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.


Note:

The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].

    20181108
    621
    medium
    O(n)
    O(1)
*/
package leetcode.Excellent;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class TaskScheduler {
    public static void main(String[] args) {
        System.out.println(new TaskScheduler().leastIntervalPQ(new char[]{'A', 'A', 'A', 'B', 'B', 'B'}, 2));
    }

    public int leastIntervalMath(char[] tasks, int n) {
        int[] map = new int[26];
        int max = 0, maxCount = 0;
        for (char t : tasks) {
            if (++map[t - 'A'] >= max) {
                maxCount += map[t - 'A'] == max ? 1 : -(maxCount - 1);
                max = map[t - 'A'];
            }
        }
        return Math.max(tasks.length, (max - 1) * (n + 1) + maxCount);
    }

    public int leastIntervalPQ(char[] tasks, int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int[] map = new int[26];
        for (char t : tasks) {
            map[t - 'A']++;
        }
        for (int i = 0; i < 26; i++) {
            if (map[i] > 0) {
                pq.offer(map[i]);
            }
        }
        int time = 0;
        while (!pq.isEmpty()) {
            Queue<Integer> helper = new ArrayDeque<>();
            for (int i = 0; i <= n; i++) {
                if (pq.isEmpty()) {
                    if (helper.isEmpty()) {
                        return time;
                    } else {
                        continue;
                    }
                }
                time++;
                int taskLeft = pq.poll();
                if (--taskLeft > 0) {
                    helper.offer(taskLeft);
                }
            }
            while (!helper.isEmpty()) {
                pq.offer(helper.poll());
            }
        }
        return time;
    }
}
