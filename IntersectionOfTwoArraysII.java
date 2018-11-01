/*
* Given two arrays, write a function to compute their intersection.

Example 1:

Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:

Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Note:

Each element in the result should appear as many times as it shows in both arrays.
The result can be in any order.
Follow up:

What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?

    20181030
    350
    easy
    O(m + n)
    O(min(m + n))
* */
package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionOfTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        //1. hashmap O(m + n), O(min(m, n))
        //2.1 sort longer, mlogm + log(m) * n, (m >> n, bad)
        //2.2 sort shorter,nlogn + logn * m (m >> n, good)
        //3. already sorted, logm * n (m >> n)

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        for (int num : nums2) {
            int remain = map.getOrDefault(num, 0);
            if (remain > 0) {
                res.add(num);
                if (remain - 1 >= 0) {
                    map.put(num, remain - 1);
                }
            }
        }

        return toIntArray(res.toArray(new Integer[0]));
    }

    private int[] toIntArray(Integer[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }

        return res;
    }
}
