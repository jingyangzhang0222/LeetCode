/*
* Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:

HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301);
Follow up:
What if the number of hits per second could be very large? Does your design scale?

    20181115
    362
    medium
    hit: amortized O(1), getHits: O(1)
    O(300) = O(1)
* */
package leetcode.Design;

import java.util.ArrayDeque;
import java.util.Deque;

public class DesignHitCounter {
    private final Deque<int[]> q;
    private int totalCount;

    /** Initialize your data structure here. */
    public DesignHitCounter() {
        q = new ArrayDeque<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        totalCount++;
        update(timestamp);
        if (!q.isEmpty() && q.peekLast()[0] == timestamp) {
            q.peekLast()[1]++;
        } else {
            q.offer(new int[]{timestamp, 1});
        }
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        update(timestamp);
        return totalCount;
    }

    /** Delete old data */
    private void update(int timestamp) {
        while (!q.isEmpty() && timestamp - q.peekFirst()[0] >= 300) {
            totalCount -= q.pollFirst()[1];
        }
    }
}
