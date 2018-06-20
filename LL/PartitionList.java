/*
* Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:

Input: head = 1->4->3->2->5->2, x = 3
Output: 1->2->2->4->3->5

    20180619
    86
    medium
    O(n)
    O(1)

    8 / 15 min
    typo: 2
* */
package leetcode.LL;

public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        ListNode smallerDummy = new ListNode(0);
        ListNode bigDummy = new ListNode(0);
        ListNode cur = head;
        ListNode cur1 = smallerDummy;
        ListNode cur2 = bigDummy;
        while (cur != null) {
            if (cur.val < x) {
                cur1.next = cur;
                cur1 = cur;
            } else {
                cur2.next = cur;
                cur2 = cur;
            }
            cur = cur.next;
        }
        cur2.next = null;
        cur1.next = bigDummy.next;
        return smallerDummy.next;
    }
}