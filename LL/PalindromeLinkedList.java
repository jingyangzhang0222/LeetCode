/*
* Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?

    20180628
    234
    easy
    O(n)
    O(1)
* */
package leetcode.LL;

public class PalindromeLinkedList {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        l1.next = l2;
        PalindromeLinkedList test = new PalindromeLinkedList();
        System.out.println(test.isPalindrome(l1));
    }
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode s = head;
        ListNode f = head.next;

        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }

        ListNode secondHead = reverse(s.next);
        while (secondHead != null) {
            if (head.val != secondHead.val) {
                return false;
            }
            head = head.next;
            secondHead = secondHead.next;
        }

        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null, next = null;
        ListNode cur = head;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
