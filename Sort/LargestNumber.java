/*
* Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a string instead of an integer.

    20190213
    179
    medium
    O(nlogn)
    O(n)
* */
package leetcode.Sort;

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {
    public String largestNumber(int[] nums) {
        Integer[] tmp = new Integer[nums.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            tmp[i] = nums[i];
            sum += nums[i];
        }
        if (sum == 0) return "0";
        Arrays.sort(tmp, new MyComparator());
        StringBuilder sb = new StringBuilder();
        for (Integer i : tmp) {
            sb.append(i);
        }
        return sb.toString();
    }

    class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            if (("" + i1 + i2).compareTo("" + i2 + i1) < 0) {
                // 901, 90
                // 90190 < 90901
                return 1;
            } else {
                return -1;
            }
        }
    }
}
