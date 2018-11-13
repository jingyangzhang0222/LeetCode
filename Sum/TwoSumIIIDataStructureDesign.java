/*
* Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

Example 1:

add(1); add(3); add(5);
find(4) -> true
find(7) -> false
Example 2:

add(3); add(1); add(2);
find(3) -> true
find(6) -> false

    20181111
    170
    easy
    O(1) add, O(n) find
    O(n)
* */
package leetcode.Sum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSumIIIDataStructureDesign {
    private final List<Integer> list;
    private final Map<Integer, Integer> map = new HashMap<>();
    private int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    /** Initialize your data structure here. */
    public TwoSumIIIDataStructureDesign() {
        list = new ArrayList<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        list.add(number);
        map.put(number, map.getOrDefault(number, 0) + 1);
        max = Math.max(max, number);
        min = Math.min(min, number);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        if (((long)value > (long)(2 * max)) || ((long)value < (long)(2 * min))) {
            return false;
        }
        for (int num : list) {
            if (map.containsKey(value - num)) {
                int fre = map.get(value - num);
                if ((2 * num == value && fre > 1) || (2 * num != value)) {
                    return true;
                }
            }
        }
        return false;
    }
}
