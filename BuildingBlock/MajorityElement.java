/*
*
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2

    20180910
    169
    easy
    O(n)
    o(1)
* */
package leetcode.BuildingBlock;

public class MajorityElement {
    public int majorityElement(int[] nums) {
        int res = nums[0];
        int cnt = 0;
        for (int num : nums) {
            if (num == res) {
                cnt++;
            } else if (--cnt == -1) {
                cnt = 1;
                res = num;
            }
        }
        return res;
    }
}
