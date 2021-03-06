/*
*Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]

    20180904
    88
    easy
    O(m + n)
    o(1)
* */
package leetcode.SlidingWindow;

public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n - 1;
        int a = m - 1;
        int b = n - 1;
        while (index >= 0) {
            if (a < 0) {
                nums1[index--] = nums2[b--];
            } else if (b < 0) {
                nums1[index--] = nums1[a--];
            } else {
                nums1[index--] = nums1[a] > nums2[b] ? nums1[a--] : nums2[b--];
            }
        }
    }
}
