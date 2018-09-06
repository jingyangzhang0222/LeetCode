/*
* Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.

Example:

int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8
Note:
You may assume that nums' length ≥ k-1 and k ≥ 1.

    20180905
    703
    easy
    O(logn)
    O(n)
* */
package leetcode.kth;

import java.util.Collections;
import java.util.PriorityQueue;

public class KthLargestElementInAStream {
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;
    private final int limit;

    public KthLargestElementInAStream(int k, int[] nums) {
        limit = k;
        minHeap = new PriorityQueue(limit);
        maxHeap = new PriorityQueue(Collections.reverseOrder());
        for (int num : nums) {
            update(num);
        }
    }

    public int add(int val) {
        update(val);
        return minHeap.peek();
    }

    private void update(int num) {
        if (minHeap.size() < limit) {
            minHeap.offer(num);
        } else if (num > minHeap.peek()) {
            maxHeap.offer(minHeap.poll());
            minHeap.offer(num);
        } else {
            maxHeap.offer(num);
        }
    }
}
