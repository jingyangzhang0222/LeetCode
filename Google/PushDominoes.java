/*
* There are N dominoes in a line, and we place each domino vertically upright.

In the beginning, we simultaneously push some of the dominoes either to the left or to the right.



After each second, each domino that is falling to the left pushes the adjacent domino on the left.

Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.

Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.

Return a string representing the final state.

Example 1:

Input: ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."
Example 2:

Input: "RR.L"
Output: "RR.L"
Explanation: The first domino expends no additional force on the second domino.
Note:

0 <= N <= 10^5
String dominoes contains only 'L', 'R' and '.'

    20181028
    838
    medium
    O(n)
    O(n)
* */
package leetcode.Google;

public class PushDominoes {
    public static void main(String[] args) {
        System.out.print(new PushDominoes().pushDominoes(".L.R...LR..L.."));
    }
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();
        int[] nearestRight = new int[n];
        int[] nearestLeft = new int[n];
        char[] res = new char[n];
        //  . L . R . . . L R . . L . .
        //  1 0 0 0 3 2 1 0 0 2 1 0 0 0
        //  0 0 0 0 1 2 3 0 0 1 2 0 0 0
        //
        for (int i = 0; i < n; i++) {
            int j = n - 1 - i;
            if (dominoes.charAt(i) == '.') {
                int prevRightDistance = i == 0 ? 0 : nearestRight[i - 1];
                if (prevRightDistance > 0 || (i > 0 && dominoes.charAt(i - 1) == 'R')) {
                    nearestRight[i] = prevRightDistance + 1;
                }
            }

            if (dominoes.charAt(j) == '.') {
                int prevLeftDistance = j == n - 1 ? 0 : nearestLeft[j + 1];
                if (prevLeftDistance > 0 || (j < n - 1 && dominoes.charAt(j + 1) == 'L')) {
                    nearestLeft[j] = prevLeftDistance + 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (dominoes.charAt(i) != '.') {
                res[i] = dominoes.charAt(i);
            } else if (nearestRight[i] == nearestLeft[i]) {
                res[i] = '.';
            } else if (nearestLeft[i] == 0 || nearestRight[i] == 0) {
                res[i] = nearestLeft[i] != 0 ? 'L' : 'R';
            } else {
                res[i] = nearestRight[i] < nearestLeft[i] ? 'R' : 'L';
            }
        }

        return new String(res);
    }
}
