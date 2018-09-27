/*
* Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example:

MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3

    20180925
    346
    easy
    O(1)
    O(size)

    Queue -> circular array
* */
package leetcode.Google;

public class MovingAverageFromDataStream {
    private final int[] nums;
    private int sum;
    private int cnt;
    /** Initialize your data structure here. */
    public MovingAverageFromDataStream(int size) {
        nums = new int[size];
        cnt = 0;
    }

    public double next(int val) {
        int index = cnt++ % nums.length;
        sum -= nums[index];
        sum += val;
        nums[index] = val;
        return (double)(sum) / (double)(Math.min(nums.length, cnt));
    }
}
