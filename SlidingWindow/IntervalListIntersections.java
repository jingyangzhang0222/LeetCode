/*
* Given two lists of closed intervals, each list of intervals is pairwise disjoint and in sorted order.

Return the intersection of these two interval lists.

(Formally, a closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.  The intersection of two closed intervals is a set of real numbers that is either empty, or can be represented as a closed interval.  For example, the intersection of [1, 3] and [2, 4] is [2, 3].)



Example 1:



Input: A = [[0,2],[5,10],[13,23],[24,25]], B = [[1,5],[8,12],[15,24],[25,26]]
Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
Reminder: The inputs and the desired output are lists of Interval objects, and not arrays or lists.


Note:

0 <= A.length < 1000
0 <= B.length < 1000
0 <= A[i].start, A[i].end, B[i].start, B[i].end < 10^9

    201902402
    986
    medium
    O(m + n)
    O(1)
* */
package leetcode.SlidingWindow;

import leetcode.MergeIntervals.Interval;

import java.util.ArrayList;
import java.util.List;

public class IntervalListIntersections {
    public Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        List<Interval> list = new ArrayList<>();
        int indexA = 0, indexB = 0;
        Interval prevInterval = null;
        while (indexA < A.length && indexB < B.length) {
            int largerStart = A[indexA].start > B[indexB].start ? A[indexA].start : B[indexB].start;
            int smallerEnd = A[indexA].end < B[indexB].end ? A[indexA].end : B[indexB].end;
            if (smallerEnd == A[indexA].end) {
                indexA++;
            } else {
                indexB++;
            }
            if (largerStart > smallerEnd) {
                prevInterval = null;
                continue;
            }

            // largerStart <= smallerEnd
            if (prevInterval != null && prevInterval.end >= largerStart) {
                prevInterval.end = smallerEnd;
                continue;
            }

            // prevInterval == null || prevInterval.end < largerStart
            Interval newInterval = new Interval(largerStart, smallerEnd);
            list.add(newInterval);
            prevInterval = newInterval;
        }

        return list.toArray(new Interval[0]);
    }
}
