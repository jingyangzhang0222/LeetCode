/*
* Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits. There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid. "1:34", "12:9" are all invalid.

Example 1:

Input: "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
Example 2:

Input: "23:59"
Output: "22:22"
Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22. It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.\

    20190405
    681
    MEDIUM
    O(4 ^ 4)
    O(4)
* */
package leetcode.NonGraphDFS;

public class NextClosestTime {
    public static void main(String[] args ) {
        System.out.println(new NextClosestTime().nextClosestTime("12:34"));
    }

    public String nextClosestTime(String time) {
        String first = null;
        boolean[] pool = new boolean[10];
        for (char c : time.toCharArray()) {
            if (c != ':') {
                pool[c - '0'] = true;
            }
        }

        for (int i = 0; i <= 2; i++) {
            if (pool[i]) {
                first = "" + i + i + ":" + i + i;
                break;
            }
        }

        String[] candidate = new String[1];
        find(10 * (time.charAt(0) - '0') + time.charAt(1) - '0', 10 * (time.charAt(3) - '0') + time.charAt(4) - '0',
                pool, candidate, 0, new char[4]);
        return candidate[0] == null ? first : candidate[0];
    }

    private void find(int h, int m, boolean[] pool, String[] candidate, int position, char[] var) {
        if (candidate[0] != null) {
            return;
        }

        if (position == 4) {
            if (firstTimeIsLarger(var, h, m)) {
                candidate[0] = "" + var[0] + var[1] + ":" + var[2] + var[3];
            }
            return;
        }

        for (int dig = 0; dig <= 9; dig++) {
            if (pool[dig]) {
                var[position] = (char)(dig + '0');
                find(h, m, pool, candidate, position + 1, var);
            }
        }
    }

    private boolean firstTimeIsLarger(char[] var, int h, int m) {
        int h1 = 10 * (var[0] - '0') + var[1] - '0';
        int m1 = 10 * (var[2] - '0') + var[3] - '0';
        return (h1 <= 23) && (m1 <= 59) && ((h1 > h) || (h1 == h && m1 > m));
    }
}
