/*
* Given two strings s and t, determine if they are both one edit distance apart.

Note:

There are 3 possiblities to satisify one edit distance apart:

Insert a character into s to get t
Delete a character from s to get t
Replace a character of s to get t
Example 1:

Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.
Example 2:

Input: s = "cab", t = "ad"
Output: false
Explanation: We cannot get t from s by only one step.
Example 3:

Input: s = "1203", t = "1213"
Output: true
Explanation: We can replace '0' with '1' to get t.

    20181110
    161
    medium
    O(n)
    O(1)
* */
package leetcode.BuildingBlock;

public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) >= 2) {
            return false;
        }

        if (s.length() == t.length()) {
            int diff = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i) && ++diff > 1) {
                    return false;
                }
            }
            return diff == 1;
        } else {
            String shorter = s.length() < t.length() ? s : t;
            String longer = shorter == s ? t : s;
            int i = 0, j = 0;
            int diff = 0;
            while (i < shorter.length() && j < longer.length()) {
                if (shorter.charAt(i) == longer.charAt(j)) {
                    i++;
                } else if (++diff > 1) {
                    return false;
                }
                j++;
            }

            return (diff == 0 && i == shorter.length() && j == longer.length() - 1) ||
                   (diff == 1 && i == shorter.length() && j == longer.length());
        }
    }

    public boolean isOneEditDistanceRecursion(String s, String t) {
        if (Math.abs(s.length() - t.length()) >= 2) return false;

        if (s.length() == t.length()) {
            int len = s.length();
            if (len == 0) return false;

            String subS = s.substring(0, len - 1);
            String subT = t.substring(0, len - 1);

            if (s.charAt(len - 1) == t.charAt(len - 1)) {
                return isOneEditDistanceRecursion(subS, subT);
            } else {
                return subS.equals(subT);
            }
        } else {
            String shorter = s.length() < t.length() ? s : t;
            String longer = shorter == s ? t : s;

            if (shorter.length() == 0) return true;

            String subS = shorter.substring(0, shorter.length() - 1);
            String subL = longer.substring(0, longer.length() - 1);

            if (shorter.charAt(shorter.length() - 1) == longer.charAt(longer.length() - 1)) {
                return isOneEditDistanceRecursion(subS, subL);
            } else {
                return subL.equals(shorter);
            }
        }
    }
}
