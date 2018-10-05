/*
* Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).

Example 1:
Input: [3, 2, 1]

Output: 1

Explanation: The third maximum is 1.
Example 2:
Input: [1, 2]

Output: 2

Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
Example 3:
Input: [2, 2, 3, 1]

Output: 1

Explanation: Note that the third maximum here means the third maximum distinct number.
Both numbers with value 2 are both considered as second maximum.

    20180927
    414
    easy
    O(n)
    O(1)
* */
package leetcode;

public class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        Integer first = null;
        Integer second = null;
        Integer third = null;
        for (int num : nums) {
            Integer prevFirst = first;
            Integer prevSecond = second;
            if (prevFirst == null || num >= prevFirst) {
                first = num;
                if (prevFirst != null && num > prevFirst) {
                    second = prevFirst;
                    third = prevSecond;
                }
            } else if (prevSecond == null || num >= prevSecond) {
                second = num;
                if (prevSecond != null && num > prevSecond) {
                    third = prevSecond;
                }
            } else if (third == null || num > third) {
                third = num;
            }
        }
        return third == null ? first : third;
    }
}
