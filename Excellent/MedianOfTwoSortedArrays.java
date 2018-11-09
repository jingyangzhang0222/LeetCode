package leetcode.Excellent;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        System.out.println(new MedianOfTwoSortedArrays().findMedianSortedArrays(
                new int[]{5},
                new int[]{0, 1}));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] s = nums1.length <= nums2.length ? nums1 : nums2;
        int[] l = s == nums1 ? nums2 : nums1;
        int leftSize = (s.length + l.length + 1) / 2;
        if (s.length == 0) {
            return l.length % 2 == 1 ? (double) l[leftSize - 1] : (l[leftSize - 1] + l[leftSize]) / (2.0);
        }
        int lo = 0, hi = s.length - 1;
        while (lo <= hi) {
            int sLast = lo + (hi - lo) / 2;
            int lLast = leftSize - (sLast + 1) - 1;
            if (sLast + 1 >= s.length || lLast < 0 || lLast + 1 >= l.length) {
                break;
            }
            int sLeft = s[sLast], sRight = s[sLast + 1];
            int lLeft = l[lLast], lRight = l[lLast + 1];

            if (sLeft <= lRight && lLeft <= sRight) {
                break;
            } else if (sLeft > lRight) {
                hi = sLast - 1;
            } else {
                lo = sLast + 1;
            }
        }
        int sLast = lo + (hi - lo) / 2;
        int lLast = leftSize - (sLast + 1) - 1;
        int leftMax = (sLast >= 0 && lLast >= 0) ? Math.max(s[sLast], l[lLast]) : sLast < 0 ? l[lLast] : s[sLast];
        int rightMin = (sLast + 1 < s.length && lLast + 1 < l.length) ?
                Math.min(s[sLast + 1], l[lLast + 1]) :
                sLast + 1 < s.length ?
                        s[sLast + 1] : l[lLast + 1];
        return ((s.length + l.length) % 2 == 1) ? (double) leftMax : (leftMax + rightMin) / 2.0;
    }
}
