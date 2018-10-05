/*
* Given two 1d vectors, implement an iterator to return their elements alternately.

Example:

Input:
v1 = [1,2]
v2 = [3,4,5,6]

Output: [1,3,2,4,5,6]

Explanation: By calling next repeatedly until hasNext returns false,
             the order of elements returned by next should be: [1,3,2,4,5,6].
Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question:
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example:

Input:
[1,2,3]
[4,5,6,7]
[8,9]

Output: [1,4,8,2,5,9,3,6,7].

    20181001
    281
    medium
    O(?)
    O(m + n)
* */
package leetcode.Design;

import java.util.ArrayList;
import java.util.List;

public class ZigzagIterator {
    private final List<Integer> v1;
    private final List<Integer> v2;
    private int idx1;
    private int idx2;
    private boolean turn1;
    private boolean turn2;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.v1 = new ArrayList(v1);
        this.v2 = new ArrayList(v2);
        idx1 = 0;
        idx2 = 0;
        turn1 = idx1 < this.v1.size();
        turn2 = turn1 ? false : idx2 < this.v2.size();
    }

    public int next() {
        int ans = 0;
        if (turn1 && !turn2) {
            ans = v1.get(idx1++);
            turn1 = idx2 >= v2.size() && idx1 < v1.size();
            turn2 = idx2 < v2.size();
        } else if (!turn1 && turn2) {
            ans = v2.get(idx2++);
            turn2 = idx1 >= v1.size() && idx2 < v2.size();
            turn1 = idx1 < v1.size();
        }
        return ans;
    }

    public boolean hasNext() {
        return turn1 || turn2;
    }
}
