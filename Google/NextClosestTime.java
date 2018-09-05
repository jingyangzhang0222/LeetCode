/*
* Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no
* limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are
all invalid.

Example 1:

Input: "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not
19:33, because this occurs 23 hours and 59 minutes later.
Example 2:

Input: "23:59"
Output: "22:22"
Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the retu

    20180904
    681
    medium
    O(1)
    O(1)

    do not think too much
* */
package leetcode.Google;

import java.util.HashSet;
import java.util.Set;

public class NextClosestTime {
    public static void main(String[] args) {
        NextClosestTime test = new NextClosestTime();
        System.out.println(test.nextClosestTime("00:11"));
    }
    public String nextClosestTime(String time) {
        int t = 1000 * (time.charAt(0) - '0') +
                100 *  (time.charAt(1) - '0') +
                10 *   (time.charAt(3) - '0') +
                time.charAt(4) - '0';
        Set<Integer> set = new HashSet<>();
        set.add(time.charAt(0) - '0');
        set.add(time.charAt(1) - '0');
        set.add(time.charAt(3) - '0');
        set.add(time.charAt(4) - '0');
        StringBuilder sb = new StringBuilder();

        for (int curTime = t + 1; curTime <= 2359; curTime++) {
            int dig1 = curTime / 1000;
            int dig2 = (curTime - dig1 * 1000) / 100;
            int dig3 = (curTime - dig1 * 1000 - dig2 * 100) / 10;
            int dig4 = curTime % 10;
            if (set.contains(dig1) &&
                    set.contains(dig2) &&
                    set.contains(dig3) &&
                    set.contains(dig4) &&
                    hourIsValid(10 * dig1 + dig2) &&
                    minuteIsValid(10 * dig3 + dig4)) {
                sb.append(dig1);
                sb.append(dig2);
                sb.append(':');
                sb.append(dig3);
                sb.append(dig4);
                return sb.toString();
            }
        }

        int min = 9;
        for (Integer num : set) {
            min = Math.min(min, num);
        }
        sb.append(min);
        sb.append(min);
        sb.append(':');
        sb.append(min);
        sb.append(min);
        return sb.toString();
    }

    private boolean hourIsValid(int hour) {
        return hour <= 23;
    }

    private boolean minuteIsValid(int min) {
        return min <= 59;
    }
}
