/*
* In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL", a move consists of either replacing one occurrence of "XL" with "LX", or replacing one occurrence of "RX" with "XR". Given the starting string start and the ending string end, return True if and only if there exists a sequence of moves to transform one string to the other.

Example:

Input: start = "RXXLRXRXL", end = "XRLXXRRLX"
Output: True
Explanation:
We can transform start to end following these steps:
RXXLRXRXL ->
XRXLRXRXL ->
XRLXRXRXL ->
XRLXXRRXL ->
XRLXXRRLX
Note:

1 <= len(start) = len(end) <= 10000.
Both start and end will only consist of characters in {'L', 'R', 'X'}.


    20181031
    777
    medium
    O(n)
    O(1)
* */
package leetcode.Google;

public class SwapAdjacentInLRString {
    public static void main(String[] args) {
        new SwapAdjacentInLRString().canTransform("XXXRXXLXXXXXXXXRXXXR", "XXXXRLXXXXXXXXXXXXRR");
    }

    public boolean canTransform(String start, String end) {
        if (start.length() != end.length()) {
            return false;
        }
        int len = start.length();
        int index1 = 0, index2 = 0;

        while (index1 < start.length() || index2 < end.length()) {
            char c1 = index1 == len ? 'X' : start.charAt(index1);
            char c2 = index2 == len ? 'X' : end.charAt(index2);

            boolean outOfOrder = c1 != 'X' && c2 != 'X' && c1 != c2;
            boolean isRButLeftward = c1 == 'R' && c2 == 'R' && index2 < index1;
            boolean isLButRightward = c1 == 'L' && c2 == 'L' && index2 > index1;
            boolean numberOfLRDifferent = (index1 == len && c2 != 'X') || (index2 == len && c1 != 'X');
            if (outOfOrder || isRButLeftward || isLButRightward || numberOfLRDifferent) {
                return false;
            }

            if (c1 != 'X' && c2 != 'X') {
                index1++;
                index2++;
            } else {
                if (index1 != len && c1 == 'X') {
                    index1++;
                }
                if (index2 != len && c2 == 'X') {
                    index2++;
                }
            }
        }

        return true;
    }
}
