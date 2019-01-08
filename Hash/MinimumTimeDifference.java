/*
* Given a list of 24-hour clock time points in "Hour:Minutes" format, find the minimum minutes difference between any two time points in the list.
Example 1:
Input: ["23:59","00:00"]
Output: 1
Note:
The number of time points in the given list is at least 2 and won't exceed 20000.
The input time is legal and ranges from 00:00 to 23:59.

    20180103
    539
    medium
    O(n)
    O(1)
* */
package leetcode.Hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimumTimeDifference {
    public static void main(String[] args) {
        List<String> test = new ArrayList<>();
        test.add("23:59");
        test.add("00:00");
        System.out.println(new MinimumTimeDifference().findMinDifference(test));
    }

    public int findMinDifference(List<String> timePoints) {
        Set<Integer> set = new HashSet<>();
        for (String time : timePoints) {
            if (!set.add(getTime(time))) {
                return 0;
            }
        }

        int min = 1440;
        for (int time1 : set) {
            for (int time2 : set) {
                if (time1 != time2) {
                    int candidate = Math.abs(time1 - time2);
                    if (candidate >= 720) {
                        candidate = 1440 - candidate;
                    }
                    min = Math.min(min, candidate);
                }
            }
        }

        return min;
    }

    private int getTime(String time) {
        int hour = (time.charAt(0) - '0') * 10 + time.charAt(1) - '0';
        int min = (time.charAt(3) - '0') * 10 + time.charAt(4) - '0';
        return 60 * hour + min;
    }
}
