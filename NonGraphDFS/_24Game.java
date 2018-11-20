/*
* You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) to get the value of 24.

Example 1:
Input: [4, 1, 8, 7]
Output: True
Explanation: (8-4) * (7-1) = 24
Example 2:
Input: [1, 2, 1, 2]
Output: False
Note:
The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] as input, the expression -1 - 1 - 1 - 1 is not allowed.
You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.

    20181117
    679
    hard
    O(1)
    O(1)
* */
package leetcode.NonGraphDFS;

import java.util.ArrayList;
import java.util.List;

public class _24Game {
    public boolean judgePoint24(int[] nums) {
        for (int size = 1; size <= 2; size++) {
            for (int i = size - 1; i < 4; i++) {
                swap(nums, size - 1, i);
                List<Double> left = getResults(nums, 0, size - 1);
                List<Double> right = getResults(nums, size, 3);
                for (double l : left) {
                    for (double r : right) {
                        if (Math.abs(l * r - 24) <= 0.0001) {
                            return true;
                        } else if (Math.abs(l + r - 24) <= 0.0001) {
                            return true;
                        } else if (Math.abs(l - r - 24) <= 0.0001) {
                            return true;
                        } else if (Math.abs(r - l - 24) <= 0.0001) {
                            return true;
                        } else if (Math.abs(l / r - 24) <= 0.0001) {
                            return true;
                        } else if (Math.abs(r / l - 24) <= 0.0001) {
                            return true;
                        }
                    }
                }
                swap(nums, size - 1, i);
            }
        }
        return false;
    }

    private List<Double> getResults(int[] nums, int start, int end) {
        List<Double> res = new ArrayList<>();
        if (start == end) {
            res.add((double)nums[start]);
        } else if (end == start + 1) {
            double l = nums[start], r = nums[end];
            res.add(l + r);
            res.add(l - r);
            res.add(r - l);
            res.add(l * r);
            res.add(l / r);
            res.add(r / l);
        } else {
            for (int i = start; i <= end; i++) {
                swap(nums, start, i);
                List<Double> left = getResults(nums, start, start);
                List<Double> right = getResults(nums, start + 1, end);
                for (double l : left) {
                    for (double r : right) {
                        res.add(l + r);
                        res.add(l - r);
                        res.add(r - l);
                        res.add(l * r);
                        res.add(l / r);
                        res.add(r / l);
                    }
                }
                swap(nums, i, start);
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
