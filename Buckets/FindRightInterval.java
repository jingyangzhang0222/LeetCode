/*
* Given a set of intervals, for each of the interval i, check if there exists an interval j whose start point is bigger than or equal to the end point of the interval i, which can be called that j is on the "right" of i.

For any interval i, you need to store the minimum interval j's index, which means that the interval j has the minimum start point to build the "right" relationship for interval i. If the interval j doesn't exist, store -1 for the interval i. Finally, you need output the stored value of each interval as an array.

Note:
You may assume the interval's end point is always bigger than its start point.
You may assume none of these intervals have the same start point.
Example 1:
Input: [ [1,2] ]

Output: [-1]

Explanation: There is only one interval in the collection, so it outputs -1.
Example 2:
Input: [ [3,4], [2,3], [1,2] ]

Output: [-1, 0, 1]

Explanation: There is no satisfied "right" interval for [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point;
For [1,2], the interval [2,3] has minimum-"right" start point.
Example 3:
Input: [ [1,4], [2,3], [3,4] ]

Output: [-1, 2, -1]

Explanation: There is no satisfied "right" interval for [1,4] and [3,4].
For [2,3], the interval [3,4] has minimum-"right" start point.

    20190311
    436
    medium
    O(max(length, value range))
    O(value range)
* */
package leetcode.Buckets;

import java.util.Arrays;

public class FindRightInterval {
    public int[] findRightInterval(Interval[] intervals) {
//        step1: find max and min time stamp value among all values(starts and ends)
//        step2: build buckets based on value range
//        step3: post process buckets, so that buckets[i] stands for "among all start stamps,
//               the index of the minimum start time that is >= i"
//        step4: re-traverse the intervals, find the "the minimum start time that is >= current end time stamp"
//
//        example:
//        intervals: [[4, 6], [3, 5], [7, 12], [8, 10], [5, 9]]
//
//        After step1:
//        min: 3, max: 12
//
//        After step2:
//        [_, _, _, _, _, _, _, _, _, _]
//
//        After step3:
//        [1, 0, 4, 2, 2, 3, __, __, ___, ___](interval's index)
//        [3, 4, 5, 6, 7, 8, 9 ,10, 11, 12](time stamp value)
//
//        ↑____________________________↑
//        min_________________________max
//
//        Answer:
//        [2, 4, -1, -1, -1]
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (Interval i : intervals) {
            min = Math.min(min, i.start);
            max = Math.max(max, i.end);
        }
        int[] buckets = new int[max - min + 1];
        Arrays.fill(buckets, -1);
        for (int i = 0; i < intervals.length; i++) {
            buckets[intervals[i].start - min] = i;
        }
        for (int i = buckets.length - 2; i >= 0; i--) {
            if (buckets[i] == -1) {
                buckets[i] = buckets[i + 1];
            }
        }
        int[] res = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            res[i] = buckets[intervals[i].end - min];
        }
        return res;
    }
}

class Interval {
    int start;
    int end;
}
