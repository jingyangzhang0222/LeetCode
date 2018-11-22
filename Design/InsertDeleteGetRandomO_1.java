package leetcode.Design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertDeleteGetRandomO_1 {
    private List<Integer> list;
    private Map<Integer, Integer> map;
    /** Initialize your data structure here. */
    public InsertDeleteGetRandomO_1() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }

        int originalIndex = map.get(val);
        Integer originalLastElement = list.get(list.size() - 1);
        list.set(originalIndex, originalLastElement);
        map.put(originalLastElement, originalIndex);
        map.remove(val);
        list.remove(list.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int index = (int)(Math.random() * list.size());
        return list.get(index);
    }
}
