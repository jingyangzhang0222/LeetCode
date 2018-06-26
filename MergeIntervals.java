package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if (intervals.size() == 0) return res;
        Collections.sort(intervals, new MyComparator());
        Interval curInt = new Interval(intervals.get(0).start, intervals.get(0).end);
        for (Interval interval : intervals) {
            if (interval.start <= curInt.end) {
                curInt.end = Math.max(curInt.end, interval.end);
            } else {
                res.add(new Interval(curInt.start, curInt.end));
                curInt = new Interval(interval.start, interval.end);
            }
        }
        res.add(new Interval(curInt.start, curInt.end));
        return res;
    }

    static class MyComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval i1, Interval i2) {
            if (i1.start == i2.start) {
                return 0;
            }
            return i1.start < i2.start ? -1 : 1;
        }
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
