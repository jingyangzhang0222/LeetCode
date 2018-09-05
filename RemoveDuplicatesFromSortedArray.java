package leetcode;

public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        int s = 0;
        for (int f = 0; f < nums.length; f++) {
            if (f == 0 || nums[f] != nums[f - 1]) {
                nums[s++] = nums[f];
            }
        }
        return s;
    }
}
