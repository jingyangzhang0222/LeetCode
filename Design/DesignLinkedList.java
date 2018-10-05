/*
* Design your implementation of the linked list. You can choose to use the singly linked list or the doubly linked list. A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node. If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement these functions in your linked list class:

get(index) : Get the value of the index-th node in the linked list. If the index is invalid, return -1.
addAtHead(val) : Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
addAtTail(val) : Append a node of value val to the last element of the linked list.
addAtIndex(index, val) : Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
deleteAtIndex(index) : Delete the index-th node in the linked list, if the index is valid.
Example:

MyLinkedList linkedList = new MyLinkedList();
linkedList.addAtHead(1);
linkedList.addAtTail(3);
linkedList.addAtIndex(1, 2);  // linked list becomes 1->2->3
linkedList.get(1);            // returns 2
linkedList.deleteAtIndex(1);  // now the linked list is 1->3
linkedList.get(1);            // returns 3
Note:

All values will be in the range of [1, 1000].
The number of operations will be in the range of [1, 1000].
Please do not use the built-in LinkedList library.

    20180926
    707
    easy
    O(?)
    O(?)
* */
package leetcode.Design;

class DesignLinkedList {
    private ListNode head;
    private ListNode tail;
    private int size;
    /** Initialize your data structure here. */
    public DesignLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        ListNode cur = head;
        int curIndex = 0;
        while (curIndex < index && cur != null) {
            cur = cur.next;
            curIndex++;
        }
        return cur == null ? -1 : cur.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        size++;
        ListNode oldHead = head;
        head = new ListNode(val);
        head.next = oldHead;
        if (oldHead == null) {
            tail = head;
        }
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        size++;
        ListNode newTail = new ListNode(val);
        if (tail == null) {
            head = newTail;
            tail = newTail;
            return;
        }
        tail.next = newTail;
        tail = newTail;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index == size) {
            addAtTail(val);
        } else if (index < size) {
            size++;
            ListNode prev = null;
            ListNode cur = head;
            int curIndex = 0;
            while (curIndex < index) {
                prev = cur;
                cur = cur.next;
                curIndex++;
            }
            ListNode newNode = new ListNode(val);
            prev.next = newNode;
            newNode.next = cur;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < size) {
            if (--size == 0) {
                head = null;
                tail = null;
                return;
            }
            ListNode prev = null;
            ListNode cur = head;
            int curIndex = 0;
            while (curIndex < index) {
                prev = cur;
                cur = cur.next;
                curIndex++;
            }
            if (prev == null) {
                ListNode newHead = head.next;
                head.next = null;
                head = newHead;
                return;
            } else if (cur == tail) {
                prev.next = null;
                tail = prev;
                return;
            }

            prev.next = cur.next;
            cur.next = null;
        }
    }

    static class ListNode{
        int val;
        ListNode next;

        ListNode (int val) {
            this.val = val;
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
