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

    20180905
    215
    medium
    Heap: O(n + klogn)
          O(n)

    QuickSelect: O(logn)
                 O(1)
* */
package leetcode.kth;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElementInAnArray {
    public static void main(String[] args) {
        KthLargestElementInAnArray test = new KthLargestElementInAnArray();
        int res = test.findKthLargestUsingQuickSelect(new int[]{1, 5, 3, 3, 4, 6, 2, 7, 3,}, 3);
        System.out.println(res);
    }

    public int findKthLargestUsingQuickSelect(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        int index = -1;
        while (index != k - 1) {
            int newIndex = quickSelect(nums, left, right);
            if (newIndex == k - 1) {
                break;
            } else if (newIndex < k - 1) {
                left = newIndex + 1;
            } else { // newIndex > k - 1
                right = newIndex - 1;
            }
            index = newIndex;
        }

        return nums[k - 1];
    }

    private int quickSelect(int[] nums, int left, int right) {
        if (left == right) {
            return left;
        }
        int pivot = nums[right];
        int indexGuard = right;
        right--;
        // [0, left]: smaller or equal to
        // [right, indexOfPivot - 1]: larger
        while (left <= right) {
            if (nums[left] >= pivot && nums[right] < pivot) {
                left++;
                right--;
            } else if (nums[left] < pivot && nums[right] >= pivot) {
                swap(nums, left++, right--);
            } else if (nums[right] < pivot) {
                right--;
            } else {
                left++;
            }
        }

        swap(nums, indexGuard, left);
        return left;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int findKthLargestUsingHeap(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue(Arrays.asList(toNegativeIntegerArray(nums)));
        for (int i = 0; i < k - 1; i++) {
            pq.poll();
        }
        return -pq.poll();
    }

    private Integer[] toNegativeIntegerArray(int[] nums) {
        Integer[] res = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = -nums[i];
        }
        return res;
    }
}
