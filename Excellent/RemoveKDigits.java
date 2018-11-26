/*
* Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
Example 3:

Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

    20180927
    402
    medium
    O(n)
    O(1)
* */
package leetcode.Excellent;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        if (num.length() == k) {
            return "0";
        }

        Deque<Integer> s = new ArrayDeque<>();
        int chance = k;
        for (int i = 0; i < num.length(); i++) {
            int dig = num.charAt(i) - '0';
            if (chance == 0) {
                s.offerLast(dig);
                continue;
            }

            while (!s.isEmpty() && s.peekLast() > dig && chance > 0) {
                s.pollLast();
                chance--;
            }

            if (!(s.isEmpty() && dig == 0)) {
                s.offerLast(dig);
            }
        }

        while (chance > 0) {
            s.pollLast();
            chance--;
        }

        StringBuilder sb = new StringBuilder();
        while (!s.isEmpty()) {
            sb.append(s.pollFirst());
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
