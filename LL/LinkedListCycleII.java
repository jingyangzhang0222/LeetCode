/*
* Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?

    t = X + mY + K
    2t = X + nY + K

    X = (n - 2m)Y - K


    20180629
    142
    medium
    O(n)
    O(1)
* */
package leetcode.LL;

public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        ListNode f = head;
        ListNode s = head;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
            if (s == f) {
                ListNode cur = head;
                while (cur != f) {
                    f = f.next;
                    cur = cur.next;
                }
                return cur;
            }
        }
        return null;
    }
}