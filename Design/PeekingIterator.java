/*
* Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

Example:

Assume that the iterator is initialized to the beginning of the list: [1,2,3].

Call next() gets you 1, the first element in the list.
Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
You call next() the final time and it returns 3, the last element.
Calling hasNext() after that should return false.
Follow up: How would you extend your design to be generic and work with all types, not just integer?

    20181125
    284
    medium
    O()
    O()
* */
package leetcode.Design;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class PeekingIterator<E> implements Iterator<E> {
    private final Iterator<E> it;
    private E curElement;

    public PeekingIterator(Iterator<E> iterator) {
        this.it = iterator;
        if (it.hasNext()) {
            curElement = it.next();
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it1 = list.iterator();
        PeekingIterator<Integer> it2 = new PeekingIterator<>(it1);

        System.out.println(it2.next());
        System.out.println(it2.peek());
        System.out.println(it2.next());
        System.out.println(it2.next());
        System.out.println(it2.hasNext());
    }

    // Returns the next element in the iteration without advancing the iterator.
    public E peek() {
        return curElement;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public E next() {
        E tmp = curElement;
        if (!it.hasNext()) {
            curElement = null;
        } else {
            curElement = it.next();
        }
        return tmp;
    }

    @Override
    public boolean hasNext() {
        return curElement != null;
    }
}
