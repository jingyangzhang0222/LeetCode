/*
* Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

    20180904
    394
    medium
    O(n)
    O(n)
* */
package leetcode.Excellent;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecodeString {
    public static void main(String[] args) {
        DecodeString test = new DecodeString();
        System.out.println(test.decodeString("3[a]"));
    }

    public String decodeString(String s) {
        Deque<String> strs = new ArrayDeque<>();
        strs.offerLast("");
        Deque<Integer> nums = new ArrayDeque<>();
        int index = 0;
        while (index < s.length()) {
            if (Character.isDigit(s.charAt(index))) {
                int num = 0;
                while (index < s.length() && Character.isDigit(s.charAt(index))) {
                    num = 10 * num + s.charAt(index) - '0';
                    index++;
                }
                nums.offerLast(num);
                strs.offerLast("");
                index++;
            } else if (Character.isLetter(s.charAt(index))) {
                StringBuilder sb = new StringBuilder();
                while (index < s.length() && Character.isLetter(s.charAt(index))) {
                    sb.append(s.charAt(index));
                    index++;
                }
                strs.offerLast(strs.pollLast() + sb.toString());
            } else {
                int repeat = nums.pollLast();
                String subString = strs.pollLast();
                StringBuilder sb = new StringBuilder();
                sb.append(strs.pollLast());
                for (int i = 0; i < repeat; i++) {
                    sb.append(subString);
                }
                strs.offerLast(sb.toString());
                index++;
            }
        }

        String res = "";
        while (!strs.isEmpty()) {
            res += strs.pollFirst();
        }
        return res;
    }
}
