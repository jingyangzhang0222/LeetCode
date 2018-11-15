/*
* Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104

    20181114
    658
    medium
    O(logn + k)
    O(1)
* */
package leetcode.BinaryrSearch;

import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements {
    public static void main(String[] args) {
        for (int num : new FindKClosestElements().findClosestElements(new int[]{1, 2, 3, 4, 5}, 4, -1)) {
            System.out.println(num);
        }
    }

    public List<Integer> findClosestElements(int[] nums, int k, int x) {
        int left = 0, right = nums.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == x) {
                left = mid;
                right = mid;
                break;
            } else if (nums[mid] < x) {
                left = mid;
            } else {
                right = mid;
            }
        }

        int start = Math.abs(x - nums[left]) < Math.abs(x - nums[right]) ? left : right;
        left = start;
        right = start;
        int cnt = 1;
        while (cnt < k) {
            if (left == 0) {
                right++;
            } else if (right == nums.length - 1) {
                left--;
            } else if (Math.abs(x - nums[left - 1]) <= Math.abs(x - nums[right + 1])) {
                left--;
            } else {
                right++;
            }
            cnt++;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            res.add(nums[i]);
        }
        return res;
    }
}
