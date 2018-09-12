/*
* Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();

    20180909
    384
    medium
    O(n)
    O(1)
* */
package leetcode.Sampling;

import java.util.Arrays;

public class ShuffleAnArray {
    private final int[] oriArr;

    public ShuffleAnArray(int[] nums) {
        oriArr = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return oriArr;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        if (oriArr == null || oriArr.length == 0) {
            return oriArr;
        }

        int[] shuffled = Arrays.copyOf(oriArr, oriArr.length);
        for (int i = 0; i < oriArr.length; i++) {
            int newIndex = (int)(oriArr.length * Math.random());
            swap(shuffled, i, newIndex);
        }
        return shuffled;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
