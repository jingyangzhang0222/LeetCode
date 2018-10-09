/*
* Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Consider the following binary search tree:

     5
    / \
   2   6
  / \
 1   3
Example 1:

Input: [5,2,6,1,3]
Output: false
Example 2:

Input: [5,2,1,3,6]
Output: true
Follow up:
Could you do it using only constant space complexity?


    20181006
    255
    medium
    O(n)
    O(n) -> O(1?)
* */
package leetcode.Tree.BST;

import java.util.ArrayDeque;
import java.util.Deque;

public class VerifyPreorderSequenceInBinarySearchTree {
    public boolean verifyPreorderStack(int[] preorder) {
        // pre: self-left-right
        Deque<Integer> s = new ArrayDeque<>();
        int min = Integer.MIN_VALUE;
        for (int num : preorder) {
            if (num < min) {
                return false;
            }
            while (!s.isEmpty() && s.peekLast() < num) {
                min = s.pollLast();
            }
            s.offerLast(num);
        }
        return true;
    }
    public boolean verifyPreorderNaive(int[] preorder) {
        // pre: self-left-right
        return verify(preorder, 0, preorder.length - 1);
    }

    private boolean verify(int[] pre, int left, int right) {
        if (left >= right) {
            return true;
        }

        int rootVal = pre[left];
        int cur = left + 1;
        while (cur < pre.length && pre[cur] < rootVal) {
            cur++;
        }
        int loLeft = left + 1;
        int loRight = cur - 1;
        int hiLeft = cur;
        while (cur < pre.length && pre[cur] > rootVal) {
            cur++;
        }
        if (cur != pre.length) {
            return false;
        }
        return verify(pre, loLeft, loRight) && verify(pre, hiLeft, right);
    }
}
