/*
* A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

    20180808
    138
    medium
    O(n)
    O(n)
* */
package leetcode.LL;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        //1. build a map, key: original random node, value: copied node
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            //2. in loop, build a new node if the copoed node has not been created yet, and put KV pair into map
            if (!map.containsKey(cur)) {
                map.put(cur, new Node(cur.val));
            }

            Node copiedNode = map.get(cur);
            Node nextNode = cur.next, randomNode = cur.random;
            // 3. if next/random has been created, just get it from the map, or create it, and put KV pair into the map
            if (nextNode != null && !map.containsKey(nextNode)) {
                map.put(nextNode, new Node(nextNode.val));
            }
            copiedNode.next = nextNode == null ? null : map.get(nextNode);
            if (randomNode != null && !map.containsKey(randomNode)) {
                map.put(randomNode, new Node(randomNode.val));
            }
            copiedNode.random = randomNode == null ? null : map.get(randomNode);
            cur = cur.next;
        }

        return map.get(head);
    }

    public Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }

        Node cur = head;
        while (cur != null) {
            Node nextNode = cur.next;
            Node copiedNode = new Node(cur.val);
            copiedNode.next = nextNode;
            cur.next = copiedNode;
            cur = nextNode;
        }

        Node copiedHead = head.next;

        cur = head;
        while (cur != null) {
            Node copiedNode = cur.next;
            Node nextNode = copiedNode.next;
            copiedNode.random = cur.random == null ? null : cur.random.next;
            cur = nextNode;
        }

        cur = head;
        while (cur != null) {
            Node copiedNode = cur.next;
            Node nextNode = copiedNode.next;
            copiedNode.next = nextNode == null ? null : nextNode.next;
            cur.next = nextNode;
            cur = nextNode;
        }
        return copiedHead;
    }


    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }

        public Node(int val) {
            this.val = val;
        }
    }
}
