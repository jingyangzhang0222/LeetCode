/*
* You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:

Input: [5,2,6,1]
Output: [2,1,1,0]
Explanation:
To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.

    20181013
    315
    medium
    O(nlogn)
    O(n)
* */
package leetcode.Excellent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args) {
        System.out.println(new CountOfSmallerNumbersAfterSelf().countSmaller(new int[]{52, 22, 83, 51, 98, 69, 81, 32, 78, 28, 94, 13, 2, 97, 3, 76, 99, 51, 9, 21, 84, 66, 65, 36, 100, 41}));
    }

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length == 0) {
            return res;
        }
        res.add(0);
        Node root = new Node(nums[nums.length - 1]);
        for (int i = nums.length - 2; i >= 0; i--) {
            res.add(build(root, nums[i]));
        }
        Collections.reverse(res);
        return res;
    }

    private int build(Node root, int val) {
        Node cur = root;
        Node pre = null;
        int smallerCount = 0;
        while (cur != null) {
            pre = cur;
            if (val < cur.val) {
                cur.leftCount++;
                cur = cur.left;
            } else if (val > cur.val) {
                smallerCount += cur.count + cur.leftCount;
                cur = cur.right;
            } else {
                cur.count++;
                smallerCount += cur.leftCount;
                break;
            }
        }
        if (pre != null && cur == null) {
            if (val < pre.val) {
                pre.left = new Node(val);
            } else if (val > pre.val) {
                pre.right = new Node(val);
            }
        }
        return smallerCount;
    }

    class Node{
        int val;
        int leftCount;
        int count;
        Node left;
        Node right;

        Node(int val) {
            this.val = val;
            this.count = 1;
        }
    }
}
