/*
* An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

Example:

Input:
[
  "0010",
  "0110",
  "0100"
]
and x = 0, y = 2

Output: 6

    20180926
    302
    hard
    O(mlogn + nlogm)
    O(1)
* */
package leetcode.Google;

public class SmallestRectangleEnclosingBlackPixels {
    public int minArea(char[][] image, int x, int y) {
        int up = searchByRow(0, x, image, true);
        int down = searchByRow(x, image.length - 1, image, false);
        int left = searchByCol(0, y, image, true);
        int right = searchByCol(y, image[0].length - 1, image, false);
        return (down - up + 1) * (right - left + 1);
    }

    private int searchByRow (int lo, int hi, char[][] image, boolean upwards) {
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            boolean midHasOne = hasOne(image[mid]);

            if (upwards) {
                if (midHasOne) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (midHasOne) {
                    lo = mid;
                    if (lo == hi - 1) break;
                } else {
                    hi = mid - 1;
                }
            }
        }
        if (lo == hi - 1) {
            // downwards
            return hasOne(image[hi]) ? hi : lo;
        }
        return lo;
    }

    private int searchByCol (int lo, int hi, char[][] image, boolean leftwards) {
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            boolean midHasOne = false;
            for (int i = 0; i < image.length; i++) {
                if (image[i][mid] == '1') {
                    midHasOne = true;
                    break;
                }
            }

            if (leftwards) {
                if (midHasOne) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (midHasOne) {
                    lo = mid;
                    if (lo == hi - 1) break;
                } else {
                    hi = mid - 1;
                }
            }
        }

        if (lo == hi - 1) {
            // rightwards
            for (int i = 0; i < image.length; i++) {
                if (image[i][hi] == '1') {
                    return hi;
                }
            }
            return lo;
        }
        return lo;
    }

    private boolean hasOne (char[] nums) {
        for (char num : nums) {
            if (num == '1') {
                return true;
            }
        }
        return false;
    }
}
