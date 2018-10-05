/*
* Given a node from a cyclic linked list which is sorted in ascending order, write a function to insert a value into the list such that it remains a cyclic sorted list. The given node can be a reference to any single node in the list, and may not be necessarily the smallest value in the cyclic list.

If there are multiple suitable places for insertion, you may choose any place to insert the new value. After the insertion, the cyclic list should remain sorted.

If the list is empty (i.e., given node is null), you should create a new single cyclic list and return the reference to that single node. Otherwise, you should return the original given node.

The following example may help you understand the problem better:

In the figure above, there is a cyclic sorted list of three elements. You are given a reference to the node with value 3, and we need to insert 2 into the list.

The new node should insert between node 1 and node 3. After the insertion, the list should look like this, and we should still return node 3.

    20181003
    708
    medium
    O(n)
    O(1)
* */
package leetcode.LL;

public class InsertIntoACyclicSortedList {
    public Node insert(Node head, int val) {
        if (head == null) {
            head = new Node();
            head.val = val;
            head.next = head;
            return head;
        }

        Node newNode = new Node();
        newNode.val = val;
        Node cur = head;

        while (cur.next != head) {
            if (cur.next.val < cur.val && (val >= cur.val || val <= cur.next.val)) {
                break;
            }
            if (cur.val <= val && val <= cur.next.val) {
                break;
            }
            cur = cur.next;
        }

        // case1: cur.next.val < cur.val && (val >= cur.val || val <= cur.next.val)
        // case2: cur.val <= val && val <= cur.next.val
        // case3: the last possible position, cur == tail, cur.next == head

        newNode.next = cur.next;
        cur.next = newNode;
        return head;
    }

    private static final class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }
}
