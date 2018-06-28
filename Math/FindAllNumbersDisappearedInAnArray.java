/*
* Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]

    20180627
    448
    easy
    O(n)
    O(1)
* */
package leetcode.Math;

import java.util.ArrayList;
import java.util.List;

//all the numbers that we have seen will be marked as negative.
// In the second iteration, if a value is not marked as negative, it implies we
// have never seen that index before, so just add it to the return list.
public class FindAllNumbersDisappearedInAnArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int n = nums.length;
        // 1 2 3 4 5
        // 0 1 2 3 4
        for (int num : nums) {
            int index = Math.abs(num) - 1;
            nums[index] = -Math.abs(nums[index]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
