/*
* Implement the following operations of a stack using queues.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.
Example:

MyStack stack = new MyStack();

stack.push(1);
stack.push(2);
stack.top();   // returns 2
stack.pop();   // returns 2
stack.empty(); // returns false
Notes:

You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), as long as you use only standard operations of a queue.
You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).


    20181009
    225
    medium
    pop/top: O(n)
    push: O(1)
* */
package leetcode.Design;

import java.util.ArrayDeque;
import java.util.Queue;

public class ImplementStackUsingQueues {
    Queue<Integer> q;
    /** Initialize your data structure here. */
    public ImplementStackUsingQueues() {
        q = new ArrayDeque<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        for (int i = 0; i < q.size() - 1; i++) {
            q.offer(q.poll());
        }
        return q.poll();
    }

    /** Get the top element. */
    public int top() {
        for (int i = 0; i < q.size() - 1; i++) {
            q.offer(q.poll());
        }
        int val = q.peek();
        q.offer(q.poll());
        return val;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
