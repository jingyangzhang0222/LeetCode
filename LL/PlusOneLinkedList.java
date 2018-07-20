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
        // safeNode: the last non-9 Node
        ListNode safeNode = null;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val != 9) {
                safeNode = cur;
            }
            cur = cur.next;
        }
        // safeNode == null indicates all the nodes are with value of '9'
        boolean flag = safeNode == null;
        if (safeNode == null) safeNode = head;
        while (safeNode != null) {
            safeNode.val = (safeNode.val + 1) % 10;
            safeNode = safeNode.next;
        }
        if (flag) {
            ListNode newHead = new ListNode(1);
            newHead.next = head;
            return newHead;
        }
        return head;
    }
}