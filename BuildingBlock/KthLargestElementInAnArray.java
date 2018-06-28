/*
* Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note:
You may assume k is always valid, 1 ≤ k ≤ array's length.

    20180627
    215
    medium
    O(n)
    O(1)
* */
package leetcode.BuildingBlock;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class KthLargestElementInAnArray {
    public static void main(String[] args) {
        KthLargestElementInAnArray test = new KthLargestElementInAnArray();
        int[] input = {3, 2, 3, 1, 2, 4, 5, 5, 6, 4};
        System.out.println(test.findKthLargestQuickSelect(input, 4));
    }

    public int findKthLargestMaxHeap(int[] nums, int k) {
        for (int i = 0; i< nums.length; i++) {
            nums[i] = 0 - nums[i];
        }
        List<Integer> test = Arrays.asList(toIntegerArray(nums));
        PriorityQueue<Integer> pq = new PriorityQueue<>(test);
        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }
        return 0 - pq.poll();
    }

    private Integer[] toIntegerArray(int[] array) {
        Integer[] res = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            res[i] = array[i];
        }
        return res;
    }

    public int findKthLargestQuickSelect(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;
        while (index != k - 1) {
            int newIndex = partition(nums, left, right);
            if (newIndex < k - 1) {
                left = newIndex + 1;
            }
            if (newIndex > k - 1) {
                right = newIndex - 1;
            }
            index = newIndex;
        }
        return nums[index];
    }

    private int partition(int[] nums, int left, int right) {
        if (left == right) return left;
        int pivot = nums[left];
        int i = left + 1;
        int j = right;
        while (i <= j) {
            if (nums[i] < pivot && nums[j] >= pivot) {
                swap(nums, i++, j--);
            } else if (nums[i] < pivot) {
                j--;
            } else if (nums[j] >= pivot) {
                i++;
            } else {
                i++;
                j--;
            }
        }
        swap(nums, left, j);
        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
