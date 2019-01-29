/*
* There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5

    20190109
    4
    hard
    O(log(min(m, n)))
    O(1)
* */
package leetcode.Excellent;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(
                new int[]{5, 6},
                new int[]{1, 2, 3, 4, 7}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] s = nums1.length <= nums2.length ? nums1 : nums2;
        int[] l = s == nums1 ? nums2 : nums1;
        int leftSize = (s.length + l.length + 1) / 2;
        if (s.length == 0) {
            return l.length % 2 == 1 ? (double) l[leftSize - 1] : (l[leftSize - 1] + l[leftSize]) / (2.0);
        }

        if ((s.length + l.length) % 2 == 0) {
            return even(s, l, leftSize);
        } else {
            return odd(s, l, leftSize);
        }
    }

    private double even(int[] s, int[] l, int leftSize) {
        // 1 2 | 4 5
        if (s[s.length - 1] <= l[0] || l[l.length - 1] <= s[0]) {
            return cornerCase(s, l, true, leftSize);
        }

        int lo = 0, hi = s.length - 1;
        while (lo <= hi) {
            int sMid = lo + (hi - lo) / 2;
            int lMid = leftSize - sMid - 2;
            int sLeftCan = s[sMid], sRightCan = sMid == s.length - 1 ? Integer.MAX_VALUE : s[sMid + 1];
            int lLeftCan = l[lMid], lRightCan = lMid == l.length - 1 ? Integer.MAX_VALUE : l[lMid + 1];
            if (sLeftCan <= lRightCan && lLeftCan <= sRightCan) {
                return (Math.max(sLeftCan, lLeftCan) + Math.min(sRightCan, lRightCan)) / 2.0;
            } else if (sLeftCan > lRightCan) {
                hi = sMid - 1;
            } else {
                lo = sMid + 1;
            }
        }

        if (hi == -1) {
            return (l[leftSize - 1] + Math.min(l[leftSize], s[0])) / 2.0;
        }

        int sMid = lo;
        int lMid = leftSize - lo - 2;
        int sLeftCan = s[sMid], sRightCan = sMid == s.length - 1 ? Integer.MAX_VALUE : s[sMid + 1];
        int lLeftCan = l[lMid], lRightCan = lMid == l.length - 1 ? Integer.MAX_VALUE : l[lMid + 1];
        return (Math.max(sLeftCan, lLeftCan) + Math.min(sRightCan, lRightCan)) / 2.0;
    }

    private double odd(int[] s, int[] l, int leftSize) {
        if (s[s.length - 1] <= l[0] || l[l.length - 1] <= s[0]) {
            return cornerCase(s, l, false, leftSize);
        }

        int lo = 0, hi = s.length - 1;
        while (lo <= hi) {
            int sMid = lo + (hi - lo) / 2;
            int lMid = leftSize - sMid - 2;
            int sLeftCan = s[sMid], sRightCan = sMid == s.length - 1 ? Integer.MAX_VALUE : s[sMid + 1];
            int lLeftCan = l[lMid], lRightCan = lMid == l.length - 1 ? Integer.MAX_VALUE : l[lMid + 1];
            if (sLeftCan <= lRightCan && lLeftCan <= sRightCan) {
                return (double)Math.max(sLeftCan, lLeftCan);
            } else if (sLeftCan > lRightCan) {
                hi = sMid - 1;
            } else {
                lo = sMid + 1;
            }
        }

        if (hi == -1) {
            return (double)l[leftSize - 1];
        }

        int sMid = lo;
        int lMid = leftSize - sMid - 2;

        return (double)Math.max(s[sMid], l[lMid]);
    }

    private double cornerCase(int[] s, int[] l, boolean even, int leftSize) {
        if (s.length == l.length) {
            // 1 2 3 | 4 5 6
            return s[s.length - 1] <= l[0] ?
                    (s[s.length - 1] + l[0]) / 2.0 :
                    (l[l.length - 1] + s[0]) / 2.0;
        }

        // s.length < l.length
        if (l[l.length - 1] <= s[0]) { // 1 2 3 4 | 5 6
            return even ?
                    (l[leftSize - 1] + l[leftSize]) / 2.0 :
                    l[leftSize - 1];
        } else {
            return even ?
                    (l[leftSize - s.length - 1] + l[leftSize - s.length]) / 2.0 :
                    l[leftSize - s.length - 1];
        }
    }
}
