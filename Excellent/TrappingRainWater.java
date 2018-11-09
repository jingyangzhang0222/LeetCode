/*
* Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

    20181009
    42
    hard
    O(n)
    O(n)
* */
package leetcode.Excellent;

import java.util.ArrayDeque;
import java.util.Deque;

public class TrappingRainWater {
    public int trapTwoPointers(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        int left = 0, right = height.length - 1;
        int leftMax = height[left++], rightMax = height[right--];

        int sum = 0;
        while (left <= right) {
            if (leftMax <= rightMax) {
                int heightDiff = Math.min(leftMax, rightMax) - height[left];
                sum += Math.max(0, heightDiff);
                leftMax = Math.max(leftMax, height[left]);
                left++;
            } else {
                int heightDiff = Math.min(leftMax, rightMax) - height[right];
                sum += Math.max(0, heightDiff);
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }
        }

        return sum;
    }

    public int trapStack(int[] height) {
        Deque<Integer> s = new ArrayDeque<>();
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            while (!s.isEmpty() && height[s.peekLast()] < height[i]) {
                int prevIndex = s.pollLast();
                if (s.isEmpty()) {
                    break;
                }
                int width = i - s.peekLast() - 1;
                int heightDiff = Math.min(height[i], height[s.peekLast()]) - height[prevIndex];
                sum += width * heightDiff;
            }
            s.offerLast(i);
        }
        return sum;
    }

    public int trapDP(int[] height) {
        if (height.length <= 2) {
            return 0;
        }
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];
        maxLeft[0] = height[0];
        maxRight[height.length - 1] = height[height.length - 1];
        for (int i = 1; i < height.length - 1; i++) {
            int j = height.length - 1 - i;
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
            maxRight[j] = Math.max(maxRight[j + 1], height[j + 1]);
        }

        int sum = 0;
        for (int i = 1; i < height.length - 1; i++) {
            sum += Math.max(0, Math.min(maxLeft[i], maxRight[i]) - height[i]);
        }
        return sum;
    }
}
