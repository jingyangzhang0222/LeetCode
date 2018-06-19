/*
* Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?

    20180618
    19
    medium
    two pointers
    O(n)
    O(1)
* */
package leetcode.LL;

public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 0 - -> 1 -> 2 -> 3 -> 4 -> 5 -> null
        //        f
        //        s
        // p
        // count: 0
        ListNode f = head;
        ListNode s = head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        int count = 0;
        while (f != null) {
            f = f.next;
            if (count >= n) {
                prev = s;
                s = s.next;
            }
            count++;
        }
        prev.next = s.next;
        s.next = null;
        return dummy.next;
    }
}
