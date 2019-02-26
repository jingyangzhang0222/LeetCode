package leetcode.Design;

public class HashMap {
    /** Initialize your data structure here. */
    private static final int INITIAL_CAPACITY = 16;
    private static final double REHASH_FACTOR = 0.75;
    private static final int SCALAR = 2;
    private int capacity;
    private int size;
    private Node[] array;

    public HashMap() {
        capacity = INITIAL_CAPACITY;
        array = new Node[capacity];
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int index = key % capacity;
        Node cur = array[index];
        while (cur != null) {
            if (cur.key == key) {
                cur.setValue(value);
                return;
            }
            cur = cur.next;
        }
        Node newHead = new Node(key, value);
        newHead.next = array[index];
        array[index] = newHead;
        if (++size / (double)capacity > REHASH_FACTOR) {
            rehash();
        }
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int index = key % capacity;
        Node cur = array[index];
        while (cur != null) {
            if (cur.key == key) {
                return cur.value;
            }
            cur = cur.next;
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int index = key % capacity;
        Node prev = null;
        Node cur = array[index];
        while (cur != null) {
            if (cur.key == key) {
                size--;
                if (prev == null) {
                    //head needs to be removed
                    array[index] = cur.next;
                } else {
                    // prev is not null
                    prev.next = cur.next;
                }
                cur.next = null;
                return;
            }
            prev = cur;
            cur = cur.next;
        }
    }

    private void rehash() {
        capacity *= SCALAR;
        Node[] oldArray = array;
        array = new Node[capacity];
        for (int i = 0; i < oldArray.length; i++) {
            Node cur = oldArray[i];
            while (cur != null) {
                Node nextNode = cur.next;
                int newIndex = cur.key % capacity;
                cur.next = array[newIndex];
                array[newIndex] = cur;
                cur = nextNode;
            }
        }
    }

    public static class Node{
        private final int key;
        private int value;
        private Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public void setValue(int newValue) {
            value = newValue;
        }
    }
}
