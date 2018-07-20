/*
* Given a linked list, rotate the list to the right by k places, where k is non-negative.

Example 1:

Input: 1->2->3->4->5->NULL, k = 2
Output: 4->5->1->2->3->NULL
Explanation:
rotate 1 steps to the right: 5->1->2->3->4->NULL
rotate 2 steps to the right: 4->5->1->2->3->NULL
Example 2:

Input: 0->1->2->NULL, k = 4
Output: 2->0->1->NULL
Explanation:
rotate 1 steps to the right: 2->0->1->NULL
rotate 2 steps to the right: 1->2->0->NULL
rotate 3 steps to the right: 0->1->2->NULL
rotate 4 steps to the right: 2->0->1->NULL

    20180719
    61
    medium
    O(n)
    O(1)

    two-pass solution
* */
package leetcode.LL;

public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        int cnt = 0;
        ListNode cur = head;
        while (cur != null) {
            cnt++;
            cur = cur.next;
        }
        k = k % cnt;
        if (k == 0) return head;
        ListNode f = head;
        ListNode s = null;
        while (f.next != null) {
            f = f.next;
            k--;
            if (k == 0) {
                s = head;
            }
            if (k < 0) {
                s = s.next;
            }
        }
        f.next = head;
        ListNode newHead = s.next;
        s.next = null;
        return newHead;
    }
}
