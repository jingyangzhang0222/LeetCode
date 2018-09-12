/*
* The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:

Input: 1
Output: "1"


Example 2:

Input: 4
Output: "1211"
Input Constraints:

1 <= n <= 30

    21080910
    38
    easy
* */
package leetcode;

public class CountAndSay {
    public String countAndSay(int n) {
        String num = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int index = 0;
            while (index < num.length()) {
                int cnt = 0;
                char thisDigit = num.charAt(index);
                while (index < num.length() && num.charAt(index) == thisDigit) {
                    index++;
                    cnt++;
                }
                sb.append(cnt).append(thisDigit);
            }
            num = sb.toString();
        }
        return num;
    }
}
