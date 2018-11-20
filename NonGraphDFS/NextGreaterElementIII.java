/*
* Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.

Example 1:

Input: 12
Output: 21


Example 2:

Input: 21
Output: -1

    20181118
    556
    medium
    O(n)
    O(1)
* */
package leetcode.NonGraphDFS;

public class NextGreaterElementIII {
    public int nextGreaterElement(int n) {
        //  2 5 4 3 2 1
        char[] s = String.valueOf(n).toCharArray();
        boolean found = false;
        for (int i = s.length - 2; i >= 0; i--) {
            if (s[i] < s[i + 1]) {
                found = true;
                for (int j = i + 1; j < s.length; j++) {
                    if (j + 1 == s.length || s[j + 1] <= s[i]) {
                        char tmp = s[i];
                        s[i] = s[j];
                        s[j] = tmp;
                        break;
                    }
                }
                reverse(s, i + 1, s.length - 1);
                break;
            }
        }
        if (!found) return -1;
        long candidate = Long.parseLong(new String(s));
        return candidate <= (long) Integer.MAX_VALUE ? (int) candidate : -1;
    }

    private void reverse(char[] s, int i, int j) {
        while (i < j) {
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            i++;
            j--;
        }
    }
}
