/*
* Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.

Please note that the string does not contain any non-printable characters.

Example:

Input: "Hello, my name is John"
Output: 5


    20181104
    434
    easy
    O(n)
    O(1)
* */
package leetcode.String;

public class NumberOfSegmentsInAString {
    public int countSegments(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += s.charAt(i) != ' ' && (i == 0 || s.charAt(i - 1) == ' ') ? 1 : 0;
        }

        return count;
    }
}
