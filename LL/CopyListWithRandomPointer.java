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
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        RandomListNode cur = head;
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        RandomListNode cHead = new RandomListNode(head.label);
        map.put(head, cHead);

        while (cur != null) {
            if (!map.containsKey(cur)) {
                map.put(cur, new RandomListNode(cur.label));
            }
            RandomListNode cCur = map.get(cur);

            if (cur.next != null && !map.containsKey(cur.next)) {
                map.put(cur.next, new RandomListNode(cur.next.label));
            }
            cCur.next = cur.next == null ? null : map.get(cur.next);

            if (cur.random != null && !map.containsKey(cur.random)) {
                map.put(cur.random, new RandomListNode(cur.random.label));
            }
            cCur.random = cur.random == null ? null : map.get(cur.random);

            cur = cur.next;
        }

        return cHead;
    }


    private static class RandomListNode {
        int label;
        RandomListNode next, random;

        RandomListNode(int x) {
            this.label = x;
        }
    }
}
