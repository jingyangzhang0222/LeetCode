/*
* You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:

Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7

    20181003
    445
    medium
    O(m + n)
    O(1)
* */
package leetcode.LL;

public class AddTwoNumbersII {
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        int cnt1 = 0, cnt2 = 0;
        while (cur1 != null || cur2 != null) {
            if (cur1 != null) {
                cur1 = cur1.next;
                cnt1++;
            }
            if (cur2 != null) {
                cur2 = cur2.next;
                cnt2++;
            }
        }
        // longer
        ListNode l = cnt1 >= cnt2 ? l1 : l2;
        // shorter
        ListNode s = l == l1 ? l2 : l1;

        int indexL = 0;
        int indexS = -Math.abs(cnt1 - cnt2);
        ListNode curL = l, curS = s;
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (curL != null && curS != null) {
            cur.next = new ListNode(0);
            cur = cur.next;
            int valL = curL.val;
            int valS = indexS >= 0 ? curS.val : 0;
            cur.val = valS + valL;
            curL = curL.next;
            if (indexS >= 0) {
                curS = curS.next;
            }
            indexL++;
            indexS++;
        }
        ListNode tmpHead = dummy.next;
        dummy.next = null;
        ListNode tail = reverse(tmpHead);
        ListNode tailGuard = tail;
        ListNode prev = null;
        int carry = 0;

        while (tail != null) {
            prev = tail;
            int res = tail.val + carry;
            tail.val = res % 10;
            carry = res / 10;
            tail = tail.next;
        }
        if (carry == 1) {
            prev.next = new ListNode(1);
            prev = prev.next;
        }
        return reverse(tailGuard);
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode cur1 = reverse(l1);
        ListNode cur2 = reverse(l2);
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (cur1 != null || cur2 != null || carry == 1) {
            cur.next = new ListNode(0);
            cur = cur.next;
            int val1 = cur1 == null ? 0 : cur1.val;
            int val2 = cur2 == null ? 0 : cur2.val;
            cur.val = (val1 + val2 + carry) % 10;
            carry = (val1 + val2 + carry) / 10;
            cur1 = cur1 == null ? null : cur1.next;
            cur2 = cur2 == null ? null : cur2.next;
        }

        ListNode tail = dummy.next;
        dummy.next = null;
        return reverse(tail);
    }

    private ListNode reverse(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre == null ? cur : pre;
    }
}
