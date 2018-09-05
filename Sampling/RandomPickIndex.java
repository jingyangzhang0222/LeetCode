/*
* Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);

    20180904
    398
    medium
    time: constructor: O(1)
          pick(): O(n)
    O(1)
* */
package leetcode.Sampling;

public class RandomPickIndex {
    private int[] arr;

    public RandomPickIndex(int[] nums) {
        arr = nums;
    }

    public int pick(int target) {
        int cnt = 0;
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                cnt++;
                int generator = (int) (cnt * Math.random());
                if (generator == 0) {
                    index = i;
                }
            }
        }
        return index;
    }

    public static void main(String[] args) {
        RandomPickIndex test = new RandomPickIndex(new int[]{1, 2, 3, 3, 3, 3});
        for (int i = 0; i < 10; i++) {
            System.out.println(test.pick(3));
        }
    }
}
