/*
* Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:

Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL
Example 2:

Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL
Note:

The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...

    20180719
    328
    medium
    O(n)
    O(1)
* */
package leetcode.LL;

public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
        ListNode oddDummy = new ListNode(1);
        ListNode evenDummy = new ListNode(2);
        ListNode cur = head;
        ListNode odd = oddDummy;
        ListNode even = evenDummy;
        int cnt = 1;
        while (cur != null) {
            if (isEven(cnt)) {
                even.next = cur;
                even = cur;
            } else {
                odd.next = cur;
                odd = cur;
            }
            cur = cur.next;
            cnt++;
        }
        even.next = null;////////////////////////////
        odd.next = evenDummy.next;
        return oddDummy.next;
    }
    private boolean isEven(int num) {
        return num % 2 == 0;
    }
}
