package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    public static void main(String[] args) {
        LRUCache<Integer, Integer> test = new LRUCache<>(2);
        System.out.println(test.get(1));
        test.set(1, 1);
        test.set(2, 2);
        System.out.println(test.get(2));
        System.out.println(test.get(1));
        test.set(3, 3);
        System.out.println(test.get(1));
        System.out.println(test.get(2));
        System.out.println(test.get(3));
    }

    private DoublyLinkedListNode head;
    private DoublyLinkedListNode tail;
    private Map<K, DoublyLinkedListNode> map;

    private final int capacity;
    private int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    private void removeLRUCache() {
        map.remove(tail);
        //at least, size is two now
        tail = tail.prev;
        if (tail != null) tail.next = null;
    }

    public void set(K key, V value) {
        if (map.containsKey(key)) {
            DoublyLinkedListNode node = map.get(key);
            updateOrder(node);
        } else {
            DoublyLinkedListNode node = new DoublyLinkedListNode(value);
            map.put(key, node);
            if (size < capacity) {
                updateOrder(node);
                size++;
            } else {
                updateOrder(node);
                removeLRUCache();
            }
        }
    }

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }
        DoublyLinkedListNode node = map.get(key);
        updateOrder(node);
        return map.get(key).value;
    }

    private void updateOrder(DoublyLinkedListNode node) {
        // node is NULL OR already MRU, do nothing
        if (node == null || node == head) {
            return;
        }

        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }

        if (size == 0) {
            tail = node;
        } else if (tail == node) {
            tail = node.prev == null ? node : node.prev;
        }

        node.next = head;
        node.prev = null;
        if (size != 0) head.prev = node;
        head = node;
    }

    class DoublyLinkedListNode {
        V value;
        DoublyLinkedListNode prev;
        DoublyLinkedListNode next;

        public DoublyLinkedListNode(V value) {
            this.value = value;
        }
    }
}
