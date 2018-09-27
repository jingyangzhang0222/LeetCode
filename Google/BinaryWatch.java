/*
* A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.


For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".

    20180926
    401
    medium
    O(2 ^ n)
    O(1)
* */
package leetcode.Google;

import java.util.ArrayList;
import java.util.List;

public class BinaryWatch {
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        for (int left = 0; left <= num; left++) {
            int right = num - left;
            List<String> hours = getTime(left, 4);
            List<String> minutes = getTime(right, 6);
            for (String hour : hours) {
                for (String min : minutes) {
                    res.add(hour + ":" + (min.length() == 1 ? "0" + min : min));
                }
            }
        }
        return res;
    }

    private List<String> getTime(int num, int lights) {
        List<String> res = new ArrayList<>();
        dfsHelper(num, res, new int[lights], 0);
        return res;
    }

    private void dfsHelper(int num, List<String> res, int[] base, int index) {
        // base case
        if (num < 0) {
            return;
        }
        if (index >= base.length) {
            if (num != 0) {
                return;
            }
            int bin = 1;
            int sum = 0;
            for (int i = 0; i < base.length; i++) {
                if (base[i] == 1) {
                    sum += bin;
                }
                bin *= 2;
            }
            if ((base.length == 4 && sum >= 0 && sum <= 11) ||
                    (base.length == 6 && sum >= 0 && sum <= 59)) {
                res.add(String.valueOf(sum));
            }
            return;
        }

        dfsHelper(num, res, base, index + 1);

        base[index] = 1;
        dfsHelper(num - 1, res, base, index + 1);
        base[index] = 0;
    }
}
