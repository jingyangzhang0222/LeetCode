/*
* Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?


    20180626
    141
    easy
    O(n)
    O(1)
* */
package leetcode.LL;

public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode s = head;
        ListNode f = head.next;
        while (f != null && f.next != null) {
            if (s == f) {
                return true;
            }
            s = s.next;
            f = f.next.next;
        }
        return false;
    }
}
