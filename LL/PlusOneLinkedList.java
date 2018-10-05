/*
* Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

Example:
Input:
1->2->3

Output:
1->2->4

    20180715
    369
    medium
    O(n)
    O(1)
* */
package leetcode.LL;

public class PlusOneLinkedList {
    public ListNode plusOne(ListNode head) {
        ListNode newHead = new ListNode(1);
        ListNode lastNonNine = null;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val != 9) {
                lastNonNine = cur;
            }
            cur = cur.next;
        }

        if (cur.val != 9) {
            cur.val++;
            return head;
        } else {
            newHead.next = head;
            lastNonNine = lastNonNine == null ? head : lastNonNine;
            while (lastNonNine != null) {
                lastNonNine.val = (lastNonNine.val + 1) % 10;
                lastNonNine = lastNonNine.next;
            }
            return head.val == 0 ? newHead : head;
        }
    }
}