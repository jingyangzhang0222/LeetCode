/*
* Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1

    20181010
    253
    medium
    O(nlogn)
    O(n)
* */
package leetcode.Sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import leetcode.Sort.MeetingRooms.Interval;

public class MeetingRoomsII {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        PriorityQueue<Interval> pq = new PriorityQueue<>(11, new MyComparator());
        Arrays.sort(intervals);
        pq.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int earliestEnd = pq.peek().end;
            if (earliestEnd <= intervals[i].start) {
                Interval merged = pq.poll();
                merged.end = intervals[i].end;
                pq.offer(merged);
            } else {
                pq.offer(intervals[i]);
            }
        }
        return pq.size();
    }

    static class MyComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval i1, Interval i2) {
            if (i1.end != i2.end) {
                return i1.end < i2.end ? -1 : 1;
            }
            return 0;
        }
    }
}
