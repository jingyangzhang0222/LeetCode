/*
* Given a circular array (the next element of the last element is the first element of the array), print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn't exist, output -1 for this number.

Example 1:
Input: [1,2,1]
Output: [2,-1,2]
Explanation: The first 1's next greater number is 2;
The number 2 can't find next greater number;
The second 1's next greater number needs to search circularly, which is also 2.
Note: The length of given array won't exceed 10000.

    20181003
    503
    medium
    O(n)
    O(n)
* */
package leetcode.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        // brute force: n ^ 2
        Deque<Integer> s = new ArrayDeque<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && nums[s.peekFirst()] <= nums[i]) {
                s.pollFirst();
            }
            res[i] = s.isEmpty() ? -1 : nums[s.peekFirst()];
            s.offerFirst(i);
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            while (!s.isEmpty() && nums[s.peekFirst()] <= nums[i]) {
                s.pollFirst();
            }
            res[i] = s.isEmpty() ? -1 : nums[s.peekFirst()];
            s.offerFirst(i);
        }
        return res;
    }
}
