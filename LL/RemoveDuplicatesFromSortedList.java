/*
* Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2
Example 2:

Input: 1->1->2->3->3
Output: 1->2->3

    20180719
    83
    easy
    O(n)
    O(1)
* */
package leetcode.LL;

public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode s = head;
        ListNode f = head.next;
        // 1---->2->3->3
        //          s
        //               f
        while (f != null) {
            while (f != null && f.val == s.val) {
                f = f.next;
            }
            s.next = f;
            s = f;
            f = f == null ? null : f.next;
        }
        return head;
    }
}
