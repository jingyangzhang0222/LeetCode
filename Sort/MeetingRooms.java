/*
* Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

Example 1:

Input: [[0,30],[5,10],[15,20]]
Output: false
Example 2:

Input: [[7,10],[2,4]]
Output: true

    20181010
    252
    medium
    O(nlogn)
    O(n)
* */
package leetcode.Sort;

import java.util.Arrays;

public class MeetingRooms {
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);
        Integer prevEndTime = null;
        for (Interval interval : intervals) {
            if (prevEndTime != null && prevEndTime > interval.start) {
                return false;
            }
            prevEndTime = interval.end;
        }
        return true;
    }


    public class Interval {
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
