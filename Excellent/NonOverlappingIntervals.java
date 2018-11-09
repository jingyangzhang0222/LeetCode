/*
* Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note:
You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
Example 1:
Input: [ [1,2], [2,3], [3,4], [1,3] ]

Output: 1

Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:
Input: [ [1,2], [1,2], [1,2] ]

Output: 2

Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:
Input: [ [1,2], [2,3] ]

Output: 0

Explanation: You don't need to remove any of the intervals since they're already non-overlapping.

    20181104
    435
    medium
    O(nlogn)
    O(n)
* */
package leetcode.Excellent;

import java.util.Arrays;

public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(Interval[] intervals) {
        Arrays.sort(intervals, (i1, i2) -> i1.end - i2.end);
        int endTime = -1;
        int count = 0;

        for (Interval i : intervals) {
            if (endTime == -1 || i.start >= endTime) {
                endTime = i.end;
                count++;
            }
        }

        return intervals.length - count;
    }

    private class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
}
