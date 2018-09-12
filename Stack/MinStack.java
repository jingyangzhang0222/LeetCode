/*
* Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.


    20180909
    155
    easy
    O(1)
    O(n)

    注意额外插值法怎么写
* */
package leetcode.Stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {
    private final Deque<Integer> s1;
    private final Deque<Integer> s2;
    /** initialize your data structure here. */
    public MinStack() {
        s1 = new ArrayDeque<>();
        s2 = new ArrayDeque<>();
    }

    public void push(int x) {
        if (s1 != null) {
            s1.offerLast(x);
        }
        if (s2 != null) {
            s2.offerLast((s2.isEmpty() || s2.peekLast() > x) ? x : s2.peekLast());
        }
    }

    public void pop() {
        if (s1 != null && !s1.isEmpty()) {
            s1.pollLast();
        }
        if (s2 != null && !s2.isEmpty()) {
            s2.pollLast();
        }
    }

    public int top() {
        return s1.peekLast();
    }

    public int getMin() {
        return s2.peekLast();
    }
}
