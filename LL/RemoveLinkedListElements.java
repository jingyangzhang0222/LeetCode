/*
* Remove all elements from a linked list of integers that have value val.

Example:

Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5

    20108719
    203
    easy
    O(n)
    O(1)
* */
package leetcode.LL;

public class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        ListNode pre = dummy;
        while (cur != null) {
            while (cur != null && cur.val == val) {
                cur = cur.next;
            }
            pre.next = cur;
            pre = cur;
            cur = cur == null ? null : cur.next;
        }
        return dummy.next;
    }
}
