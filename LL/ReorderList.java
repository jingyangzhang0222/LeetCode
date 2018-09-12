/*
* Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You may not modify the values in the list's nodes, only nodes itself may be changed.

Example 1:

Given 1->2->3->4, reorder it to 1->4->2->3.
Example 2:

Given 1->2->3->4->5, reorder it to 1->5->2->4->3.

    20180910
    143
    medium
    O(n)
    O(1)
* */
package leetcode.LL;

public class ReorderList {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        // 1, 2, 3, 4, 5, #
        // s  f

        // 1, 2, 3, 4, #
        //    s     f
        ListNode s = head;
        ListNode f= head.next;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }
        ListNode two = reverse(s.next);
        s.next = null;
        ListNode one = head.next;
        ListNode cur = head;
        // 1 2 #
        // 4 3 #
        // 1 2 3 #
        // 5 4 #
        while (one != null && two != null) {
            cur.next = two;
            two = two.next;
            cur.next.next = one;
            one = one.next;
            cur = cur.next.next;
        }

        if (one == null && two != null) {
            cur.next = two;
        }
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
}
