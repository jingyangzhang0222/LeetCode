/*
* Reverse a linked list from position m to n. Do it in one-pass.

Note: 1 ≤ m ≤ n ≤ length of list.

Example:

Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL

    20180719
    92
    medium
    O(n)
    O(1)
* */
package leetcode.LL;

public class ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode reversedHead = null;
        ListNode reversedTail = null;
        ListNode firstTail = null;
        //     1->2->3->4->5->NULL, m = 2, n = 4
        //           c
        //        p
        for (int i = 1; i <= n; i++) {
            ListNode next = cur.next;
            if (i == m - 1) {
                firstTail = cur;
            }
            if (i == m) {
                reversedTail = cur;
            }
            if (i == n) {
                reversedHead = cur;
            }
            if (i >= m + 1 && i <= n) {
                cur.next = pre;
            }
            pre = cur;
            cur = next;
        }

        if (m != 1) firstTail.next = reversedHead;
        reversedTail.next = cur;
        return m == 1 ? reversedHead : head;
    }
}
