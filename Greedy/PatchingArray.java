/*
* Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required.

Example 1:

Input: nums = [1,3], n = 6
Output: 1
Explanation:
Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
So we only need 1 patch.
Example 2:

Input: nums = [1,5,10], n = 20
Output: 2
Explanation: The two patches can be [2, 4].
Example 3:

Input: nums = [1,2,2], n = 5
Output: 0

    20180910
    330
    hard
    O(n)
    O(1)
* */

// point: [1, bound)
// bound <= n
package leetcode.Greedy;

public class PatchingArray {
    public static void main(String[] args) {
        PatchingArray test = new PatchingArray();
        System.out.println(test.minPatches(new int[]{1, 3}, 6));

    }

    public int minPatches(int[] nums, int n) {
        if (n <= 0) {
            return 0;
        }
        // [1, bound)
        // 1 2 2 6 7, 100
        // 1 3 5

        int cnt = 0;
        int index = 0;
        long bound = 1;
        while (bound <= n) {
            if (index < nums.length && nums[index] <= bound) {
                bound += nums[index++];
            } else {
                bound *= 2;
                cnt++;
            }
        }
        return cnt;
    }
}
