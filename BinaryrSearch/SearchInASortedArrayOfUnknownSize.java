/*
* Given an integer array sorted in ascending order, write a function to search target in nums.  If target exists, then return its index, otherwise return -1. However, the array size is unknown to you. You may only access the array using an ArrayReader interface, where ArrayReader.get(k) returns the element of the array at index k (0-indexed).

You may assume all integers in the array are less than 10000, and if you access the array out of bounds, ArrayReader.get will return 2147483647.



Example 1:

Input: array = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
Example 2:

Input: array = [-1,0,3,5,9,12], target = 2
Output: -1
Explanation: 2 does not exist in nums so return -1

    20181114
    702
    medium
    O(logn)
    O(1)
* */
package leetcode.BinaryrSearch;

public class SearchInASortedArrayOfUnknownSize {
    public int search(ArrayReader reader, int target) {
        int left = 0, right = 1;
        final int M = Integer.MAX_VALUE;
        while (reader.get(right) != M && reader.get(right) < target) {
            left = right + 1;
            right *= 10;
        }

        // right: M || >= target

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (reader.get(mid) == target) {
                return mid;
            } else if (reader.get(mid) > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return reader.get(left) == target ? left : -1;
    }

    private class ArrayReader{
        int get(int i) {
            return 1;
        }
    }
}
