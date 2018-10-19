/*
* A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

Example:

Input: low = "50", high = "100"
Output: 3
Explanation: 69, 88, and 96 are three strobogrammatic numbers.
Note:
Because the range might be a large number, the low and high numbers are represented as string.

    20181018
    248
    hard
    O(5 ^ maxLength)
    O(maxLength)
* */
package leetcode.NonGraphDFS;

public class StrobogrammaticNumberIII {
    private int cnt = 0;
    private int minLength;
    private int maxLength;

    public static void main(String[] args) {
        System.out.print(new StrobogrammaticNumberIII().strobogrammaticInRange("0", "2343144543"));
    }

    public int strobogrammaticInRange(String low, String high) {
        minLength = low.length();
        maxLength = high.length();

        char[][] pool = new char[][]{{'0', '0'}, {'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};

        for (int n = minLength; n <= maxLength; n++) {
            char[] subsol = new char[n];
            dfs(pool, subsol, 0, low, high);
        }
        return cnt;
    }

    private void dfs(char[][] pool, char[] subsol, int index, String low, String high) {
        // 0 1 2 3        4
        //     b
        // 0 1 2 3 4      5
        //       b
        if (index == (subsol.length + 1) / 2) {
            if (subsol.length > minLength && subsol.length < maxLength) {
                cnt++;
            } else {
                String num = new String(subsol);
                if (isLargerOrEqualTo(num, low) && isLargerOrEqualTo(high, num)) {
                    cnt++;
                }
            }
            return;
        }
        // no trailing zeroes (except there is only one space)
        int start = (index == 0 && subsol.length - 1 - index != 0) ? 1 : 0;
        // length is odd, the mid char must be 0, 1 or 8
        int end = index == subsol.length - 1 - index ? 2 : 4;
        for (int i = start; i <= end; i++) {
            subsol[index] = pool[i][0];
            subsol[subsol.length - 1 - index] = pool[i][1];
            dfs(pool, subsol, index + 1, low, high);
        }
    }

    private boolean isLargerOrEqualTo(String s, String t) {
        if (s.length() != t.length()) {
            return s.length() > t.length();
        }
        for (int i = 0; i < s.length(); i++) {
            if ((int)s.charAt(i) > (int)t.charAt(i)) {
                return true;
            } else if ((int)s.charAt(i) < (int)t.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
