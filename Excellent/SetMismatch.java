/*
* The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.

Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.

Example 1:
Input: nums = [1,2,2,4]
Output: [2,3]
Note:
The given array size will in the range [2, 10000].
The given array's numbers won't have any order.

    20181127
    645
    easy
    O(n)
    O(1)
* */
package leetcode.Excellent;

public class SetMismatch {
    public int[] findErrorNums(int[] nums) {
        // 0 1 2 3 4 5 6
        // 1 2 2 4 6 5 7

        int[] res = new int[2];
        int index = 0;
        while (index < nums.length) {
            if (nums[index] == index + 1) {
                index++;
            } else {
                if (nums[nums[index] - 1] == nums[index]) {
                    res[0] = nums[index];
                    res[1] = index + 1;
                    index++;
                } else {
                    swap(nums, nums[index] - 1, index);
                }
            }
        }

        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


}
